package com.bytedance.leadnews.common.pojo.param.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserParam {
    @Data
    public static class Status{

        @NotNull(message = "参数不合法")
        private List<Integer> ids;

        @NotNull(message = "参数不合法")
        private Integer status;

        @NotNull(message = "参数不合法")
        private String reason;
    }
}
