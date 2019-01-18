package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TPPortalWrongQuestionParam;
import com.libiyi.exa.common.thrift.TRPortalWrongQuestionInfoList;

public interface PortalWrongQuestionService {

    TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(TPPortalWrongQuestionParam wrongQuestionParam);
}
