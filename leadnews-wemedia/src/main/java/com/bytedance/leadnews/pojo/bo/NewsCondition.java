package com.bytedance.leadnews.pojo.bo;

import com.bytedance.leadnews.common.exception.ParamRequestException;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class NewsCondition {
    private Integer status;
    private String titleKeyword;
    private Integer channelId;
    private LocalDateTime publishStartTime;
    private LocalDateTime publishEndTime;
    private Integer userId;

    public void checkedParam() {
        if (publishStartTime!=null && publishEndTime==null) {
            throw new ParamRequestException();
        }
        if (publishStartTime==null && publishEndTime!=null) {
            throw new ParamRequestException();
        }
    }

    public NewsCondition() {
    }

    public NewsCondition(Integer status, String titleKeyword, Integer channelId, String publishStartTime, String publishEndTime) {
        checkedParam();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime end = null;
        LocalDateTime start = null;
        if (publishStartTime!=null && publishEndTime!=null) {
            try {
                end = LocalDateTime.parse(publishEndTime, df);
                start = LocalDateTime.parse(publishStartTime,df);
            }catch (Exception e) {
                throw new ParamRequestException();
            }
        }
        this.status = status;
        this.titleKeyword = titleKeyword;
        this.channelId = channelId;
        this.publishStartTime = start;
        this.publishEndTime = end;
    }
}
