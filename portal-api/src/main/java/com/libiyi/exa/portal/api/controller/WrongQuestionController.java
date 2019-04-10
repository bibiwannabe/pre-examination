package com.libiyi.exa.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.portal.api.param.QuestionAnswerModel;
import com.libiyi.exa.portal.api.param.SubjectQuestionModel;
import com.libiyi.exa.portal.api.param.SubjectQuestionPageModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/question")
public class WrongQuestionController {
    static Logger logger = LogManager.getLogger(WrongQuestionController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @GetMapping("/list")
    @ResponseBody
    public Result<List<QuestionAnswerModel>> getQuestionList(@RequestParam("subjectId") int subjectId,
                                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                       HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<List<QuestionAnswerModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TPPortalWrongQuestionParam tpPortalWrongQuestionParam = getTPPortalWrongQuestionParam(page, size, subjectId, userId);
        TRPortalWrongQuestionInfoList trPortalWrongQuestionInfoList;
        try {
            trPortalWrongQuestionInfoList = exaServerService.getWrongQuestionBySubjectId(tpPortalWrongQuestionParam);
            if (trPortalWrongQuestionInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<List<QuestionAnswerModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<List<QuestionAnswerModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        List<QuestionAnswerModel> questionAnswerModel = trPortalWrongQuestionInfoList.getQuestionInfoList().stream().map(this::getQuestionAnswerModel).collect(Collectors.toList());
        return new Result.Builder<List<QuestionAnswerModel>>(questionAnswerModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private QuestionAnswerModel getQuestionAnswerModel(TPortalWrongQuestionInfo tPortalWrongQuestionInfo) {
        QuestionAnswerModel questionAnswerModel = new QuestionAnswerModel();
        questionAnswerModel.setAnswer(tPortalWrongQuestionInfo.getAnswer());
        questionAnswerModel.setId(tPortalWrongQuestionInfo.getId());
        questionAnswerModel.setContent(tPortalWrongQuestionInfo.getContent());
        questionAnswerModel.setOptions(tPortalWrongQuestionInfo.getOptions());
        questionAnswerModel.setType(tPortalWrongQuestionInfo.getType());
        questionAnswerModel.setWrongCount(tPortalWrongQuestionInfo.getWrongCounts());
        questionAnswerModel.setSubjectId(tPortalWrongQuestionInfo.getSubjectId());
        return questionAnswerModel;
    }

    private TPPortalWrongQuestionParam getTPPortalWrongQuestionParam(Integer page, Integer size, Integer subjectId, Integer userId) {
        TPPortalWrongQuestionParam  tpPortalWrongQuestionParam = new TPPortalWrongQuestionParam();
        TPagination tPagination = new TPagination();
        tPagination.setPage(page);
        tPagination.setSize(size);
        tpPortalWrongQuestionParam.setSubjectId(subjectId);
        tpPortalWrongQuestionParam.setUserId(userId);
        tpPortalWrongQuestionParam.setPagination(tPagination);
        return tpPortalWrongQuestionParam;
    }

    @GetMapping("/subjectList")
    @ResponseBody
    public Result<SubjectQuestionPageModel> getSubjectQuestionList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                                   HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null) {
            return new Result.Builder<SubjectQuestionPageModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        SubjectQuestionPageModel subjectQuestionPageModel = new SubjectQuestionPageModel();
        List<SubjectQuestionModel> questionAnswerModel = new ArrayList<>();
        TPUserIdAndPageParam tpUserIdAndPageParam = getTPUserIdAndPageParam(page, size, userId);
        TRPraticeSubject trPraticeSubject;
        try {
            trPraticeSubject = exaServerService.getSubjectQuestionCount(tpUserIdAndPageParam);
            if (trPraticeSubject.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<SubjectQuestionPageModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取错题科目及数量失败", e);
            return new Result.Builder<SubjectQuestionPageModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        if(!CollectionUtils.isEmpty(trPraticeSubject.getSubjectQuestionCount())) {
            questionAnswerModel = trPraticeSubject.getSubjectQuestionCount().stream().map(this::getSubjectQuestionModel).collect(Collectors.toList());
            subjectQuestionPageModel.setSubjectList(questionAnswerModel);
            subjectQuestionPageModel.setTotal(trPraticeSubject.getPagination().getTotalNum());
        }
        subjectQuestionPageModel.setPage(page);
        subjectQuestionPageModel.setSize(page);
        return new Result.Builder<SubjectQuestionPageModel>(subjectQuestionPageModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private SubjectQuestionModel getSubjectQuestionModel(TSubjectQuestionCount tSubjectQuestionCount) {
        SubjectQuestionModel subjectQuestionModel = new SubjectQuestionModel();
        subjectQuestionModel.setQuestionCounts(tSubjectQuestionCount.getQuestionNum());
        subjectQuestionModel.setSubjectId(tSubjectQuestionCount.getSubjectId());
        subjectQuestionModel.setSubjectName(tSubjectQuestionCount.getSubjectName());
        return subjectQuestionModel;
    }

    private TPUserIdAndPageParam getTPUserIdAndPageParam(Integer page, Integer size, Integer userId) {
        TPUserIdAndPageParam tpUserIdAndPageParam = new TPUserIdAndPageParam();
        TPagination tPagination = new TPagination();
        tPagination.setSize(size);
        tPagination.setPage(page);
        tpUserIdAndPageParam.setPagination(tPagination);
        tpUserIdAndPageParam.setUserId(userId);
        return tpUserIdAndPageParam;
    }
}
