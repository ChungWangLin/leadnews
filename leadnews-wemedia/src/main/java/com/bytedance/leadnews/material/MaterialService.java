package com.bytedance.leadnews.material;

import com.bytedance.leadnews.common.constant.MediaType;
import com.bytedance.leadnews.common.exception.CustomerException;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.WmMaterial;
import com.bytedance.leadnews.common.util.UserHolder;
import com.bytedance.leadnews.pojo.bo.QueryCondition;
import com.bytedance.leadnews.pojo.param.MaterialParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialDao materialDao;

    public void save(List<String> images) {
        Integer userId = UserHolder.getUserId();
        LocalDateTime now = LocalDateTime.now();

        List<WmMaterial> list = images.stream().map(e -> {
            WmMaterial item = new WmMaterial();
            item.setCollection(0);
            item.setCreatedTime(now);
            item.setUrl(e);
            item.setType(judgeType(e).getType());
            item.setUserId(userId);
            return item;
        }).collect(Collectors.toList());

        materialDao.save(list);

    }

    /**
     * 判断素材类型
     */
    public MediaType judgeType(String name){
        String type = name.substring(name.lastIndexOf(".")+1);
        if (type.equals("jpg") || type.equals("png") || type.equals("jpeg")) {
            return MediaType.PICTURE;
        }
        if (type.equals("mp4")) {
            return MediaType.VIDEO;
        }
        log.info("不支持的类型【type:{}】",type);
        throw new CustomerException("不支持的类型");
    }

    public PageInfo<WmMaterial> findByPage(Integer page, Integer size, QueryCondition condition) {
        Long starter = PageInfo.limit(page, size);
        Integer userId = UserHolder.getUserId();
        condition.setUserId(userId);
        List<WmMaterial> list = materialDao.findByPage(starter,size,condition);
        long count = materialDao.count(condition);
        return new PageInfo<WmMaterial>().init(page,size,count,list);
    }

    public void deleteByIds(List<Integer> ids) {
        Integer userId = UserHolder.getUserId();
        materialDao.deleteByIds(ids,userId);
    }

    public void collection(MaterialParam.Update param) {
        Integer userId = UserHolder.getUserId();
        param.setUserId(userId);
        materialDao.updateCollection(param);
    }
}
