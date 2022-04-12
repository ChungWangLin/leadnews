package com.bytedance.leadnews.common.pojo.param.article;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ApAuthorParam {

    @Data
    public static class Create implements Serializable {
        private static final long serialVersionUID = -8500853216872430030L;

        @NotBlank(message = "参数不合法")
        private String name;

        @NotNull(message = "参数不合法")
        private Integer type;

        @NotNull(message = "参数不合法")
        private Integer UserId;

        @NotNull(message = "参数不合法")
        private Integer wmUserId;
    }

    @Data
    @Valid
    public static class CreateParams{
        private List<Create> params;
    }
}
