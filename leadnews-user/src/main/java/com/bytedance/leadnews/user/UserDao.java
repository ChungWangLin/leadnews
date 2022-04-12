package com.bytedance.leadnews.user;

import com.bytedance.leadnews.common.pojo.entity.ApUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<ApUser> findByIds(@Param("ids") List<Integer> ids);
}
