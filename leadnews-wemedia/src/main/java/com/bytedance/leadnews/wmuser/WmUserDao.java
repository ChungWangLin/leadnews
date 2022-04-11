package com.bytedance.leadnews.wmuser;

import com.bytedance.leadnews.common.pojo.entity.WmUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmUserDao {
    /**
     * 批量创建自媒体账户
     */
    void batchInsert(@Param("WMUsers") List<WmUser> WMUsers);
}
