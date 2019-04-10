package com.libiyi.exa.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.portal.api.param.EvaluateResultModel;
import com.libiyi.exa.portal.api.param.PracticeRecordModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/practice")
public class PracticeRecordController {
    static Logger logger = LogManager.getLogger(PracticeRecordController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @Autowired
    private PaperInfoController paperInfoController;

    @GetMapping("/list")
    @ResponseBody
    public Result<List<PracticeRecordModel>> getPracticeRecordList(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<List<PracticeRecordModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TRPracticeRecord trPracticeRecord;
        try {
            trPracticeRecord = exaServerService.getPracticeRecordByUserId(userId);
            if (trPracticeRecord.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<List<PracticeRecordModel>>().setCode(trPracticeRecord.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<List<PracticeRecordModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        List<PracticeRecordModel> practiceRecordModelList = trPracticeRecord.getPracticeInfo().stream().map(this::getPracticeRecordModel).collect(Collectors.toList());
        return new Result.Builder<List<PracticeRecordModel>>(practiceRecordModelList).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private PracticeRecordModel getPracticeRecordModel(TPracticeRecordAndPaperInfo tPracticeRecordAndPaperInfo) {
        PracticeRecordModel practiceRecordModel = new PracticeRecordModel();
        practiceRecordModel.setAvgPoints(tPracticeRecordAndPaperInfo.getAvgCounts());
        practiceRecordModel.setPaperId(tPracticeRecordAndPaperInfo.getPaperId());
        practiceRecordModel.setCounts(tPracticeRecordAndPaperInfo.getCounts());
        practiceRecordModel.setSubjectId(tPracticeRecordAndPaperInfo.getSubjectId());
        practiceRecordModel.setPaperName(tPracticeRecordAndPaperInfo.getPaperName());
        practiceRecordModel.setPracticeId(tPracticeRecordAndPaperInfo.getRecordId());
        return practiceRecordModel;
    }


    @GetMapping("/{practiceId}")
    @ResponseBody
    public Result<EvaluateResultModel> getPracticeInfo(@PathVariable("practiceId")Integer praticeId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<EvaluateResultModel>().setCode(CodeEnum.NO_LOGIN.getCode()).build();
        }
        TREvaluateResult trEvaluateResult;
        try {
            trEvaluateResult = exaServerService.getEvaluateResultById(praticeId);
            if (trEvaluateResult.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<EvaluateResultModel>().setCode(trEvaluateResult.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<EvaluateResultModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        EvaluateResultModel evaluateResultModel = paperInfoController.getEvaluateResultModel(trEvaluateResult);
        return new Result.Builder<EvaluateResultModel>(evaluateResultModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

}
