package com.bytedance.leadnews.common.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

public class PasswordEncode {
    private PasswordEncode(){}

    public static String encode(String password) {
        String salt = "abc";
        return DigestUtils.md5DigestAsHex((salt + password).getBytes(StandardCharsets.UTF_8));
    }
}
