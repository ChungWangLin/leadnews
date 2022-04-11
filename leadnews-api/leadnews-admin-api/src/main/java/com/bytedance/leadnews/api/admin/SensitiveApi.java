package com.bytedance.leadnews.api.admin;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdSensitive;
import com.bytedance.leadnews.common.pojo.param.admin.SensitiveParam;

/**
 * 敏感词管理
 */
public interface SensitiveApi {
    /**
     * 分页条件查询敏感词
     */
    PageInfo<AdSensitive> getSensitiveByPage(Integer page, Integer size, String keyword);

    /**
     * 新增敏感词
     */
    void create(SensitiveParam.Create param);

    /**
     * 更新敏感词
     */
    void update(SensitiveParam.Update param);

    /**
     * 根据id删除
     */
    void delete(String ids);
}
