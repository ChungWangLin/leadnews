package com.bytedance.leadnews.news;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.pojo.entity.News;
import com.bytedance.leadnews.pojo.bo.NewsCondition;
import com.bytedance.leadnews.pojo.param.NewsParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    /**
     * 分页查询文章
     */
    @GetMapping("/vm/news")
    public PageInfo<News> findByPage(Integer page, Integer size,
                                     @RequestParam(required = false) Integer status,
                                     @RequestParam(required = false) String title,
                                     @RequestParam(required = false) Integer channelId,
                                     @RequestParam(required = false) String publishStartTime,
                                     @RequestParam(required = false) String publishEndTime) {

        PageInfo.checkedPage(page, size);
        NewsCondition condition = new NewsCondition(status,title,channelId,publishStartTime,publishEndTime);
        return newsService.findByPage(page,size,condition);
    }

    @PostMapping("/vm/news")
    public Integer createNews(@RequestBody @Validated NewsParam.Create param) {
        return newsService.create(param);
    }

    @PutMapping("/vm/news")
    public void updateDraft(@RequestBody @Validated NewsParam.Edit param) {
        newsService.updateDraft(param);
    }
}
