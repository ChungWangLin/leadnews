package com.bytedance.leadnews.common.constant;

public enum WmUserStatus {

    NEVER_LOCK(1,"永久不可用"),
    LOCK(0,"不可用"),
    OK(9,"正常");
    final Integer code;
    final String status;

    WmUserStatus(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
