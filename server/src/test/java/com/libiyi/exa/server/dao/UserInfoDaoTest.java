package com.libiyi.exa.server.dao;

import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.BaseTest;
import com.libiyi.exa.server.entity.UserInfo;
import org.junit.Test;

import javax.annotation.Resource;


public class UserInfoDaoTest extends BaseTest {
    @Resource
    private UserInfoMapper userInfoMapper;

    @Test
    public void insert(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("libiyi");
        userInfo.setPassword("libiyi");
        userInfo.setCreateTime(DateUtil.getNow());
        userInfo.setUpdateTime(DateUtil.getNow());
        userInfo.setPhone(18276416684l);
        userInfoMapper.insert(userInfo);

    }
}
