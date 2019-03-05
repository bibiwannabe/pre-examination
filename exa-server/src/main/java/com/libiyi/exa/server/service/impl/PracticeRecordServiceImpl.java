package com.libiyi.exa.server.service.impl;

import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.thrift.TPracticeRecordAndPaperInfo;
import com.libiyi.exa.common.thrift.TRPracticeRecord;
import com.libiyi.exa.common.thrift.TRResponse;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.dao.PracticeRecordMapper;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.entity.PracticeRecord;
import com.libiyi.exa.server.service.PracticeRecordService;
import com.libiyi.exa.server.thriftServer.ServerServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PracticeRecordServiceImpl implements PracticeRecordService {
    static Logger logger = LogManager.getLogger(PracticeRecordServiceImpl.class);

    @Autowired
    private PracticeRecordMapper practiceRecordMapper;

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    /**
     * 获取用户最近五条练习记录
     *
     * @param userId
     * @return
     */
    @Override
    public TRPracticeRecord getPracticeRecordByUserId(int userId) {
        TRPracticeRecord trPracticeRecord = new TRPracticeRecord();
        TRResponse trResponse = new TRResponse();
        try {
            List<PracticeRecord> practiceRecordList = practiceRecordMapper.getRecentFive(userId);
            if(CollectionUtils.isEmpty(practiceRecordList)) {
                trResponse.setCode(CodeEnum.SUCCESS.getCode());
                trPracticeRecord.setResponse(trResponse);
                return trPracticeRecord;
            }
            List<TPracticeRecordAndPaperInfo> tPracticeRecordAndPaperInfos = practiceRecordList.stream().map(this::getTPracticeRecordAndPaperInfo).collect(Collectors.toList());
            trPracticeRecord.setPracticeInfo(tPracticeRecordAndPaperInfos);
        } catch (Exception e) {
            logger.error("获取用户记录失败:", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trPracticeRecord.setResponse(trResponse);
            return trPracticeRecord;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trPracticeRecord.setResponse(trResponse);
        return trPracticeRecord;
    }

    private TPracticeRecordAndPaperInfo getTPracticeRecordAndPaperInfo(PracticeRecord practiceRecord) {
        TPracticeRecordAndPaperInfo tPracticeRecordAndPaperInfo = new TPracticeRecordAndPaperInfo();
        PaperInfo paperInfo;
        try {
            paperInfo = paperInfoMapper.getById(practiceRecord.getPaperId());
        } catch (Exception e) {
            logger.error("查找试卷失败， paperId:{}", practiceRecord.getPaperId(), e);
            return tPracticeRecordAndPaperInfo;
        }
        tPracticeRecordAndPaperInfo.setPaperName(paperInfo.getName());
        tPracticeRecordAndPaperInfo.setRecordId(practiceRecord.getId());
        tPracticeRecordAndPaperInfo.setCounts(paperInfo.getCounts());
        tPracticeRecordAndPaperInfo.setAvgCounts(paperInfo.getAvgPoints());
        tPracticeRecordAndPaperInfo.setSubjectId(paperInfo.getSubjectId());
        tPracticeRecordAndPaperInfo.setPaperId(paperInfo.getId());
        return tPracticeRecordAndPaperInfo;
    }
}
