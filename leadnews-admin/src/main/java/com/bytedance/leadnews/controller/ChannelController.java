package com.bytedance.leadnews.controller;

import channel.ChannelApi;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.pojo.bo.QueryCondition;
import com.bytedance.leadnews.service.ChannelService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChannelController implements ChannelApi {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    @GetMapping("/channels")
    public PageInfo<AdChannel> getApChannelByPage(@RequestParam Integer page, @RequestParam Integer size,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Byte status) {
        QueryCondition query = new QueryCondition(name,status);
        return channelService.findByPage(page,size,query);
    }
}
