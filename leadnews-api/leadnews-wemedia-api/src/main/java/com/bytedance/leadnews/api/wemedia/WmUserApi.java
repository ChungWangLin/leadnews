package com.bytedance.leadnews.api.wemedia;

import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "wm-service")
public interface WmUserApi {

    /**
     * 创建自媒体账户
     */
    @PostMapping("/vm-users")
    void createWmUser(WmUserParam.UserInfo userInfo);
}
