package com.bytedance.leadnews.common.pojo.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.bytedance.leadnews.common.pojo.param.article.ApAuthorParam;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * <p>
 * APP文章作者信息表
 * </p>
 */
@Data
public class ApAuthor implements Serializable {
    private static final long serialVersionUID = -2867988431233965935L;

    private Integer id;

    private String name;

    private Integer type;

    private Integer userId;

    private LocalDateTime createdTime;

    private Integer wmUserId;

    public ApAuthor convertFromCreateParam(ApAuthorParam.Create param) {
        BeanUtils.copyProperties(param,this);
        return this;
    }
}
