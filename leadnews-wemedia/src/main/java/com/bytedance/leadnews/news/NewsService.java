package com.bytedance.leadnews.news;

import com.bytedance.leadnews.common.exception.ParamRequestException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.pojo.bo.ContentNode;
import com.bytedance.leadnews.pojo.dto.NewsDTO;
import com.bytedance.leadnews.pojo.entity.News;
import com.bytedance.leadnews.common.util.UserHolder;
import com.bytedance.leadnews.pojo.bo.NewsCondition;
import com.bytedance.leadnews.pojo.param.NewsParam;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public NewsDTO findById(Integer id) {
        News news = newsDao.findNewsById(id);
        String content = news.getContent();
        List<ContentNode> contents = getContents(content);
        List<String> images = getImages(news.getImages());
        NewsDTO newsDTO = new NewsDTO(news);
        newsDTO.setContent(contents);
        newsDTO.setImages(images);
        return newsDTO;
    }

    private List<ContentNode> getContents(String contentStr) {
        List list = new ArrayList<>();
        try {
            list = new ObjectMapper().readValue(contentStr,List.class);
        } catch (Exception e) {
            log.error("转换失败！");
        }
        return list;
    }

    private List<String> getImages(String imagesStr) {
        return Arrays.asList(imagesStr.split(","));
    }

    /**
     * 删除文章，状态为9和已上架的文章不能删除
     */
    public void deleteById(Integer id) {
        Integer userId = UserHolder.getUserId();
        News news = newsDao.findNewsById(id);
        if (news==null) {
            return;
        }
        if(news.getEnable()==1 &&  News.Status.PUBLISHED.value().equals(news.getStatus())) {
            log.warn("文章【{}】=>状态为【{}】,上架情况为【{}】，不能删除",id,news.getStatus(),news.getEnable());
            throw new ParamRequestException();
        }
        newsDao.delete(id, userId);
    }

    /**
     * 更改文章状态
     */
    public void setEnabled(Integer id, Integer enabled) {
        Integer userId = UserHolder.getUserId();
        News news = newsDao.findNewsById(id);
        if (news==null) {
            return;
        }
        if (!News.Status.PUBLISHED.value().equals(news.getStatus())) {
            log.warn("该文章[{}]还未发布，不能改状态！",id);
            throw new ParamRequestException();
        }
        newsDao.updateEnabled(id, userId, enabled);
    }
}
