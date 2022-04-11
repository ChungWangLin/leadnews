package com.bytedance.leadnews.wmuser;

import com.bytedance.leadnews.common.pojo.entity.WmUser;
import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WmUserService {
    private final WmUserDao wmUserDao;

    public WmUserService(WmUserDao wmUserDao) {
        this.wmUserDao = wmUserDao;
    }

    /**
     * 批量添加自媒体账户
     */
    public void batchCreateWmUser(List<WmUserParam.Create> params){
        List<WmUser> wmUsers = params.stream().map(e-> new WmUser().convertFromCreateParam(e)).collect(Collectors.toList());
        wmUserDao.batchInsert(wmUsers);
    }
}
