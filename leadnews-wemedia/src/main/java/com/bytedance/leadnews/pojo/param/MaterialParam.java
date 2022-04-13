package com.bytedance.leadnews.pojo.param;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MaterialParam {

    @Data
    public static class Update{
        @NotNull(message = "参数不合法")
        private Integer id;

        @NotNull(message = "参数不合法")
        @Max(value = 1,message = "参数不合法")
        @Min(value = 0,message = "参数不合法")
        private Integer collection;

        private Integer userId;
    }
}
