package com.bytedance.leadnews.api.user;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import com.bytedance.leadnews.common.pojo.param.user.UserParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserAuthApi {

    /**
     * 分页获取用户审核信息
     */
    @GetMapping("/auth")
    PageInfo<ApUserRealName> getUserAuthByPage(Integer page, Integer size,
                                               @RequestParam(required = false) String idNo,
                                               @RequestParam(required = false) Byte status,
                                               @RequestParam(required = false) String submittedTime,
                                               @RequestParam(required = false) String name);

    /**
     * 批量通过
     */
    @PutMapping("/auth/status")
    void updateStatus(@RequestBody @Validated UserParam.Status status);
}
