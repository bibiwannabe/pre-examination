package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TPPortalWrongQuestionParam;
import com.libiyi.exa.common.thrift.TPUserIdAndPageParam;
import com.libiyi.exa.common.thrift.TRPortalWrongQuestionInfoList;
import com.libiyi.exa.common.thrift.TRPraticeSubject;

public interface PortalWrongQuestionService {

    TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(TPPortalWrongQuestionParam wrongQuestionParam);

    TRPraticeSubject getSubjectQuestionCount(TPUserIdAndPageParam param);
}
