package com.bytedance.leadnews.news;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.pojo.entity.News;
import com.bytedance.leadnews.common.util.UserHolder;
import com.bytedance.leadnews.pojo.bo.NewsCondition;
import com.bytedance.leadnews.pojo.param.NewsParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class NewsService {

    private final NewsDao newsDao;

    public PageInfo<News> findByPage(Integer page, Integer size, NewsCondition condition) {
        Integer userId = UserHolder.getUserId();
        condition.setUserId(userId);
        Long starter = PageInfo.limit(page, size);
        List<News> list = newsDao.findByPage(starter, size, condition);
        Long total = newsDao.count(condition);
        return new PageInfo<News>().init(page, size, total, list);
    }

    public Integer create(NewsParam.Create param) {
        News news = new News();
        Integer userId = UserHolder.getUserId();
        News draft = news.createDraft(param,userId);
        draft.setCreatedTime(LocalDateTime.now());
        draft.setEnable(1);
        newsDao.save(draft);
        return draft.getId();
    }

    public void updateDraft(NewsParam.Edit param) {
        News news = new News();
        Integer userId = UserHolder.getUserId();
        News draft = news.createDraft(param,userId);
        draft.setId(param.getId());
        newsDao.updateDraft(draft);
    }
}
