package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.dto.PaperInfoModifyDto;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.service.AdminPaperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminPaperServiceImpl implements AdminPaperService {
    static Logger logger = LogManager.getLogger(AdminPaperServiceImpl.class);


    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Override
    public TRResponse createPaper(TPAdminCreatePaperInfo paperInfo) {
        TRResponse trResponse = new TRResponse();
        PaperInfo paperInfo1 = getPaperInfo(paperInfo);
        try {
            paperInfoMapper.createPaperInfo(paperInfo1);
        } catch (Exception e) {
            logger.error("创建试卷错误,{}", JSON.toJSONString(paperInfo1), e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            return trResponse;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trResponse.setDesc(CodeEnum.SUCCESS.getDesc());
        return trResponse;
    }

    private PaperInfo getPaperInfo(TPAdminCreatePaperInfo createPaperInfo) {
        PaperInfo paperInfo = new PaperInfo();
        paperInfo.setQuestionList(createPaperInfo.getQuestionList());
        paperInfo.setCreateUser(createPaperInfo.getCreateUser());
        paperInfo.setName(createPaperInfo.getName());
        paperInfo.setCreateTime(DateUtil.getNow());
        paperInfo.setUpdateTime(DateUtil.getNow());
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
        paperInfoModifyDto.setSubjectId(modifyParam.getSubjectId());
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
        trAdminPaperInfoList.setPaperInfoList(tPaperInfoList);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trAdminPaperInfoList.setResponse(trResponse);
        return trAdminPaperInfoList;
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
        return tPaperInfo;
    }
}
