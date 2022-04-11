package com.bytedance.leadnews.common.pojo.param.wemedia;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class WmUserParam implements Serializable {
    @Data
    public static class Create implements Serializable{
        private static final long serialVersionUID = 1743189088330440987L;

        @NotNull(message = "参数不合法")
        private Integer apUserId;

        @NotBlank(message = "参数不合法")
        private String name;

        @NotBlank(message = "参数不合法")
        private String password;

        @NotBlank(message = "参数不合法")
        private String salt;

        @NotBlank(message = "参数不合法")
        private String image;

        @NotBlank(message = "参数不合法")
        private String phone;

        @NotNull(message = "参数不合法")
        private Integer status;
    }

    @Data
    public static class UserInfo implements Serializable{
        private static final long serialVersionUID = 8073602329590726068L;
        @Valid
        private List<Create> userInfo;
    }
}
