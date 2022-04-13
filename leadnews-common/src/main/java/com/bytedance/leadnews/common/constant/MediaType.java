package com.bytedance.leadnews.common.constant;

public enum MediaType {
    PICTURE(0),
    VIDEO(1);
    final Integer type;

    MediaType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
