package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

public interface PortalPaperPaperService {

    TRPortalPaperInfoList getPaperListBySubjectId(TPPortalQueryPaperInfo queryPaperInfo);

    TRPortalPaperAndQuestionInfo portalGetPaperById(int id);

    TREvaluateResult updateEvaluateResult(TPCreatPracticeRecordParam practiceParam);

    TRPortalPaperInfoList getRecommendPaperList(int userId);
}
