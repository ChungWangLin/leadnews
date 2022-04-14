package com.bytedance.leadnews.news;

import com.bytedance.leadnews.pojo.entity.News;
import com.bytedance.leadnews.pojo.bo.NewsCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {

    List<News> findByPage(@Param("starter") Long starter, @Param("size") Integer size, @Param("condition") NewsCondition condition);

    Long count(@Param("condition") NewsCondition condition);

    void save(News draft);

    void updateDraft(News draft);

    News findNewsById(@Param("id") Integer id);

    void delete(@Param("id") Integer id, @Param("userId") Integer userId);

    void updateEnabled(@Param("id") Integer id, @Param("userId") Integer userId, @Param("enabled") Integer enabled);
}
