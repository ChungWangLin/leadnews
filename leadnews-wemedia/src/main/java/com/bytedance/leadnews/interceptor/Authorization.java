package com.bytedance.leadnews.interceptor;

import com.bytedance.leadnews.common.constant.Token;
import com.bytedance.leadnews.common.util.UserHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;

@Component
@Slf4j
@RequiredArgsConstructor
public class Authorization implements HandlerInterceptor {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.debug("当前URI:{}", uri);

        // 1、判断当前路径是否需要拦截
        if (intercept(uri)) {
            log.debug("uri({})在白名单中，放行", uri);
            return true;
        }

        String token = request.getHeader("Authorization");
        if (token != null) {
            log.debug("token{}", token);
            String key = Token.WM_USER + token;
            Integer userId =(Integer) redisTemplate.opsForValue().get(key);

            if (userId != null) {
                Long expire = redisTemplate.getExpire(key);
                if (expire==null || expire<60*10){
                    log.warn("expire:{},即将过期",expire);
                    redisTemplate.expire(key, Duration.ofSeconds(60*60*24));
                }
                UserHolder.setUser(userId);
                return true;
            }
        }

        log.warn("token为空或校验失败");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }

    /**
     * 判断uri是否在白名单
     *
     * @return true:在白名单， false:不在白名单
     */
    private boolean intercept(String uri) {
        String[] whiteList = {"/login"};
        for (String u : whiteList) {
            if (antPathMatcher.match(u, uri)) {
                return true;
            }
        }
        return false;
    }
}
