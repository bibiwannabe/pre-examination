package com.libiyi.exa.server.thriftServer;

import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.server.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl implements ExaServerService.Iface {
    static Logger logger = LogManager.getLogger(ServerServiceImpl.class);

    @Autowired
    private UserService userService;

    @Override
    public TRResponse userRegister(TPUserRegisterInfo userInfo) throws TException {
        return userService.userRegister(userInfo);
    }

    @Override
    public TRResponse checkEmailExist(String email) throws TException {
        return userService.checkEmailExist(email);
    }

    @Override
    public TRUserLoginInfo userLogin(TPUserLoginInfo userLoginInfo) throws TException {
        return userService.userLogin(userLoginInfo);
    }

    @Override
    public TRUserLoginInfo teacherLogin(TPUserLoginInfo userLoginInfo) throws TException {
        return userService.teacherLogin(userLoginInfo);
    }

    @Override
    public TRResponse sendEmail(String email) throws TException {
        return userService.sendEmail(email);
    }
}
