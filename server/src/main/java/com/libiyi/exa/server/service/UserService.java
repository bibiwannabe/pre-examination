package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TPUserInfo;
import com.libiyi.exa.common.thrift.TRResponse;
import org.springframework.stereotype.Service;

public interface UserService {

    TRResponse userRegister(TPUserInfo tpUserInfo);

}
