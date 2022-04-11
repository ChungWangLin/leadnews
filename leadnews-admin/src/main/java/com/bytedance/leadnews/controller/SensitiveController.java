package com.bytedance.leadnews.controller;

import com.bytedance.leadnews.api.admin.SensitiveApi;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.AdSensitive;
import com.bytedance.leadnews.common.pojo.param.admin.SensitiveParam;
import com.bytedance.leadnews.exception.ParamRequestException;
import com.bytedance.leadnews.service.SensitiveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class SensitiveController implements SensitiveApi {
    private final SensitiveService sensitiveService;

    public SensitiveController(SensitiveService sensitiveService) {
        this.sensitiveService = sensitiveService;
    }

    @Override
    @GetMapping("/sensitive")
    public PageInfo<AdSensitive> getSensitiveByPage(@RequestParam Integer page, @RequestParam Integer size,
                                                    @RequestParam(required = false) String keyword) {
        if (page==null || page<1 || size==null || size<1) {
            throw new ParamRequestException("不合法参数");
        }
        return sensitiveService.findByPage(page, size, keyword);
    }

    @Override
    @PostMapping("/sensitive")
    public void create(@RequestBody @Validated SensitiveParam.Create param) {
        sensitiveService.createSensitive(param);
    }

    @Override
    @PutMapping("/sensitive")
    public void update(@RequestBody @Validated SensitiveParam.Update param) {
        sensitiveService.update(param);
    }

    @Override
    @DeleteMapping("/sensitive")
    public void delete(@RequestParam("ids") String ids) {
        String[] split = ids.split(",");

        List<Long> idList;
        try {
            idList = Arrays.stream(split).map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("错误的参数:{}",ids);
            throw new ParamRequestException("错误的参数！");
        }
        sensitiveService.batchDelete(idList);
    }
}
