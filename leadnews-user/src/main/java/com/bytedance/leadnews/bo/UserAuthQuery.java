package com.bytedance.leadnews.bo;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class UserAuthQuery {
    private String name;
    private String idNo;
    private LocalDateTime submittedTime;
    private Byte status;

    public UserAuthQuery() {
    }

    public UserAuthQuery(String idNo,String name, String submittedTime, Byte status) {
        this.name = name;
        this.idNo = idNo;
        this.status = status;
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        this.submittedTime = submittedTime==null?null:LocalDateTime.parse(submittedTime,df);
    }
}
