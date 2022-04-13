package com.bytedance.leadnews.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginParam {
    @NotBlank(message = "参数不合法")
    private String username;

    @NotBlank(message = "参数不合法")
    private String password;
}
