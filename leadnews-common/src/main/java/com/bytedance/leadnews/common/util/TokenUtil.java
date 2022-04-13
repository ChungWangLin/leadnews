package com.bytedance.leadnews.common.util;

import java.util.Locale;
import java.util.UUID;

public class TokenUtil {
    private TokenUtil(){}
    public static String createToken(){
        return UUID.randomUUID().toString().replace("-","").toUpperCase(Locale.ROOT);
    }
}
