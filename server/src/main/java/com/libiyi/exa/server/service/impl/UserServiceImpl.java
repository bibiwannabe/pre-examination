package com.libiyi.exa.server.service.impl;

import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPUserInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.common.util.PasswordUtil;
import com.libiyi.exa.server.dao.UserInfoMapper;
import com.libiyi.exa.server.entity.UserInfo;
import com.libiyi.exa.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public TRResponse userRegister(TPUserInfo tpUserInfo) {
        TRResponse trResponse = new TRResponse();

        String password = PasswordUtil.MD5EncodeUtf8(tpUserInfo.getPassword());
        tpUserInfo.setPassword(password);
        UserInfo userInfo = getUserInfo(tpUserInfo);
        try {
            Integer id = userInfoMapper.insert(userInfo);
            if(id<1) {
                throw new Exception();
            }
        }catch (Exception e) {
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private UserInfo getUserInfo(TPUserInfo tpUserInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword(tpUserInfo.getPassword());
        userInfo.setPhone(tpUserInfo.getPhone());
        userInfo.setAccType(tpUserInfo.getAccType());
        userInfo.setCreateTime(DateUtil.getNow());
        userInfo.setUpdateTime(DateUtil.getNow());
        userInfo.setName(tpUserInfo.getName());
        return userInfo;
    }
}
