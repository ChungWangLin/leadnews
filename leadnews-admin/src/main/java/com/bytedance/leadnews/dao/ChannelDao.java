package com.bytedance.leadnews.dao;

import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.pojo.bo.ChannelQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChannelDao {
    /**
     * 根据条件查询频道数量
     * @param condition 查询条件
     */
    Long findCountByCondition(ChannelQuery condition);

    /**
     * 按条件分页查询
     * @param condition 条件
     */
    List<AdChannel> findByPage(@Param("offset") Long offset, @Param("size") Integer size, @Param("condition") ChannelQuery condition);

    /**
     * 新增频道
     * @param channel 频道
     */
    void insertChannel(AdChannel channel);

    /**
     * 更新频道
     */
    void updateChannel(AdChannel channel);

    /**
     * 删除频道
     */
    void deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据频道名查询AdChannel
     */
    AdChannel findByName(@Param("name") String name);
}
