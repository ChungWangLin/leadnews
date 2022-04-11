package com.bytedance.leadnews.controller;

import com.bytedance.leadnews.api.admin.ChannelApi;
import com.bytedance.leadnews.common.exception.ParamRequestException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdChannel;
import com.bytedance.leadnews.common.pojo.param.admin.ChannelParam;
import com.bytedance.leadnews.pojo.bo.ChannelQuery;
import com.bytedance.leadnews.service.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
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
        if (page==null || page<1 || size==null || size<1) {
            throw new ParamRequestException("不合法参数");
        }
        ChannelQuery query = new ChannelQuery(name,status);
        return channelService.findByPage(page,size,query);
    }

    @Override
    @PostMapping("/channels")
    public void createChannel(@RequestBody @Validated ChannelParam.Create param) {
        channelService.createChannel(param);
    }

    @Override
    @PutMapping("/channels")
    public void updateChannel(@RequestBody @Validated ChannelParam.Update param) {
        channelService.updateChannel(param);
    }


    @Override
    @DeleteMapping("/channels")
    public void deleteChannelByIds(@RequestParam("ids") String ids) {
        String[] idsArray = ids.split(",");
        List<Integer> channelIds;
        try {
            channelIds = Arrays.stream(idsArray).map(Integer::valueOf).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("错误的ids:{}", ids);
            throw new ParamRequestException("参数不合法");
        }
        channelService.deleteChannelByIds(channelIds);
    }
}
