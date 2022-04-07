package com.bytedance.leadnews.service;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.common.pojo.param.admin.ChannelParam;
import com.bytedance.leadnews.dao.ChannelDao;
import com.bytedance.leadnews.pojo.bo.QueryCondition;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChannelService {
    private final ChannelDao channelDao;

    public ChannelService(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }

    /**
     * 按条件分页查询
     * @param page 当前页
     * @param size 分页大小
     * @param condition 条件
     */
    public PageInfo<AdChannel> findByPage(Integer page, Integer size, QueryCondition condition) {
        Long count = channelDao.findCountByCondition(condition);
        long offset = (long) (page - 1) *size;
        List<AdChannel> lis = channelDao.findByPage(offset,size,condition);
        PageInfo<AdChannel> pageInfo = new PageInfo<>();
        return pageInfo.init(page, size, count, lis);
    }

    /**
     * 新增频道
     */
    public void createChannel(ChannelParam.Create param) {
        AdChannel channel = new AdChannel().convertFromParam(param);
        LocalDateTime now = LocalDateTime.now();
        channel.setCreatedTime(now);
        channelDao.insertChannel(channel);
    }
}
