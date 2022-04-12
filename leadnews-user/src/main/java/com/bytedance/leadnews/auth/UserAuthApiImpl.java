package com.bytedance.leadnews.auth;

import com.bytedance.leadnews.api.user.UserAuthApi;
import com.bytedance.leadnews.bo.UserAuthQuery;
import com.bytedance.leadnews.common.constant.UserAuthStatus;
import com.bytedance.leadnews.common.exception.ParamRequestException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import com.bytedance.leadnews.common.pojo.param.user.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
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

    @Override
    public void updateStatus(@RequestBody @Validated UserParam.Status status) {
        checkedStatus(status);
        userAuthService.batchUpdateStatus(status);
    }

    private void checkedStatus(UserParam.Status status) {
        Integer statue = status.getStatus();
        if (statue==null) {
            log.error("更改状态为:{}",statue);
            throw new ParamRequestException("参数不合法！");
        }

        if (statue.equals(UserAuthStatus.PASS.getCode())) {
            status.setReason(null);
        } else if (
                !statue.equals(UserAuthStatus.CREATE.getCode()) &&
                !statue.equals(UserAuthStatus.WAIT.getCode()) &&
                !statue.equals(UserAuthStatus.FAILED.getCode())) {
            log.error("更改状态为:{}",statue);
            throw new ParamRequestException("参数不合法！");
        }
    }
}
