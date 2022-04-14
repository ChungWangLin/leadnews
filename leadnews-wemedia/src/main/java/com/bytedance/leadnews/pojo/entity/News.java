package com.bytedance.leadnews.pojo.entity;

import com.bytedance.leadnews.common.exception.CustomerException;
import com.bytedance.leadnews.pojo.bo.ContentNode;
import com.bytedance.leadnews.pojo.param.NewsParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Slf4j
public class News {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer type;
    private Integer channelId;
    private String labels;
    private LocalDateTime createdTime;
    private LocalDateTime submittedTime;
    private Integer status;
    private LocalDateTime publishTime;
    private String reason;
    private Integer articleId;
    private String images;
    private Integer enable;


    public static enum Status {
        DRAFT(0,"草稿"),
        AUTO_REVIEW(1,"审核中"),
        AUTO_REVIEW_FAILED(2,"审核失败"),
        REVIEW(3,"人工审核"),
        REVIEW_PASS(4,"人工审核通过"),
        AUTO_REVIEW_PASS(8,"审核通过"),
        PUBLISHED(9,"已发布");

        final Integer  status;
        final String desc;

        Status(Integer status,String desc) {
            this.status = status;
            this.desc = desc;
        }

        public Integer value() {
            return status;
        }
    }

    public News(){}

    private News(String title, String labels, String images, Integer channelId, String content, Integer type) {
        this.title = title;
        this.labels = labels;
        this.images = images;
        this.channelId = channelId;
        this.content = content;
        this.type = type;
    }

    public News createDraft(NewsParam.Create param,Integer userId){
        News news = createNews(param);
        news.setStatus(Status.DRAFT.value());
        news.setUserId(userId);
        return news;
    }

    private String getNewContent(List<ContentNode> content) {
        String str;
        if (CollectionUtils.isEmpty(content)) {
            str = null;
        } else {
            try {
                str = new ObjectMapper().writeValueAsString(content);
            } catch (Exception e) {
                log.warn("文章解析错误！");
                throw new CustomerException("文章解析错误！");
            }
        }
        return str;
    }

    /**
     * 自动获取文章封面并计算封面图片个数
     * @param contents 文章内容
     * @return 封面图片列表
     */
    private Map<String,Object> getImages(List<ContentNode> contents) {
        Map<String,Object> map = new HashMap<>();
        List<String> images = new ArrayList<>();
        for (ContentNode content : contents) {
            if (content.getType().equals("image")) {
                images.add(content.getValue());
            }
        }
        if (images.size()==0) {
            map.put("type",0);
        } else if (images.size()==1) {
            map.put("type",1);
        } else {
            map.put("type",3);
        }

        map.put("images",images);
        return map;
    }

    private News createNews(NewsParam.Create param) {
        String title = param.getTitle();
        String labels = param.getLabels();
        String images;
        Integer type = param.getType();
        Integer channelId = param.getChannelId();

        String content = getNewContent(param.getContent());
        if (type==-1) {
            Map<String, Object> imageMap = getImages(param.getContent());
            type = (Integer) imageMap.get("type");
            images = String.join(",",(List<String>)imageMap.get("images"));
        } else {
            images = String.join(",",param.getImages());
        }
        return new News(title,labels,images,channelId,content,type);
    }
}
