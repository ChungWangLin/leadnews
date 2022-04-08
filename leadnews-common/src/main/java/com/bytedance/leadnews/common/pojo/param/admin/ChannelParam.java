package com.bytedance.leadnews.common.pojo.param.admin;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 频道管理相关入参
 */
public class ChannelParam implements Serializable {

    @Data
    /**
     * 创建频道参数
     */
    public static class Create implements Serializable{
        private static final long serialVersionUID = 2025087070788020284L;
        @NotBlank(message = "频道名不能为空")
        @Length(min = 1,max = 10,message = "名称长度(1-10)")
        private String name;

        @NotNull(message = "请输入(0-16)位的简短描述")
        @Length(min = 0, max = 16, message = "请输入(0-16)位的简短描述")
        private String description;

        @NotNull(message = "状态不能为空")
        @Min(value = 0,message = "状态不合法")
        @Max(value = 1,message = "状态不合法")
        private Byte status;

        @NotNull(message = "排序不能为空")
        private Byte ord;
    }

    @Getter
    @Setter
    public static class Update implements Serializable{
        private static final long serialVersionUID = 2054713634196019869L;
        @NotNull(message = "id不能为空")
        private Integer id;

        @Length(max = 10,message = "名称长度(1-10)")
        private String name;

        @Length(max = 16,message = "请输入(1-16)简短描述")
        private String description;

        @Max(value = 1,message = "状态参数不合法")
        @Min(value = 0,message = "状态参数不合法")
        private Byte status;

        @Max(value = 1,message = "不合法的默认值")
        @Min(value = 0,message = "不合法的默认值")
        private Byte isDefault;

        @Min(value = 0,message = "不合法的排序")
        private Byte ord;
    }
}
