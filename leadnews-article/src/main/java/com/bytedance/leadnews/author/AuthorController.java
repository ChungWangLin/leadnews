package com.bytedance.leadnews.author;

import com.bytedance.leadnews.api.article.ApAuthorApi;
import com.bytedance.leadnews.common.pojo.param.article.ApAuthorParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthorController implements ApAuthorApi {
    private final AuthorService authorService;

    @Override
    public void createApAuthor(@RequestBody @Validated ApAuthorParam.CreateParams params) {
        if (CollectionUtils.isEmpty(params.getParams())) {
            log.warn("批量创建作者信息，集合为空！{}",params.getParams());
            return;
        }
        authorService.createAuthor(params);
    }
}
