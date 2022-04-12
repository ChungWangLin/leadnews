package com.bytedance.leadnews.wmuser;

import com.bytedance.leadnews.api.wemedia.WmUserApi;
import com.bytedance.leadnews.common.pojo.entity.WmUser;
import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class WmUserController implements WmUserApi {
    private final WmUserService wmUserService;

    public WmUserController(WmUserService wmUserService) {
        this.wmUserService = wmUserService;
    }

    @Override
    @PostMapping("/vm-users")
    public List<WmUser> createWmUser(@RequestBody @Validated WmUserParam.UserInfo userInfo) {
        List<WmUser> wmUsers = wmUserService.batchCreateWmUser(userInfo.getUserInfo());
        return wmUsers;
    }
}
