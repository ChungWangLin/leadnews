package com.bytedance.leadnews.common.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WmMaterial {
    private Integer id;
    private Integer userId;
    private String url;
    private Integer type;
    private Integer collection;
    private LocalDateTime createdTime;
}
