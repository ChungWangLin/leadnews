package com.bytedance.leadnews.common.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实名认证实体类
 */
@Data
public class ApUserRealName implements Serializable {
    private static final long serialVersionUID = -7317134199018755676L;
    private Integer id;
    private Integer userId;
    private String name;
    private String idNo;
    private String fontImage;
    private String backImage;
    private String holdImage;
    private String liveImage;
    private Byte status;
    private String reason;
    private LocalDateTime createdTime;
    private LocalDateTime submittedTime;
    private LocalDateTime updatedTime;
}
