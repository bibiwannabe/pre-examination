package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPPortalQueryPaperInfo;
import com.libiyi.exa.common.thrift.TPortalPaperInfo;
import com.libiyi.exa.common.thrift.TRPortalPaperInfoList;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.service.PortalPaperPaperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortalPaperServiceImpl implements PortalPaperPaperService {
    static Logger logger = LogManager.getLogger(PortalPaperServiceImpl.class);

    @Autowired
    private PaperInfoMapper paperInfoMapper;

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

    private TPortalPaperInfo getTPortalPaperInfo(PaperInfo paperInfo) {
        TPortalPaperInfo tPortalPaperInfo = new TPortalPaperInfo();
        tPortalPaperInfo.setId(paperInfo.getId());
        tPortalPaperInfo.setName(paperInfo.getName());
        tPortalPaperInfo.setQuestionList(paperInfo.getQuestionList());
        tPortalPaperInfo.setSubjectId(paperInfo.getSubjectId());
        return tPortalPaperInfo;
    }
}
