package com.bytedance.leadnews.service;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdSensitive;
import com.bytedance.leadnews.common.pojo.param.admin.SensitiveParam;
import com.bytedance.leadnews.dao.SensitiveDao;
import com.bytedance.leadnews.exception.ConflictException;
import com.bytedance.leadnews.pojo.bo.SensitiveQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SensitiveService {
    private final SensitiveDao sensitiveDao;

    public SensitiveService(SensitiveDao sensitiveDao) {
        this.sensitiveDao = sensitiveDao;
    }

    /**
     * 按条件分页查询
     */
    public PageInfo<AdSensitive> findByPage(Integer page, Integer size, String keyword) {
        long offset = (long) (page - 1) *size;
        SensitiveQuery query = new SensitiveQuery(keyword);
        List<AdSensitive> list = sensitiveDao.findByCondition(offset, size, query);
        Long count = sensitiveDao.count(query);
        PageInfo<AdSensitive> pageInfo = new PageInfo<>();
        return pageInfo.init(page,size,count,list);
    }

    /**
     * 新增敏感词
     */
    public void createSensitive(SensitiveParam.Create param) {
        if (findBySensitive(param.getSensitives())!=null) {
            throw new ConflictException("敏感词已存在");
        }
        AdSensitive adSensitive = new AdSensitive();
        BeanUtils.copyProperties(param,adSensitive);
        adSensitive.setCreatedTime(LocalDateTime.now());
        sensitiveDao.insert(adSensitive);
    }

    /**
     * 按敏感词查找
     * @param sensitive 敏感词
     * @return 敏感词实体类
     */
    public AdSensitive findBySensitive(String sensitive){
        return sensitiveDao.findBySensitive(sensitive);
    }

    /**
     * 更新敏感词
     */
    public void update(SensitiveParam.Update param) {
        if (findBySensitive(param.getSensitives())!=null) {
            throw new ConflictException("敏感词已存在");
        }
        AdSensitive adSensitive = new AdSensitive();
        BeanUtils.copyProperties(param,adSensitive);
        sensitiveDao.update(adSensitive);
    }

    /**
     * 根据id批量删除
     */
    public void batchDelete(List<Long> idList) {
        sensitiveDao.batchDelete(idList);
    }
}
