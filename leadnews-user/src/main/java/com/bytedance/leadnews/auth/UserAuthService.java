package com.bytedance.leadnews.auth;

import com.bytedance.leadnews.api.article.ApAuthorApi;
import com.bytedance.leadnews.api.wemedia.WmUserApi;
import com.bytedance.leadnews.bo.UserAuthQuery;
import com.bytedance.leadnews.bo.UserStatus;
import com.bytedance.leadnews.common.constant.ApAuthorType;
import com.bytedance.leadnews.common.constant.UserAuthStatus;
import com.bytedance.leadnews.common.pojo.dto.PageInfo;
import com.bytedance.leadnews.common.pojo.entity.ApUser;
import com.bytedance.leadnews.common.pojo.entity.ApUserRealName;
import com.bytedance.leadnews.common.pojo.entity.WmUser;
import com.bytedance.leadnews.common.pojo.param.article.ApAuthorParam;
import com.bytedance.leadnews.common.pojo.param.user.UserParam;
import com.bytedance.leadnews.common.pojo.param.wemedia.WmUserParam;
import com.bytedance.leadnews.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthService {
    private final UserAuthDao userAuthDao;
    private final UserService userService;
    private final WmUserApi wmUserApi;
    private final ApAuthorApi apAuthorApi;

    /**
     * 根据条件获取用户审核数据
     */
    public PageInfo<ApUserRealName> findByPage(Integer page, Integer size, UserAuthQuery query) {
        Long start = PageInfo.limit(page, size);
        List<ApUserRealName> list = userAuthDao.findByPage(start,size,query);
        Long total = userAuthDao.count(query);
        return new PageInfo<ApUserRealName>().init(page,size,total,list);
    }

    /**
     * 批量更改用户状态
     */
    public void batchUpdateStatus(UserParam.Status status) {
        UserStatus userStatus = new UserStatus().convertFromStatusParam(status);
        userAuthDao.batchUpdateStatus(userStatus);

        // 如果状态是通过的，就创建自媒体账户
        if (UserAuthStatus.PASS.getCode().equals(status.getStatus())) {
            log.info("进行用户实名认证");
            List<ApUser> apUsers = findUserApByIds(status.getIds());
            // 批量创建自媒体账户
            List<WmUser> wmUsers = batchCreateWmUser(apUsers);

            // 批量创建作者
            batchCrateAuthor(apUsers,wmUsers);
        }
    }

    /**
     * 根据realNameIds 获取ApUserList
     * @param realNameIds 实名认证Ids
     */
    public List<ApUser> findUserApByIds(List<Integer> realNameIds) {
        // 获取userIds
        List<Integer> userIds = userAuthDao.findUserIdByRealNameId(realNameIds);
        // 根据userIDs获取apUser
        return userService.findByIds(userIds);
    }

    /**
     * 批量创建自媒体账户
     */
    private List<WmUser> batchCreateWmUser(List<ApUser> apUsers) {
        // 转换为WmUserParam,调用远程插入
        List<WmUserParam.Create> params = apUsers.stream().map(e->{
            WmUserParam.Create param = new WmUserParam.Create();
            BeanUtils.copyProperties(e,param);
            param.setApUserId(e.getId());
            return param;
        }).collect(Collectors.toList());

        WmUserParam.UserInfo userInfo = new WmUserParam.UserInfo();
        userInfo.setUserInfo(params);
        return wmUserApi.createWmUser(userInfo);
    }


    private Map<Integer,Integer> assemblyWmAndUserMap(List<WmUser> wmUsers) {
        Map<Integer,Integer> map = new HashMap<>();
        for (WmUser wmuser : wmUsers) {
            map.put(wmuser.getApUserId(),wmuser.getId());
        }
        return map;
    }

    /**
     * 批量创建作者
     */
    private void batchCrateAuthor(List<ApUser> apUsers, List<WmUser> wmUsers) {
        Map<Integer, Integer> map = assemblyWmAndUserMap(wmUsers);

        ApAuthorParam.CreateParams params = new ApAuthorParam.CreateParams();
        List<ApAuthorParam.Create> list = new ArrayList<>();
        ApAuthorParam.Create create;
        for (ApUser apUser : apUsers) {
            create = new ApAuthorParam.Create();
            create.setUserId(apUser.getId());
            create.setWmUserId(map.get(apUser.getId()));
            create.setName(apUser.getName());
            create.setType(ApAuthorType.WE_MEDIA);
            list.add(create);
        }
        params.setParams(list);
        apAuthorApi.createApAuthor(params);
    }
}
