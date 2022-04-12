package com.bytedance.leadnews.author;

import com.bytedance.leadnews.common.pojo.entity.ApAuthor;
import com.bytedance.leadnews.common.pojo.param.article.ApAuthorParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao authorDao;

    /**
     * 添加作者
     */
    public void createAuthor(ApAuthorParam.CreateParams params) {
        List<ApAuthor> apAuthors = params.getParams().stream().map(e->{
            ApAuthor apAuthor = new ApAuthor().convertFromCreateParam(e);
            apAuthor.setCreatedTime(LocalDateTime.now());
            return apAuthor;
        }).collect(Collectors.toList());
        authorDao.save(apAuthors);
    }
}
