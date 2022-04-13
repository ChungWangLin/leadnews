package com.bytedance.leadnews.material;

import com.bytedance.leadnews.common.exception.ParamRequestException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.WmMaterial;
import com.bytedance.leadnews.pojo.bo.QueryCondition;
import com.bytedance.leadnews.pojo.param.MaterialParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    /**
     * 批量添加素材
     */
    @PostMapping("/vm/material")
    public void create(@RequestBody List<String> images) {
        if (CollectionUtils.isEmpty(images)) {
            return;
        }
        materialService.save(images);
    }

    /**
     * 分页获取素材
     */
    @GetMapping("/vm/material")
    public PageInfo<WmMaterial> findByPage(@RequestParam Integer page, @RequestParam Integer size,
                                           @RequestParam(required = false)Integer collection) {
        PageInfo.checkedPage(page,size);
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setCollection(collection);
        return materialService.findByPage(page,size,queryCondition);
    }

    /**
     * 批量删除素材
     */
    @DeleteMapping("/vm/material")
    public void deleteByIds(@RequestParam String ids) {
        List<Integer> materialIds = new ArrayList<>();
        String[] idsStr = ids.split(",");
        try {
            materialIds = Arrays.stream(idsStr).map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ParamRequestException("参数不合法");
        }
        materialService.deleteByIds(materialIds);
    }

    /**
     * 更新素材
     */
    @PutMapping("/vm/material")
    public void collection(@RequestBody @Validated MaterialParam.Update param) {
        materialService.collection(param);
    }
}
