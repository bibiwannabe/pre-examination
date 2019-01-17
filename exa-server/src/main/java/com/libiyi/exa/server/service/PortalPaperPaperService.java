package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.TPPortalQueryPaperInfo;
import com.libiyi.exa.common.thrift.TRPortalPaperAndQuestionInfo;
import com.libiyi.exa.common.thrift.TRPortalPaperInfoList;

public interface PortalPaperPaperService {

    TRPortalPaperInfoList getPaperListBySubjectId(TPPortalQueryPaperInfo queryPaperInfo);

    TRPortalPaperAndQuestionInfo portalGetPaperById(int id);
}
