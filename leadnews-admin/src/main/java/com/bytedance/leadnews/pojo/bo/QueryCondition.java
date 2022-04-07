package com.bytedance.leadnews.pojo.bo;

import lombok.Data;

@Data
public class QueryCondition  {
    private String name;
    private Byte status;

    public QueryCondition( String name, Byte status) {
        this.name = name;
        this.status = status;
    }

}
