package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.model.PaperQuestionListModel;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.dao.QuestionInfoMapper;
import com.libiyi.exa.server.dto.PaperInfoModifyDto;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.entity.QuestionInfo;
import com.libiyi.exa.server.service.AdminPaperService;
import com.libiyi.exa.server.utils.ParserPaperQuestionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPaperServiceImpl implements AdminPaperService {
    static Logger logger = LogManager.getLogger(AdminPaperServiceImpl.class);


    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    @Override
    public TRIdResult createPaper(TPAdminCreatePaperInfo paperInfo) {
        TRIdResult trIdResult = new TRIdResult();
        TRResponse trResponse = new TRResponse();
        PaperInfo paperInfo1 = getPaperInfo(paperInfo);
        try {
            paperInfoMapper.createPaperInfo(paperInfo1);
        } catch (Exception e) {
            logger.error("创建试卷错误,{}", JSON.toJSONString(paperInfo1), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trIdResult.setResponse(trResponse);
            return trIdResult;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        trIdResult.setResponse(trResponse);
        trIdResult.setId(paperInfo1.getId());
        return trIdResult;
    }

    private PaperInfo getPaperInfo(TPAdminCreatePaperInfo createPaperInfo) {
        PaperInfo paperInfo = new PaperInfo();
        paperInfo.setQuestionList(createPaperInfo.getQuestionList());
        paperInfo.setCreateUser(createPaperInfo.getCreateUser());
        paperInfo.setName(createPaperInfo.getName());
        paperInfo.setCreateTime(DateUtil.getNow());
        paperInfo.setUpdateTime(DateUtil.getNow());
        paperInfo.setSubjectId(createPaperInfo.getSubjectId());
        return paperInfo;
    }

    @Override
    public TRResponse modifyPaper(TPAdminModifyPaperInfo modifyParam) {
        TRResponse trResponse = new TRResponse();
        PaperInfoModifyDto paperInfoModifyDto = getPaperInfoModifyDto(modifyParam);
        try {
            paperInfoMapper.updateById(paperInfoModifyDto);
        } catch (Exception e) {
            logger.error("修改试卷错误,{}", JSON.toJSONString(paperInfoModifyDto), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private PaperInfoModifyDto getPaperInfoModifyDto(TPAdminModifyPaperInfo modifyParam) {
        PaperInfoModifyDto paperInfoModifyDto = new PaperInfoModifyDto();
        paperInfoModifyDto.setId(modifyParam.getId());
        paperInfoModifyDto.setQuestionList(modifyParam.getQuestionList());
        paperInfoModifyDto.setName(modifyParam.getName());
        paperInfoModifyDto.setSubjectId(modifyParam.getSubjectId());
        paperInfoModifyDto.setUpdateTime(DateUtil.getNow());
        return paperInfoModifyDto;
    }

    @Override
    public TRAdminPaperInfoList getPaperListByParam(TPAdminQueryPaperInfo queryParam) {
        TRAdminPaperInfoList trAdminPaperInfoList = new TRAdminPaperInfoList();
        Integer counts;
        List<PaperInfo> paperInfoList;
        TRResponse trResponse = new TRResponse();
        try {
            counts = paperInfoMapper.getCountsByParam(queryParam.getSubjectId());
            paperInfoList = paperInfoMapper.getByParam(queryParam.getSubjectId(), (queryParam.getPagination().getPage() - 1) * queryParam.getPagination().getSize(),
                    queryParam.getPagination().getSize());
        } catch (Exception e) {
            logger.error("获取试卷错误,{}", JSON.toJSONString(queryParam), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminPaperInfoList.setResponse(trResponse);
            return trAdminPaperInfoList;
        }
        queryParam.getPagination().setTotalNum(counts);
        trAdminPaperInfoList.setPagination(queryParam.getPagination());
        List<TPaperInfo> tPaperInfoList = paperInfoList.stream().map(this::getTPaperInfo).collect(Collectors.toList());
        logger.info("获取到试卷列表：" + JSON.toJSONString(tPaperInfoList));
        trAdminPaperInfoList.setPaperInfoList(tPaperInfoList);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminPaperInfoList.setResponse(trResponse);
        return trAdminPaperInfoList;
    }

    @Override
    public TRAdminPaperAndQuestionInfo getPaperById(int id) {
        TRAdminPaperAndQuestionInfo trAdminPaperAndQuestionInfo = new TRAdminPaperAndQuestionInfo();
        TRResponse response = new TRResponse();
        try {
            PaperInfo paperInfo = paperInfoMapper.getById(id);
            if(paperInfo == null) {
                response.setCode(CodeEnum.SUCCESS.getCode());
                trAdminPaperAndQuestionInfo.setResponse(response);
            }
            trAdminPaperAndQuestionInfo = getTRAdminPaperAndQuestionInfo(paperInfo);
            logger.info("paperInfo.getQuestionList:{}",JSON.toJSONString(paperInfo.getQuestionList()));
            PaperQuestionListModel questionListModel = JSON.parseObject(paperInfo.getQuestionList(), PaperQuestionListModel.class);
            logger.info("PaperQuestionListModel:{}",JSON.toJSONString(questionListModel));
            if(!CollectionUtils.isEmpty(questionListModel.getFilling().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getFilling().getIdList());
                TQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTQuestionPointsAndInfoList(questionListModel.getFilling().getPoint(), questionInfos);
                trAdminPaperAndQuestionInfo.setFilling(tQuestionPointsAndInfoList);
            }
            if(!CollectionUtils.isEmpty(questionListModel.getChoice().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getChoice().getIdList());
                TQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTQuestionPointsAndInfoList(questionListModel.getChoice().getPoint(), questionInfos);
                trAdminPaperAndQuestionInfo.setChoice(tQuestionPointsAndInfoList);
            }
            if(!CollectionUtils.isEmpty(questionListModel.getSelection().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getSelection().getIdList());
                TQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTQuestionPointsAndInfoList(questionListModel.getSelection().getPoint(), questionInfos);
                trAdminPaperAndQuestionInfo.setSelection(tQuestionPointsAndInfoList);
            }

        } catch (Exception e) {
            logger.error("获取试卷失败", e);
            response.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminPaperAndQuestionInfo.setResponse(response);
        }
        response.setCode(CodeEnum.SUCCESS.getCode());
        trAdminPaperAndQuestionInfo.setResponse(response);
        return trAdminPaperAndQuestionInfo;
    }

    /**
     * 获取平均分最低的五个试卷
     * @param subjectId
     * @return
     */
    @Override
    public TRAdminPaperInfoList getAvgLowestFive(int subjectId) {
        TRAdminPaperInfoList trAdminPaperInfoList = new TRAdminPaperInfoList();
        TRResponse trResponse = new TRResponse();
        List<TPaperInfo> tPaperInfoList = new ArrayList<>();
        try {
            List<PaperInfo> paperInfoList = paperInfoMapper.getAvgLowestFive(subjectId);
            if (paperInfoList == null){
                trResponse.setCode(CodeEnum.SUCCESS.getCode());
                trAdminPaperInfoList.setPaperInfoList(tPaperInfoList);
                trAdminPaperInfoList.setResponse(trResponse);
                return trAdminPaperInfoList;
            }
            tPaperInfoList = paperInfoList.stream().map(this::getTPaperInfo).collect(Collectors.toList());
            trAdminPaperInfoList.setPaperInfoList(tPaperInfoList);
        } catch (Exception e) {
            logger.error("获取试卷列表失败,", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminPaperInfoList.setResponse(trResponse);
            return trAdminPaperInfoList;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminPaperInfoList.setResponse(trResponse);
        return trAdminPaperInfoList;
    }

    @Override
    public TRResponse addQuestionToPaper(TAddQuestionParam tAddQuestionParam) {
        TRResponse trResponse = new TRResponse();
        try {
            PaperInfo paperInfo = paperInfoMapper.getById(tAddQuestionParam.getPaperId());
            if(paperInfo == null) {
                trResponse.setCode(CodeEnum.DATA_ILEAGLE.getCode());
                return trResponse;
            }
            paperInfo.setQuestionList(ParserPaperQuestionUtil.addQuestion(tAddQuestionParam.getQuestionId(),tAddQuestionParam.getQuestionType(),paperInfo.getQuestionList()));
            PaperInfoModifyDto paperInfoModifyDto = getPaperInfoModifyDtoByPaperInfo(paperInfo);
            paperInfoMapper.updateById(paperInfoModifyDto);
        } catch (Exception e){
            logger.error("添加题目到试卷失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        return trResponse;
    }

    @Override
    public TRAdminPaperList getPaperListBySubject(int i) {
        TRResponse trResponse = new TRResponse();
        TRAdminPaperList trAdminPaperList = new TRAdminPaperList();
        List<PaperInfo> paperInfoList = new ArrayList<>();
        List<TPaperInfo> tPaperInfoList = new ArrayList<>();
        try{
            paperInfoList = paperInfoMapper.getListBySubjectId(i);
            if(CollectionUtils.isEmpty(paperInfoList)){
                trResponse.setCode(CodeEnum.SUCCESS.getCode());
                trAdminPaperList.setResponse(trResponse);
                return trAdminPaperList;
            }
            tPaperInfoList = paperInfoList.stream().map(this::getTPaperInfo).collect(Collectors.toList());
        }catch (Exception e) {
            logger.error("获取试卷列表失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trAdminPaperList.setResponse(trResponse);
            return trAdminPaperList;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminPaperList.setResponse(trResponse);
        trAdminPaperList.setPaperInfoList(tPaperInfoList);
        return trAdminPaperList;
    }

    private PaperInfoModifyDto getPaperInfoModifyDtoByPaperInfo(PaperInfo paperInfo) {
        PaperInfoModifyDto paperInfoModifyDto = new PaperInfoModifyDto();
        paperInfoModifyDto.setId(paperInfo.getId());
        paperInfoModifyDto.setName(paperInfo.getName());
        paperInfoModifyDto.setQuestionList(paperInfo.getQuestionList());
        paperInfoModifyDto.setSubjectId(paperInfo.getSubjectId());
        paperInfoModifyDto.setUpdateTime(DateUtil.getNow());
        return paperInfoModifyDto;
    }

    private TRAdminPaperAndQuestionInfo getTRAdminPaperAndQuestionInfo(PaperInfo paperInfo) {
        if(paperInfo == null) {
            return null;
        }
        TRAdminPaperAndQuestionInfo trAdminPaperAndQuestionInfo = new TRAdminPaperAndQuestionInfo();
        trAdminPaperAndQuestionInfo.setId(paperInfo.getId());
        trAdminPaperAndQuestionInfo.setName(paperInfo.getName());
        trAdminPaperAndQuestionInfo.setCreateUser(paperInfo.getCreateUser());
        trAdminPaperAndQuestionInfo.setSubjectId(paperInfo.getSubjectId());
        trAdminPaperAndQuestionInfo.setAvgPoints(paperInfo.getAvgPoints());
        trAdminPaperAndQuestionInfo.setCounts(paperInfo.getCounts());
        trAdminPaperAndQuestionInfo.setCreateTime(paperInfo.getCreateTime());
        trAdminPaperAndQuestionInfo.setUpdateTime(paperInfo.getUpdateTime());
        return trAdminPaperAndQuestionInfo;
    }

    private TQuestionPointsAndInfoList getTQuestionPointsAndInfoList(Integer point, List<QuestionInfo> questionInfos) {
        TQuestionPointsAndInfoList tQuestionPointsAndInfoList = new TQuestionPointsAndInfoList();
        tQuestionPointsAndInfoList.setPoint(point);
        tQuestionPointsAndInfoList.setQuestionInfoList(questionInfos.stream().map(this::getTAdminQuestionInfo).collect(Collectors.toList()));
        return tQuestionPointsAndInfoList;
    }

    private TAdminQuestionInfo getTAdminQuestionInfo(QuestionInfo questionInfo) {
        TAdminQuestionInfo tAdminQuestionInfo = new TAdminQuestionInfo();
        tAdminQuestionInfo.setId(questionInfo.getId());
        tAdminQuestionInfo.setOptions(questionInfo.getOptions());
        tAdminQuestionInfo.setType(questionInfo.getType());
        tAdminQuestionInfo.setSubjectId(questionInfo.getSubjectId());
        tAdminQuestionInfo.setCreateUser(questionInfo.getCreateUser());
        tAdminQuestionInfo.setUpdateTime(questionInfo.getUpdateTime());
        tAdminQuestionInfo.setCounts(questionInfo.getCounts());
        tAdminQuestionInfo.setAnswer(questionInfo.getAnswer());
        tAdminQuestionInfo.setCorrectNum(questionInfo.getCorrectNum());
        tAdminQuestionInfo.setContent(questionInfo.getContent());
        tAdminQuestionInfo.setType(questionInfo.getType());
        return tAdminQuestionInfo;
    }

    private TPaperInfo getTPaperInfo(PaperInfo paperInfo) {
        TPaperInfo tPaperInfo = new TPaperInfo();
        tPaperInfo.setId(paperInfo.getId());
        tPaperInfo.setAvgPoints(paperInfo.getAvgPoints());
        tPaperInfo.setCounts(paperInfo.getCounts());
        tPaperInfo.setCreateTime(paperInfo.getCreateTime());
        tPaperInfo.setCreateUser(paperInfo.getCreateUser());
        tPaperInfo.setName(paperInfo.getName());
        tPaperInfo.setQuestionList(paperInfo.getQuestionList());
        tPaperInfo.setUpdateTime(paperInfo.getUpdateTime());
        tPaperInfo.setSubjectId(paperInfo.getSubjectId());
        return tPaperInfo;
    }
}
