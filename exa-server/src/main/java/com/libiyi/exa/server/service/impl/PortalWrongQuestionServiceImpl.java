package com.libiyi.exa.server.service.impl;

import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPPortalWrongQuestionParam;
import com.libiyi.exa.common.thrift.TPortalWrongQuestionInfo;
import com.libiyi.exa.common.thrift.TRPortalWrongQuestionInfoList;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.server.dao.QuestionInfoMapper;
import com.libiyi.exa.server.dao.WrongRecordMapper;
import com.libiyi.exa.server.entity.QuestionInfo;
import com.libiyi.exa.server.entity.WrongRecord;
import com.libiyi.exa.server.service.PortalWrongQuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PortalWrongQuestionServiceImpl implements PortalWrongQuestionService {
    static Logger logger = LogManager.getLogger(PortalWrongQuestionServiceImpl.class);

    @Autowired
    private WrongRecordMapper wrongRecordMapper;

    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    @Override
    public TRPortalWrongQuestionInfoList getWrongQuestionBySubjectId(TPPortalWrongQuestionParam wrongQuestionParam) {
        TRPortalWrongQuestionInfoList trPortalWrongQuestionInfoList = new TRPortalWrongQuestionInfoList();
        TRResponse trResponse = new TRResponse();
        List<QuestionInfo> questionInfos = null;
        Integer count = 0;
        List<Integer> idList = new ArrayList<>();
        List<WrongRecord> wrongRecordList = new ArrayList<>();
        try {
            count = wrongRecordMapper.getCountByUserAndSubjectId(wrongQuestionParam.getUserId(),
                    wrongQuestionParam.getSubjectId());
            logger.info(count);
            wrongRecordList = wrongRecordMapper.getListByUserAndSubjectId(wrongQuestionParam.getUserId(),
                    wrongQuestionParam.getSubjectId(), (wrongQuestionParam.getPagination().getPage() - 1) * wrongQuestionParam.getPagination().getSize(),
                    wrongQuestionParam.getPagination().getSize());
            wrongRecordList.forEach(r -> idList.add(r.getQuestionId()));
            if (!CollectionUtils.isEmpty(idList)) {
                questionInfos = questionInfoMapper.getByIdList(idList);
            }

            wrongQuestionParam.getPagination().setTotalNum(count);
            trPortalWrongQuestionInfoList.setPagination(wrongQuestionParam.getPagination());
            trResponse.setCode(CodeEnum.SUCCESS.getCode());
            trPortalWrongQuestionInfoList.setResponse(trResponse);
            if (questionInfos != null) {
                Map<Integer, Integer> wrongRecordMap = new HashMap<>();
                wrongRecordList.forEach(wrongRecord -> wrongRecordMap.put(wrongRecord.getQuestionId(), wrongRecord.getWrongCounts()));
                List<TPortalWrongQuestionInfo> tPortalWrongQuestionInfos = new ArrayList<>();
                for (QuestionInfo q : questionInfos) {
                    tPortalWrongQuestionInfos.add(getTPortalWrongQuestionInfo(q, wrongRecordMap));
                }
                trPortalWrongQuestionInfoList.setQuestionInfoList(tPortalWrongQuestionInfos);
            }
        } catch (Exception e) {
            logger.error("获取错题信息失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trPortalWrongQuestionInfoList.setResponse(trResponse);
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trPortalWrongQuestionInfoList.setResponse(trResponse);
        wrongQuestionParam.getPagination().setTotalNum(count);
        trPortalWrongQuestionInfoList.setPagination(wrongQuestionParam.getPagination());
        return trPortalWrongQuestionInfoList;
    }

    private TPortalWrongQuestionInfo getTPortalWrongQuestionInfo(QuestionInfo questionInfo, Map<Integer, Integer> wrongRecordMap) {
        TPortalWrongQuestionInfo tPortalWrongQuestionInfo = new TPortalWrongQuestionInfo();
        tPortalWrongQuestionInfo.setId(questionInfo.getId());
        tPortalWrongQuestionInfo.setSubjectId(questionInfo.getSubjectId());
        tPortalWrongQuestionInfo.setType(questionInfo.getType());
        tPortalWrongQuestionInfo.setWrongCounts(wrongRecordMap.get(questionInfo.getId()));
        tPortalWrongQuestionInfo.setAnswer(questionInfo.getAnswer());
        tPortalWrongQuestionInfo.setContent(questionInfo.getContent());
        tPortalWrongQuestionInfo.setOptions(questionInfo.getOptions());
        return tPortalWrongQuestionInfo;
    }
}
