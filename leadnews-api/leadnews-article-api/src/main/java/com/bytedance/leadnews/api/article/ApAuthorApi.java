package com.bytedance.leadnews.api.article;

import com.bytedance.leadnews.common.pojo.param.article.ApAuthorParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "article-service")
public interface ApAuthorApi {

    @PostMapping("/article/author")
    void createApAuthor(ApAuthorParam.CreateParams params);
}
