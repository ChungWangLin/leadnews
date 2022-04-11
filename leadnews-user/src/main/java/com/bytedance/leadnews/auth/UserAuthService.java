package com.bytedance.leadnews.auth;

import com.bytedance.leadnews.bo.UserAuthQuery;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthService {
    private final UserAuthDao userAuthDao;

    public UserAuthService(UserAuthDao userAuthDao) {
        this.userAuthDao = userAuthDao;
    }

    public PageInfo<ApUserRealName> findByPage(Integer page, Integer size, UserAuthQuery query) {
        Long start = PageInfo.limit(page, size);
        List<ApUserRealName> list = userAuthDao.findByPage(start,size,query);
        Long total = userAuthDao.count(query);
        return new PageInfo<ApUserRealName>().init(page,size,total,list);
    }
}
