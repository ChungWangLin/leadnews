package com.bytedance.leadnews.pojo.bo;

import lombok.Data;

@Data
public class ChannelQuery {
    private String name;
    private Byte status;

    public ChannelQuery(String name, Byte status) {
        this.name = name;
        this.status = status;
    }

}
