package com.bytedance.leadnews.common.pojo.param.admin;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 频道管理相关入参
 */
public class ChannelParam {

    @Data
    /**
     * 创建频道参数
     */
    public static class Create{
        @NotBlank(message = "频道名不能为空")
        private String name;
        private String description;
        @NotNull(message = "状态不能为空")
        private Byte status;
        @NotNull(message = "排序不能为空")
        private Byte ord;
    }
}
