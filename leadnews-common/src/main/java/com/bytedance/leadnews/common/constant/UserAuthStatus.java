package com.bytedance.leadnews.common.constant;

/**
 * 用户实名认证状态
 */
public enum UserAuthStatus {
    CREATE(0, "创建中"),
    WAIT(1,"等待审核"),
    FAILED(2,"审核失败"),
    PASS(9,"审核通过");

    final Integer code;
    final String status;

    UserAuthStatus(Integer code, String status){
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
