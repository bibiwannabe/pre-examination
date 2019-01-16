package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

public interface QuestionService {
    /**
     * 创建问题
     * @param questionInfo
     * @return
     */
    TRResponse createQuestion(TPAdminCreateQuestionInfo questionInfo);

    /**
     * 获取题目列表
     * @param queryParam
     * @return
     */
    TRAdminQuestionInfoList getQuestionListByParam(TPAdminQuerryQuestionInfo queryParam);

    /**
     * 修改题目
     * @param modifyParam
     * @return
     */
    TRResponse modifyQuestion(TPAdminModifyQuestionInfo modifyParam);
}
