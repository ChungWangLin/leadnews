package com.bytedance.leadnews.wmuser;

import com.bytedance.leadnews.common.constant.Token;
import com.bytedance.leadnews.common.constant.WmUserStatus;
import com.bytedance.leadnews.common.exception.CustomerException;
import com.bytedance.leadnews.common.pojo.entity.WmUser;
import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import com.bytedance.leadnews.common.util.PasswordEncode;
import com.bytedance.leadnews.common.util.TokenUtil;
import com.bytedance.leadnews.pojo.dto.LoginInfo;
import com.bytedance.leadnews.pojo.param.LoginParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WmUserService {
    private final WmUserDao wmUserDao;
    private final RedisTemplate<String,Object> redisTemplate;

    /**
     * 批量添加自媒体账户
     */
    public List<WmUser> batchCreateWmUser(List<WmUserParam.Create> params){
        List<WmUser> wmUsers = params.stream().map(e-> {
            WmUser wmUser = new WmUser().convertFromCreateParam(e);
            wmUser.setStatus(WmUserStatus.OK.getCode());
            return wmUser;
        }).collect(Collectors.toList());
        wmUserDao.batchInsert(wmUsers);
        return wmUsers;
    }

    public LoginInfo login(LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();

        WmUser wmUser = wmUserDao.findByUsername(username);
        if (wmUser==null) {
            throw new CustomerException("用户不存在");
        }

        if (!PasswordEncode.encode(password).equals(wmUser.getPassword())) {
            throw new CustomerException("密码错误");
        }

        LoginInfo loginInfo = new LoginInfo().convertFromWmUser(wmUser);
        String token = TokenUtil.createToken();
        String key = Token.WM_USER+ token;
        Integer value = wmUser.getApUserId();
        redisTemplate.opsForValue().set(key,value, Duration.ofSeconds(60*60*24));
        loginInfo.setToken(token);
        return loginInfo;
    }

}
