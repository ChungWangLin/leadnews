package com.bytedance.leadnews.news;

import com.bytedance.leadnews.common.exception.ParamRequestException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.pojo.dto.NewsDTO;
import com.bytedance.leadnews.pojo.entity.News;
import com.bytedance.leadnews.pojo.bo.NewsCondition;
import com.bytedance.leadnews.pojo.param.NewsParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
    @GetMapping("/wm/news")
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

    /**
     * 保存文章
     */
    @PostMapping("/wm/news")
    public Integer createNews(@RequestBody @Validated NewsParam.Create param) {
        return newsService.create(param);
    }

    /**
     * 更新文章内容
     */
    @PutMapping("/wm/news")
    public void updateDraft(@RequestBody @Validated NewsParam.Edit param) {
        newsService.updateDraft(param);
    }

    /**
     * 根据id获取文章
     */
    @GetMapping("/wm/news/{id}")
    public NewsDTO getById(@PathVariable("id") Integer id) {
        return newsService.findById(id);
    }

    /**
     * 删除文章
     */
    @DeleteMapping("/wm/news/{id}")
    public void deleteNews(@PathVariable("id") Integer id) {
        newsService.deleteById(id);
    }

    /**
     * 更改文章状态
     */
    @PutMapping("/wm/news/{id}")
    public void enable(@PathVariable("id") Integer id, @RequestParam Integer enabled) {
        if (enabled!=0 && enabled!=1) {
            throw new ParamRequestException();
        }

        newsService.setEnabled(id,enabled);
    }
}
