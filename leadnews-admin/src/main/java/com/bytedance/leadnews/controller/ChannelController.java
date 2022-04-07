package com.bytedance.leadnews.controller;

import com.bytedance.leadnews.api.channel.ChannelApi;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.common.pojo.param.admin.ChannelParam;
import com.bytedance.leadnews.exception.ParamRequestException;
import com.bytedance.leadnews.pojo.bo.QueryCondition;
import com.bytedance.leadnews.service.ChannelService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChannelController implements ChannelApi {
    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    @GetMapping("/channels")
    public PageInfo<AdChannel> getApChannelByPage(@RequestParam("page") Integer page,
                                                  @RequestParam("size") Integer size,
                                                  @RequestParam(required = false) String name,
                                                  @RequestParam(required = false) Byte status) {
        if (page<1 || size<1) {
            throw new ParamRequestException("不合法参数");
        }
        QueryCondition query = new QueryCondition(name,status);
        return channelService.findByPage(page,size,query);
    }

    @Override
    @PostMapping("/channels")
    public void createChannel(@RequestBody @Validated ChannelParam.Create param) {
        channelService.createChannel(param);
    }
}
