package com.bytedance.leadnews.common.pojo.param.admin;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SensitiveParam {
    @Data
    public static class Create{
        @NotBlank(message = "敏感词不能为空")
        private String sensitives;
    }

    @Data
    public static class Update{
        @NotNull(message = "id不能为空")
        private Integer id;

        @Length(min = 1,max = 10,message = "名称范围(1-10)")
        private String sensitives;
    }
}
