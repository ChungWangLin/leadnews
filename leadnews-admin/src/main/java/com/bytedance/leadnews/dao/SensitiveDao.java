package com.bytedance.leadnews.dao;

import com.bytedance.leadnews.common.pojo.entity.AdSensitive;
import com.bytedance.leadnews.pojo.bo.SensitiveQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SensitiveDao {

    /**
     * 分页条件查询
     */
    List<AdSensitive> findByCondition(@Param("offset") Long offset, @Param("size") Integer size, @Param("condition") SensitiveQuery condition);

    /**
     * 按条件查询总数
     */
    Long count(@Param("condition") SensitiveQuery condition);

    /**
     * 根据敏感词精确查找
     */
    AdSensitive findBySensitive(@Param("sensitives") String sensitives);

    /**
     * 创建敏感词
     */
    void insert(AdSensitive adSensitive);

    /**
     * 更新敏感词
     */
    void update(AdSensitive adSensitive);

    /**
     * 根据id批量删除
     */
    void batchDelete(@Param("idList") List<Long> idList);
}
