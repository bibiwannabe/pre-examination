package com.libiyi.exa.server.server;

import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.TPUserInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

public class ServerServiceImpl implements ExaServerService.Iface {
    @Autowired
    private com.libiyi.exa.server.service.UserService userService;

    @Override
    public TRResponse userRegister(TPUserInfo userInfo) throws TException {
        return userService.userRegister(userInfo);
    }
}
