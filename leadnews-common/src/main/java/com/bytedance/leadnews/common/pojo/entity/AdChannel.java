package com.bytedance.leadnews.common.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 频道
 */
@Data
public class AdChannel {
    private Integer id;
    private String name;
    private String description;
    private Byte isDefault;
    private Byte status;
    private Byte ord;
    private LocalDateTime createdTime;
}
