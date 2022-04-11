package com.bytedance.leadnews.pojo.bo;

import lombok.Data;

@Data
public class SensitiveQuery {
    private String keyword;

    public SensitiveQuery() {
    }

    public SensitiveQuery(String keyword) {
        this.keyword = keyword;
    }
}
