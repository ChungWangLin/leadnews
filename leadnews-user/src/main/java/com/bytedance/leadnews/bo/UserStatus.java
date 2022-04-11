package com.bytedance.leadnews.bo;

import com.bytedance.leadnews.common.pojo.param.user.UserParam;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserStatus {
    private Integer status;
    private String reason;
    private List<Integer> ids;
    private LocalDateTime now;

    public  UserStatus convertFromStatusParam(UserParam.Status status) {
        BeanUtils.copyProperties(status,this);
        this.now = LocalDateTime.now();
        return this;
    }
}
