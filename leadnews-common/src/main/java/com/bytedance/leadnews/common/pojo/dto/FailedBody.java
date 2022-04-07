package com.bytedance.leadnews.common.pojo.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 统一失败返回体
 */
@Data
public class FailedBody {
    private LocalDateTime time;
    private Integer status;
    private String error;
    private String path;

    private FailedBody( Integer status, String error, String path) {
        this.time = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.path = path;
    }

    public static FailedBody failed(Integer status, String error, String path) {
        return new FailedBody(status,error,path);
    }
}
