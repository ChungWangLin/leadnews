package com.bytedance.leadnews.auth;

import com.bytedance.leadnews.api.user.UserAuthApi;
import com.bytedance.leadnews.bo.UserAuthQuery;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthApiImpl implements UserAuthApi {
    private final UserAuthService userAuthService;

    public UserAuthApiImpl(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    public PageInfo<ApUserRealName> getUserAuthByPage(Integer page, Integer size,
                                                      @RequestParam(required = false) String idNo,
                                                      @RequestParam(required = false) Byte status,
                                                      @RequestParam(required = false) String submittedTime,
                                                      @RequestParam(required = false) String name) {
        PageInfo.checkedPage(page,size);
        UserAuthQuery query = new UserAuthQuery(idNo,name,submittedTime,status);
        return userAuthService.findByPage(page,size,query);
    }
}
