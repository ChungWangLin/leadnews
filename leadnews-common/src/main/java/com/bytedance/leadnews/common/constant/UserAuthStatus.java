package com.bytedance.leadnews.common.constant;

/**
 * 用户实名认证状态
 */
public final class UserAuthStatus {
    private UserAuthStatus(){}

    public static final Byte CREATE = 0;
    public static final Byte WAIT = 1;
    public static final Byte FAILED = 2;
    public static final Byte PASS = 9;
}
