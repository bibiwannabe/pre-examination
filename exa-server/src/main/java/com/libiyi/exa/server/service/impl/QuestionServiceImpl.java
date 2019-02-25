package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.QuestionTypeEnum;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.dao.QuestionInfoMapper;
import com.libiyi.exa.server.dto.QuestionModifyDto;
import com.libiyi.exa.server.entity.QuestionInfo;
import com.libiyi.exa.server.service.QuestionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    static Logger logger = LogManager.getLogger(QuestionServiceImpl.class);


    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    /**
     * 创建问题
     *
     * @param questionInfoParam
     * @return
     */
    @Override
    public TRIdResult createQuestion(TPAdminCreateQuestionInfo questionInfoParam) {
        TRIdResult trIdResult = new TRIdResult();
        TRResponse trResponse = new TRResponse();
        Integer id = 0;
        try {
            QuestionInfo questionInfo = getQuestionInfo(questionInfoParam);
            questionInfoMapper.addQuestionInfo(questionInfo);
            id = questionInfo.getId();
            logger.info("id:{}", questionInfo.getId());
        } catch (Exception e) {
            logger.error("创建题目失败,e:", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trResponse.setDesc(CodeEnum.UNKNOWN_ERROR.getDesc());
            trIdResult.setResponse(trResponse);
            return trIdResult;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trIdResult.setResponse(trResponse);
        trIdResult.setId(id);
        return trIdResult;
    }

    private QuestionInfo getQuestionInfo(TPAdminCreateQuestionInfo questionInfoParam) {
        QuestionInfo questionInfo = new QuestionInfo();
        questionInfo.setCreateUser(questionInfoParam.getCreateUser());
        questionInfo.setAnswer(questionInfoParam.getAnswer());
        questionInfo.setContent(questionInfoParam.getContent());
        if (questionInfoParam.getType() == QuestionTypeEnum.BLANK_FILLING.getCode().intValue()) {
            questionInfo.setOptions("");
        } else {
            questionInfo.setOptions(questionInfoParam.getOptions());
        }
        questionInfo.setType(questionInfoParam.getType());
        questionInfo.setCreateTime(DateUtil.getNow());
        questionInfo.setUpdateTime(DateUtil.getNow());
        questionInfo.setSubjectId(questionInfoParam.getSubjectId());
        return questionInfo;
    }

    /**
     * 获取问题列表
     *
     * @param queryParam
     * @return
     */
    @Override
    public TRAdminQuestionInfoList getQuestionListByParam(TPAdminQuerryQuestionInfo queryParam) {
        TRAdminQuestionInfoList trAdminQuestionInfoList = new TRAdminQuestionInfoList();
        TRResponse trResponse = new TRResponse();
        List<QuestionInfo> questionInfoList;
        Integer count;
        try {
            count = questionInfoMapper.getCountsByParam(queryParam.getSubjectId(), queryParam.getType());
            questionInfoList = questionInfoMapper.getByParam(queryParam.getSubjectId(), queryParam.getType(),
                    (queryParam.getPagination().getPage() - 1) * queryParam.getPagination().getSize(), queryParam.getPagination().getSize());
        } catch (Exception e) {
            logger.error("获取题目列表失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminQuestionInfoList.setResponse(trResponse);
            return trAdminQuestionInfoList;
        }
        List<TAdminQuestionInfo> tAdminQuestionInfo = questionInfoList.stream().map(this::getTAdminQuestionInfo).collect(Collectors.toList());
        trAdminQuestionInfoList.setAdminQuestionInfoList(tAdminQuestionInfo);
        queryParam.getPagination().setTotalNum(count);
        trAdminQuestionInfoList.setPagination(queryParam.getPagination());
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminQuestionInfoList.setResponse(trResponse);
        return trAdminQuestionInfoList;
    }

    private TAdminQuestionInfo getTAdminQuestionInfo(QuestionInfo questionInfo) {
        TAdminQuestionInfo tAdminQuestionInfo = new TAdminQuestionInfo();
        tAdminQuestionInfo.setId(questionInfo.getId());
        tAdminQuestionInfo.setAnswer(questionInfo.getAnswer());
        tAdminQuestionInfo.setContent(questionInfo.getContent());
        tAdminQuestionInfo.setCorrectNum(questionInfo.getCorrectNum());
        tAdminQuestionInfo.setCounts(questionInfo.getCounts());
        tAdminQuestionInfo.setCreateTime(questionInfo.getCreateTime());
        tAdminQuestionInfo.setUpdateTime(questionInfo.getUpdateTime());
        tAdminQuestionInfo.setCreateUser(questionInfo.getCreateUser());
        tAdminQuestionInfo.setSubjectId(questionInfo.getSubjectId());
        tAdminQuestionInfo.setType(questionInfo.getType());
        tAdminQuestionInfo.setOptions(questionInfo.getOptions());
        return tAdminQuestionInfo;
    }

    /**
     * 修改问题
     *
     * @param modifyParam
     * @return
     */
    @Override
    public TRResponse modifyQuestion(TPAdminModifyQuestionInfo modifyParam) {
        TRResponse trResponse = new TRResponse();
        QuestionModifyDto questionModifyDto = getQuestionModifyDto(modifyParam);
        try {
            questionInfoMapper.updateById(questionModifyDto);
        } catch (Exception e) {
            logger.error("修改题目失败 question:{}", JSON.toJSONString(questionModifyDto), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        return trResponse;
    }

    @Override
    public TRAdminQuestionInfo getQuestionById(Integer id) {
        TRAdminQuestionInfo trAdminQuestionInfo = new TRAdminQuestionInfo();
        TRResponse trResponse = new TRResponse();
        QuestionInfo questionInfo = null;
        try {
            questionInfo = questionInfoMapper.getById(id);
        } catch (Exception e) {
            logger.error("获取试题失败,id:{}", id, e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminQuestionInfo.setResponse(trResponse);
            return trAdminQuestionInfo;
        }
        if (questionInfo == null) {
            trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
            trAdminQuestionInfo.setResponse(trResponse);
            return trAdminQuestionInfo;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminQuestionInfo.setResponse(trResponse);
        TAdminQuestionInfo tAdminQuestionInfo = getTAdminQuestionInfo(questionInfo);
        trAdminQuestionInfo.setAdminQuestionInfo(tAdminQuestionInfo);
        return trAdminQuestionInfo;
    }

    @Override
    public TRAdminQuestionSearchList getQuestionListBySearchKey(TPAdminSearchQuestionParam tpAdminSearchQuestionParam) {
        TRAdminQuestionSearchList trAdminQuestionSearchList = new TRAdminQuestionSearchList();
        TRResponse trResponse = new TRResponse();
        List<QuestionInfo> questionInfoList;
        try {
            questionInfoList = questionInfoMapper.getBySearchKeywords(tpAdminSearchQuestionParam.getSubjectId(), tpAdminSearchQuestionParam.getType(), tpAdminSearchQuestionParam.getKeyWords());
        } catch (Exception e) {
            logger.error("查找列表失败，{}", JSON.toJSONString(tpAdminSearchQuestionParam));
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminQuestionSearchList.setResponse(trResponse);
            return trAdminQuestionSearchList;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        List<TAdminQuestionInfo> tAdminQuestionInfo = questionInfoList.stream().map(this::getTAdminQuestionInfo).collect(Collectors.toList());
        trAdminQuestionSearchList.setAdminQuestionInfoList(tAdminQuestionInfo);
        trAdminQuestionSearchList.setResponse(trResponse);
        return trAdminQuestionSearchList;
    }

    private QuestionModifyDto getQuestionModifyDto(TPAdminModifyQuestionInfo modifyParam) {
        QuestionModifyDto questionModifyDto = new QuestionModifyDto();
        questionModifyDto.setAnswer(modifyParam.getAnswer());
        questionModifyDto.setContent(modifyParam.getContent());
        questionModifyDto.setId(modifyParam.getId());
        questionModifyDto.setOptions(modifyParam.getOptions());
        questionModifyDto.setSubjectId(modifyParam.getSubjectId());
        questionModifyDto.setUpdateTime(DateUtil.getNow());
        return questionModifyDto;
    }
}
