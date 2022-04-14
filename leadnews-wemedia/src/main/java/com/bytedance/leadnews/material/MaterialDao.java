package com.bytedance.leadnews.material;

import com.bytedance.leadnews.common.pojo.entity.WmMaterial;
import com.bytedance.leadnews.pojo.bo.MaterialCondition;
import com.bytedance.leadnews.pojo.param.MaterialParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaterialDao {
    void save(@Param("list") List<WmMaterial> list);

    List<WmMaterial> findByPage(@Param("starter") Long starter,@Param("size") Integer size, @Param("condition") MaterialCondition condition);

    long count(@Param("condition") MaterialCondition condition);

    void deleteByIds(@Param("ids") List<Integer> ids,@Param("userId") Integer userId);

    void updateCollection(@Param("param") MaterialParam.Update param);
}
