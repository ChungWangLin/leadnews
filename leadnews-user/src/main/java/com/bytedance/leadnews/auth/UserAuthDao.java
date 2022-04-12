package com.bytedance.leadnews.auth;

import com.bytedance.leadnews.bo.UserAuthQuery;
import com.bytedance.leadnews.bo.UserStatus;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserAuthDao {

    /**
     * 按条件分页查询用户审核信息
     */
    List<ApUserRealName> findByPage(@Param("start") Long start, @Param("size") Integer size, @Param("query") UserAuthQuery query);

    /**
     * 按条件获取总数
     */
    Long count(@Param("query") UserAuthQuery query);

    /**
     * 批量通过用户审核
     */
    void batchUpdateStatus(UserStatus userStatus);

    /**
     * 根据 realNameId 获取userId
     * @param ids 实名认证资料ids
     * @return userIds
     */
    List<Integer> findUserIdByRealNameId(@Param("ids") List<Integer> ids);
}
