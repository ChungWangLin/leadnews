package com.bytedance.leadnews.user;

import com.bytedance.leadnews.common.pojo.entity.ApUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<ApUser> findByIds(List<Integer> ids){
        return userDao.findByIds(ids);
    }
}
