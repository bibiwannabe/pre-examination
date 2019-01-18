package com.libiyi.exa.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.portal.api.param.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paper")
public class PaperInfoController {
    static Logger logger = LogManager.getLogger(PaperInfoController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @GetMapping("/list")
    @ResponseBody
    public Result<PaperListModel> getPaperList(@RequestParam("subjectId") int subjectId,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                               HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<PaperListModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminQueryPaperInfo queryPaperInfo = getTPPortalQueryPaperInfo(page, size, subjectId);
        TRAdminPaperInfoList trAdminPaperInfoList;
        try {
            trAdminPaperInfoList = exaServerService.getPaperListByParam(queryPaperInfo);
            if (trAdminPaperInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        PaperListModel paperListModel = getPaperListModel(trAdminPaperInfoList);
        return new Result.Builder<PaperListModel>(paperListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminQueryPaperInfo getTPPortalQueryPaperInfo(int page, int size, int subjectId) {
        TPAdminQueryPaperInfo tpPortalQueryPaperInfo = new TPAdminQueryPaperInfo();
        tpPortalQueryPaperInfo.setSubjectId(subjectId);
        TPagination tPagination = new TPagination();
        tPagination.setSize(size);
        tPagination.setPage(page);
        tpPortalQueryPaperInfo.setPagination(tPagination);
        return tpPortalQueryPaperInfo;
    }

    private PaperListModel getPaperListModel(TRAdminPaperInfoList trAdminPaperInfoList) {
        PaperListModel paperListModel = new PaperListModel();
        List<PaperModel> paperModelList = trAdminPaperInfoList.getPaperInfoList().stream().map(this::getPaperModel).collect(Collectors.toList());
        paperListModel.setPaperList(paperModelList);
        paperListModel.setPage(trAdminPaperInfoList.getPagination().getPage());
        paperListModel.setSize(trAdminPaperInfoList.getPagination().getSize());
        paperListModel.setTotal(trAdminPaperInfoList.getPagination().getTotalNum());
        return paperListModel;
    }

    private PaperModel getPaperModel(TPaperInfo tPaperInfo) {
        PaperModel paperModel = new PaperModel();
        paperModel.setName(tPaperInfo.getName());
        paperModel.setId(tPaperInfo.getId());
        paperModel.setSubjectId(tPaperInfo.getSubjectId());
        return paperModel;
    }

    @GetMapping("/{paperId}")
    @ResponseBody
    public Result<PaperAndQuestionInfoModel> getPaperList(@PathVariable("paperId") int paperId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<PaperAndQuestionInfoModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRPortalPaperAndQuestionInfo trPortalPaperAndQuestionInfo;
        try {
            trPortalPaperAndQuestionInfo = exaServerService.portalGetPaperById(paperId);
            if (trPortalPaperAndQuestionInfo.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<PaperAndQuestionInfoModel>().setCode(trPortalPaperAndQuestionInfo.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<PaperAndQuestionInfoModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        PaperAndQuestionInfoModel paperAndQuestionInfoModel = getPaperAndQuestionInfoModel(trPortalPaperAndQuestionInfo);
        return new Result.Builder<PaperAndQuestionInfoModel>(paperAndQuestionInfoModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private PaperAndQuestionInfoModel getPaperAndQuestionInfoModel(TRPortalPaperAndQuestionInfo trPortalPaperAndQuestionInfo) {
        PaperAndQuestionInfoModel paperAndQuestionInfoModel = new PaperAndQuestionInfoModel();
        paperAndQuestionInfoModel.setId(trPortalPaperAndQuestionInfo.getId());
        paperAndQuestionInfoModel.setName(trPortalPaperAndQuestionInfo.getName());
        paperAndQuestionInfoModel.setSubjectId(trPortalPaperAndQuestionInfo.getSubjectId());
        if (trPortalPaperAndQuestionInfo.getChoice() != null) {
            paperAndQuestionInfoModel.setChoice(getQuestionInfoListAndPoints(trPortalPaperAndQuestionInfo.getChoice()));
        }
        if (trPortalPaperAndQuestionInfo.getSelection() != null) {
            paperAndQuestionInfoModel.setSelection(getQuestionInfoListAndPoints(trPortalPaperAndQuestionInfo.getSelection()));
        }
        if (trPortalPaperAndQuestionInfo.getFilling() != null) {
            paperAndQuestionInfoModel.setFilling(getQuestionInfoListAndPoints(trPortalPaperAndQuestionInfo.getFilling()));
        }
        return paperAndQuestionInfoModel;
    }

    private QuestionInfoListAndPoints getQuestionInfoListAndPoints(TPortalQuestionPointsAndInfoList tQuestionPointsAndInfoList) {
        QuestionInfoListAndPoints questionInfoListAndPoints = new QuestionInfoListAndPoints();
        questionInfoListAndPoints.setPoint(tQuestionPointsAndInfoList.getPoint());
        questionInfoListAndPoints.setQuestionList(tQuestionPointsAndInfoList.getQuestionInfoList().stream().map(this::getQuestionModel).collect(Collectors.toList()));
        return questionInfoListAndPoints;
    }

    private QuestionModel getQuestionModel(TPortalQuestionInfo tPortalQuestionInfo) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(tPortalQuestionInfo.getId());
        questionModel.setSubjectId(tPortalQuestionInfo.getSubjectId());
        questionModel.setOptions(tPortalQuestionInfo.getOptions());
        questionModel.setType(tPortalQuestionInfo.getType());
        questionModel.setContent(tPortalQuestionInfo.getContent());
        return questionModel;
    }

    @PostMapping("/practice")
    @ResponseBody
    public Result<EvaluateResultModel> getPracticeRecord(@RequestBody PracticeModel practiceModel, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<EvaluateResultModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPCreatPracticeRecordParam tpCreatPracticeRecordParam = getTPCreatPracticeRecordParam(practiceModel, trUserLoginInfo.getId());
        TREvaluateResult trEvaluateResult;
        try {
            trEvaluateResult = exaServerService.getEvaluateResult(tpCreatPracticeRecordParam);
            if (trEvaluateResult.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<EvaluateResultModel>().setCode(trEvaluateResult.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<EvaluateResultModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        EvaluateResultModel evaluateResultModel = getEvaluateResultModel(trEvaluateResult);
        return new Result.Builder<EvaluateResultModel>(evaluateResultModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private EvaluateResultModel getEvaluateResultModel(TREvaluateResult trEvaluateResult) {
        TPortalPaperAndQuestion tPortalPaperAndQuestion = trEvaluateResult.getTPortalPaperAndQuestion();
        EvaluateResultModel evaluateResultModel = new EvaluateResultModel();
        if(tPortalPaperAndQuestion.getChoice()!=null && tPortalPaperAndQuestion.getChoice().getQuestionAndResult()!=null) {
            EvaluateResultAndPointsModel evaluateResultAndPointsModel = getEvaluateResultAndPointsModel(tPortalPaperAndQuestion.getChoice());
            evaluateResultModel.setChoice(evaluateResultAndPointsModel);
        }
        if(tPortalPaperAndQuestion.getSelection()!=null && tPortalPaperAndQuestion.getSelection().getQuestionAndResult()!=null) {
            EvaluateResultAndPointsModel evaluateResultAndPointsModel = getEvaluateResultAndPointsModel(tPortalPaperAndQuestion.getSelection());
            evaluateResultModel.setSelection(evaluateResultAndPointsModel);
        }
        if(tPortalPaperAndQuestion.getFilling()!=null && tPortalPaperAndQuestion.getFilling().getQuestionAndResult()!=null) {
            EvaluateResultAndPointsModel evaluateResultAndPointsModel = getEvaluateResultAndPointsModel(tPortalPaperAndQuestion.getFilling());
            evaluateResultModel.setFilling(evaluateResultAndPointsModel);
        }
        evaluateResultModel.setGrades(trEvaluateResult.getGrades());
        evaluateResultModel.setId(tPortalPaperAndQuestion.getId());
        evaluateResultModel.setName(tPortalPaperAndQuestion.getName());
        evaluateResultModel.setSubjectId(tPortalPaperAndQuestion.getSubjectId());

        return evaluateResultModel;
    }

    private EvaluateResultAndPointsModel getEvaluateResultAndPointsModel(TEvaluateResultListAndPoints tEvaluateResultListAndPoints) {
        EvaluateResultAndPointsModel evaluateResultAndPointsModel = new EvaluateResultAndPointsModel();
        List<QuestionResultModel> questionList = tEvaluateResultListAndPoints.getQuestionAndResult().stream().map(this::getQuestionResultModel).collect(Collectors.toList());
        evaluateResultAndPointsModel.setQuestionList(questionList);
        evaluateResultAndPointsModel.setTotalPoints(tEvaluateResultListAndPoints.getTotalPoints());
        return evaluateResultAndPointsModel;
    }

    private QuestionResultModel getQuestionResultModel(TEvaluateResult tEvaluateResult) {
        QuestionResultModel questionResultModel = new QuestionResultModel();
        questionResultModel.setAnswer(tEvaluateResult.getAnswer());
        questionResultModel.setId(tEvaluateResult.getId());
        questionResultModel.setContent(tEvaluateResult.getContent());
        if(!tEvaluateResult.isIsCorrect()){
           questionResultModel.setWrongAnswer(tEvaluateResult.getWrongAnswer());
        }
        questionResultModel.setCorrect(tEvaluateResult.isIsCorrect());
        questionResultModel.setOptions(tEvaluateResult.getOptions());
        questionResultModel.setType(tEvaluateResult.getType());
        return questionResultModel;
    }

    private TPCreatPracticeRecordParam getTPCreatPracticeRecordParam(PracticeModel practiceModel, Integer userId) {
        TPCreatPracticeRecordParam tpCreatPracticeRecordParam = new TPCreatPracticeRecordParam();
        tpCreatPracticeRecordParam.setUserId(userId);
        tpCreatPracticeRecordParam.setChoice(practiceModel.getChoice());
        tpCreatPracticeRecordParam.setPaperId(practiceModel.getPaperId());
        tpCreatPracticeRecordParam.setFilling(practiceModel.getFilling());
        tpCreatPracticeRecordParam.setSelection(practiceModel.getSelection());
        return tpCreatPracticeRecordParam;
    }

    @GetMapping("/recommend")
    @ResponseBody
    public Result<PaperListModel> getPaperRecommendList(HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<PaperListModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRPortalPaperInfoList trPortalPaperInfoList;
        try {
            trPortalPaperInfoList = exaServerService.getRecommendPaperList(trUserLoginInfo.getId());
            if (trPortalPaperInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        PaperListModel paperListModel = getPaperRecommendListModel(trPortalPaperInfoList);
        return new Result.Builder<PaperListModel>(paperListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private PaperListModel getPaperRecommendListModel(TRPortalPaperInfoList trPortalPaperInfoList) {
        PaperListModel paperListModel = new PaperListModel();
        List<PaperModel> paperModels = trPortalPaperInfoList.getPaperInfoList().stream().map(this::getPaperModel).collect(Collectors.toList());
        paperListModel.setPaperList(paperModels);
        return paperListModel;
    }

    PaperModel getPaperModel(TPortalPaperInfo tPortalPaperInfo){
        PaperModel paperModel = new PaperModel();
        paperModel.setId(tPortalPaperInfo.getId());
        paperModel.setName(tPortalPaperInfo.getName());
        paperModel.setSubjectId(tPortalPaperInfo.getSubjectId());
        return paperModel;
    }

}
