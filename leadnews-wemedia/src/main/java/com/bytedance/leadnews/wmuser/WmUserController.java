package com.bytedance.leadnews.wmuser;

import com.bytedance.leadnews.api.wemedia.WmUserApi;
import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WmUserController implements WmUserApi {
    private final WmUserService wmUserService;

    public WmUserController(WmUserService wmUserService) {
        this.wmUserService = wmUserService;
    }

    @Override
    @PostMapping("/vm-users")
    public void createWmUser(@RequestBody @Validated WmUserParam.UserInfo userInfo) {
        wmUserService.batchCreateWmUser(userInfo.getUserInfo());
    }
}
