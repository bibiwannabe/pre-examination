package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

public interface AdminPaperService {

    TRResponse createPaper(TPAdminCreatePaperInfo paperInfo);

    TRResponse modifyPaper(TPAdminModifyPaperInfo modifyParam);

    TRAdminPaperInfoList getPaperListByParam(TPAdminQueryPaperInfo queryParam);

    TRAdminPaperAndQuestionInfo getPaperById(int id);
}
