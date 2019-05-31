package com.libiyi.exa.server.service;

import com.libiyi.exa.common.thrift.*;

import java.util.List;

public interface QuestionService {
    /**
     * 创建问题
     * @param questionInfo
     * @return
     */
    TRIdResult createQuestion(TPAdminCreateQuestionInfo questionInfo);

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

    /**
     * 获取题目
     * @param id
     * @return
     */
    TRAdminQuestionInfo getQuestionById(Integer id);

    /**
     * 根据关键词搜索题目
     * @param tpAdminSearchQuestionParam
     * @return
     */
    TRAdminQuestionSearchList getQuestionListBySearchKey(TPAdminSearchQuestionParam tpAdminSearchQuestionParam);

    /**
     * 根据试卷id获取错误最多的试题
     * @param paperId
     * @return
     */
    TRPaperQuestionDataList getQuestionListByPaperId(Integer paperId);

    /**
     * 批量上传题目
     * @param list
     * @return
     */
    TRIdResult batchUploadQuestion(List<TPAdminCreateQuestionInfo> list);
}
