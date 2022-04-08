package com.bytedance.leadnews.api.channel;

import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.common.pojo.param.admin.ChannelParam;

public interface ChannelApi {
    /**
     * 条件分页查询
     */
    PageInfo<AdChannel> getApChannelByPage(Integer page, Integer size, String channelName, Byte status);

    /**
     * 创建频道
     */
    void createChannel(ChannelParam.Create param);

    /**
     * 更新频道
     */
    void updateChannel(ChannelParam.Update param);

    /**
     * 删除频道
     */
    void deleteChannelById(Integer id);

}
