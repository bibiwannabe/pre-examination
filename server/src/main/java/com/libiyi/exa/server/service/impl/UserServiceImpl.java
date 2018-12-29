package com.libiyi.exa.server.service.impl;

import com.libiyi.exa.common.thrift.TPUserInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.server.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public TRResponse userRegister(TPUserInfo tpUserInfo) {
        return null;
    }
}
