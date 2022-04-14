package com.bytedance.leadnews.pojo.dto;

import com.bytedance.leadnews.pojo.bo.ContentNode;
import com.bytedance.leadnews.pojo.entity.News;
import lombok.Data;

import java.util.List;

@Data
public class NewsDTO {

    private Integer id;

    private String title;

    private List<ContentNode> content;

    private Integer type;

    private Integer channelId;

    private String labels;

    private List<String> images;

    public NewsDTO(News news) {
        this.id = news.getId();
        this.channelId = news.getChannelId();
        this.labels = news.getLabels();
        this.title = news.getTitle();
        this.type = news.getType();
    }

    public NewsDTO(){}
}
