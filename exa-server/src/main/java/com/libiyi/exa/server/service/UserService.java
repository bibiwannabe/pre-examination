package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

public interface UserService {

    TRResponse userRegister(TPUserRegisterInfo tpUserInfo);

    TRResponse checkEmailExist(String email);

    TRUserLoginInfo userLogin(TPUserLoginInfo userLoginInfo);

    TRResponse sendEmail(String email);

    TRUserLoginInfo getUserInfo(Integer userId);

    TRResponse updateUsername(String name, Integer id);
}
