package com.bytedance.leadnews.api.user;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserAuthApi {
    @GetMapping("/auth")
    PageInfo<ApUserRealName> getUserAuthByPage(Integer page, Integer size,
                                               @RequestParam(required = false) String idNo,
                                               @RequestParam(required = false) Byte status,
                                               @RequestParam(required = false) String submittedTime,
                                               @RequestParam(required = false) String name);
}
