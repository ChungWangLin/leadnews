package com.bytedance.leadnews.common.util;

public class UserHolder {
    private UserHolder(){}
    private static final ThreadLocal<Integer> userThreadLocal = new ThreadLocal<>();

    /**
     * 存储登录用户Id
     */
    public static void setUser(Integer userId) {
        userThreadLocal.set(userId);
    }

    /**
     * 获取登录用户信息
     */
    public static Integer getUser(){
        return userThreadLocal.get();
    }

    /**
     * 移除登录用户
     */
    public static void remove(){
        userThreadLocal.remove();
    }
}
