package com.bytedance.leadnews.pojo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NewsParam {
    @Data
    public static class Create{

        @NotBlank(message = "参数不合法")
        String title;

        @NotNull(message = "参数不合法")
        List<Content> content;

        @NotNull(message = "参数不合法")
        Integer type;

        @NotNull(message = "参数不合法")
        Integer channelId;

        @NotBlank(message = "参数不合法")
        String labels;

        @NotNull(message = "参数不合法")
        List<String> images;
    }

    @Data
    public static class Content{
        private String type;
        private String value;
    }


    public static class Edit extends Create{
        @NotNull(message = "参数不合法")
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
