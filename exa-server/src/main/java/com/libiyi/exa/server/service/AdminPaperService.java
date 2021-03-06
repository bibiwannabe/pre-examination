package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

public interface AdminPaperService {

    TRIdResult createPaper(TPAdminCreatePaperInfo paperInfo);

    TRResponse modifyPaper(TPAdminModifyPaperInfo modifyParam);

    TRAdminPaperInfoList getPaperListByParam(TPAdminQueryPaperInfo queryParam);

    TRAdminPaperAndQuestionInfo getPaperById(int id);

    TRAdminPaperInfoList getAvgLowestFive(int subjectId);

    TRResponse addQuestionToPaper(TAddQuestionParam tAddQuestionParam);

    TRAdminPaperList getPaperListBySubject(int i);
}
