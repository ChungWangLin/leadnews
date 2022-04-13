package com.bytedance.leadnews.pojo.dto;

import com.bytedance.leadnews.common.pojo.entity.WmUser;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 自媒体登录成功后，返回数据
 */
@Data
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = -4455565008250943971L;
    private String name;
    private String nickname;
    private String image;
    private String phone;
    private String token;

    public LoginInfo convertFromWmUser(WmUser wmUser) {
        BeanUtils.copyProperties(wmUser,this);
        return this;
    }
}
