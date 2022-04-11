package com.bytedance.leadnews.common.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AdSensitive implements Serializable {
    private static final long serialVersionUID = 1260079192454626565L;
    private Integer id;
    private String sensitives;
    private LocalDateTime createdTime;
}
