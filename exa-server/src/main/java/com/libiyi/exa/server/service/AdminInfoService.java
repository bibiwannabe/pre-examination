package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TPAdminInfo;
import com.libiyi.exa.common.thrift.TRAdminInfo;
import com.libiyi.exa.common.thrift.TRResponse;

public interface AdminInfoService {
    
    TRResponse createAdminInfo(TPAdminInfo adminInfo);

    TRAdminInfo getAdminInfo(Integer userId);
}
