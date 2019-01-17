package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.model.PaperQuestionListModel;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.dao.QuestionInfoMapper;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.entity.QuestionInfo;
import com.libiyi.exa.server.service.PortalPaperPaperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortalPaperServiceImpl implements PortalPaperPaperService {
    static Logger logger = LogManager.getLogger(PortalPaperServiceImpl.class);

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    @Override
    public TRPortalPaperInfoList getPaperListBySubjectId(TPPortalQueryPaperInfo queryPaperInfo) {
        TRPortalPaperInfoList trPortalPaperInfoList = new TRPortalPaperInfoList();
        TRResponse trResponse = new TRResponse();
        List<PaperInfo> paperInfoList;
        Integer counts;
        try {
            counts = paperInfoMapper.getCountsByParam(queryPaperInfo.getSubjectId());
            paperInfoList = paperInfoMapper.getByParam(queryPaperInfo.getSubjectId(),
                    (queryPaperInfo.getPagination().getPage()-1)*queryPaperInfo.getPagination().getSize(),
                    queryPaperInfo.getPagination().getSize());
        } catch (Exception e) {
            logger.error("前台获取试卷错误,{}", JSON.toJSONString(queryPaperInfo), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trPortalPaperInfoList.setResponse(trResponse);
            return trPortalPaperInfoList;
        }
        List<TPortalPaperInfo> tPortalPaperInfos = paperInfoList.stream().map(this::getTPortalPaperInfo).collect(Collectors.toList());
        queryPaperInfo.getPagination().setTotalNum(counts);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trPortalPaperInfoList.setResponse(trResponse);
        trPortalPaperInfoList.setPaperInfoList(tPortalPaperInfos);
        trPortalPaperInfoList.setPagination(queryPaperInfo.getPagination());
        return trPortalPaperInfoList;
    }

    @Override
    public TRPortalPaperAndQuestionInfo portalGetPaperById(int id) {
        TRPortalPaperAndQuestionInfo trPortalPaperAndQuestionInfo = new TRPortalPaperAndQuestionInfo();
        TRResponse response = new TRResponse();
        try {
            PaperInfo paperInfo = paperInfoMapper.getById(id);
            if(paperInfo == null) {
                response.setCode(CodeEnum.SUCCESS.getCode());
                trPortalPaperAndQuestionInfo.setResponse(response);
            }
            trPortalPaperAndQuestionInfo = getTRPortalPaperAndQuestionInfo(paperInfo);
            PaperQuestionListModel questionListModel = JSON.parseObject(paperInfo.getQuestionList(), PaperQuestionListModel.class);
            if(!CollectionUtils.isEmpty(questionListModel.getFilling().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getFilling().getIdList());
                TPortalQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTPortalQuestionPointsAndInfoList(questionListModel.getFilling().getPoint(), questionInfos);
                trPortalPaperAndQuestionInfo.setFilling(tQuestionPointsAndInfoList);
            }
            if(!CollectionUtils.isEmpty(questionListModel.getChoice().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getChoice().getIdList());
                TPortalQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTPortalQuestionPointsAndInfoList(questionListModel.getChoice().getPoint(), questionInfos);
                trPortalPaperAndQuestionInfo.setChoice(tQuestionPointsAndInfoList);
            }
            if(!CollectionUtils.isEmpty(questionListModel.getSelection().getIdList())) {
                List<QuestionInfo> questionInfos = questionInfoMapper.getByIdList(questionListModel.getSelection().getIdList());
                TPortalQuestionPointsAndInfoList tQuestionPointsAndInfoList = getTPortalQuestionPointsAndInfoList(questionListModel.getSelection().getPoint(), questionInfos);
                trPortalPaperAndQuestionInfo.setSelection(tQuestionPointsAndInfoList);
            }

        } catch (Exception e) {
            logger.error("获取试卷失败", e);
            response.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trPortalPaperAndQuestionInfo.setResponse(response);
        }
        response.setCode(CodeEnum.SUCCESS.getCode());
        trPortalPaperAndQuestionInfo.setResponse(response);
        return trPortalPaperAndQuestionInfo;
    }

    private TRPortalPaperAndQuestionInfo getTRPortalPaperAndQuestionInfo(PaperInfo paperInfo) {
        if(paperInfo == null) {
            return null;
        }
        TRPortalPaperAndQuestionInfo trPortalPaperAndQuestionInfo = new TRPortalPaperAndQuestionInfo();
        trPortalPaperAndQuestionInfo.setId(paperInfo.getId());
        trPortalPaperAndQuestionInfo.setName(paperInfo.getName());
        trPortalPaperAndQuestionInfo.setSubjectId(paperInfo.getSubjectId());
        return trPortalPaperAndQuestionInfo;
    }

    private TPortalQuestionPointsAndInfoList getTPortalQuestionPointsAndInfoList(Integer point, List<QuestionInfo> questionInfos) {
        TPortalQuestionPointsAndInfoList tQuestionPointsAndInfoList = new TPortalQuestionPointsAndInfoList();
        tQuestionPointsAndInfoList.setPoint(point);
        tQuestionPointsAndInfoList.setQuestionInfoList(questionInfos.stream().map(this::getTAdminQuestionInfo).collect(Collectors.toList()));
        return tQuestionPointsAndInfoList;
    }

    private TPortalQuestionInfo getTAdminQuestionInfo(QuestionInfo questionInfo) {
        TPortalQuestionInfo tPortalQuestionInfo = new TPortalQuestionInfo();
        tPortalQuestionInfo.setId(questionInfo.getId());
        tPortalQuestionInfo.setOptions(questionInfo.getOptions());
        tPortalQuestionInfo.setType(questionInfo.getType());
        tPortalQuestionInfo.setContent(questionInfo.getContent());
        tPortalQuestionInfo.setSubjectId(questionInfo.getSubjectId());
        return tPortalQuestionInfo;
    }

    private TPortalPaperInfo getTPortalPaperInfo(PaperInfo paperInfo) {
        TPortalPaperInfo tPortalPaperInfo = new TPortalPaperInfo();
        tPortalPaperInfo.setId(paperInfo.getId());
        tPortalPaperInfo.setName(paperInfo.getName());
        tPortalPaperInfo.setQuestionList(paperInfo.getQuestionList());
        tPortalPaperInfo.setSubjectId(paperInfo.getSubjectId());
        return tPortalPaperInfo;
    }


}
