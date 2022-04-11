package com.bytedance.leadnews.common.pojo.entity;

import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class WmUser implements Serializable {
    private static final long serialVersionUID = 6531311869344987605L;
    private Integer id;
    private Integer apUserId;
    private Integer apAuthorId;
    private String name;
    private String password;
    private String salt;
    private String nickname;
    private String image;
    private String location;
    private String phone;
    private Integer status;
    private String email;
    private Integer type;
    private Integer score;
    private LocalDateTime loginTime;
    private LocalDateTime createdTime;

    public WmUser convertFromCreateParam(WmUserParam.Create param) {
        BeanUtils.copyProperties(param,this);
        this.createdTime=LocalDateTime.now();
        return this;
    }
}
