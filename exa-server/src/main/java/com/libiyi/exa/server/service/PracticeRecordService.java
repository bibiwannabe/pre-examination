package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TRPracticeRecord;

public interface PracticeRecordService {

    TRPracticeRecord getPracticeRecordByUserId(int userId);
}
