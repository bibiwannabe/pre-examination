package com.libiyi.exa.admin.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.param.*;
import com.libiyi.exa.common.common.*;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/question")
public class QuestionController {
    static Logger logger = LogManager.getLogger(QuestionController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @PostMapping("/create")
    @ResponseBody
    public Result<String> createQuestion(@RequestBody QuestionParam questionParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TPAdminCreateQuestionInfo createQuestionInfo = getTPAdminCreateQuestionInfo(questionParam, userId);
        try {
            TRResponse trResponse = exaServerService.addQuestion(createQuestionInfo);
            if (trResponse.getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("创建题目失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminCreateQuestionInfo getTPAdminCreateQuestionInfo(QuestionParam questionParam, Integer userId) {
        TPAdminCreateQuestionInfo tpAdminCreateQuestionInfo = new TPAdminCreateQuestionInfo();
        tpAdminCreateQuestionInfo.setSubjectId(questionParam.getSubjectId());
        tpAdminCreateQuestionInfo.setAnswer(questionParam.getAnswer());
        tpAdminCreateQuestionInfo.setContent(questionParam.getContent());
        tpAdminCreateQuestionInfo.setCreateUser(userId);
        if(questionParam.getType() != QuestionTypeEnum.BLANK_FILLING.getCode()) {
            tpAdminCreateQuestionInfo.setOptions(questionParam.getOptions());
        }
        tpAdminCreateQuestionInfo.setType(questionParam.getType());
        return tpAdminCreateQuestionInfo;
    }


    @PostMapping("/update")
    @ResponseBody
    public Result<String> updateQuestion(@RequestBody QuestionModifyParam questionModifyParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminModifyQuestionInfo modifyQuestionInfo = getTPAdminModifyQuestionInfo(questionModifyParam);
        try {
            TRResponse trResponse = exaServerService.modifyQuestion(modifyQuestionInfo);
            if (trResponse.getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("创建题目失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminModifyQuestionInfo getTPAdminModifyQuestionInfo(QuestionModifyParam questionModifyParam) {
        TPAdminModifyQuestionInfo tpAdminModifyQuestionInfo = new TPAdminModifyQuestionInfo();
        tpAdminModifyQuestionInfo.setAnswer(questionModifyParam.getAnswer());
        tpAdminModifyQuestionInfo.setContent(questionModifyParam.getContent());
        tpAdminModifyQuestionInfo.setId(questionModifyParam.getId());
        tpAdminModifyQuestionInfo.setOptions(questionModifyParam.getOptions());
        tpAdminModifyQuestionInfo.setSubjectId(questionModifyParam.getSubjectId());
        return tpAdminModifyQuestionInfo;
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<QuestionListModel> getQuestionList(@RequestParam("subjectId") int subjectId,
                                                     @RequestParam("type") int type,
                                                     @RequestParam(value = "page",required = false, defaultValue = "1") int page,
                                                     @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                     HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<QuestionListModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminQuerryQuestionInfo queryQuestionInfo = getTPAdminQuerryQuestionInfo(page, size, subjectId, type);
        TRAdminQuestionInfoList trAdminQuestionInfoList;
        try {
            trAdminQuestionInfoList = exaServerService.getQuestionListByParam(queryQuestionInfo);
            if (trAdminQuestionInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<QuestionListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取题目失败", e);
            return new Result.Builder<QuestionListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        QuestionListModel questionListModel = getQuestionListModel(trAdminQuestionInfoList);
        return new Result.Builder<QuestionListModel>(questionListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private QuestionListModel getQuestionListModel(TRAdminQuestionInfoList trAdminQuestionInfoList) {
        QuestionListModel questionListModel = new QuestionListModel();
        questionListModel.setPage(trAdminQuestionInfoList.getPagination().getPage());
        questionListModel.setSize(trAdminQuestionInfoList.getPagination().getSize());
        questionListModel.setTotal(trAdminQuestionInfoList.getPagination().getTotalNum());
        List<QuestionModel> questionModelList = trAdminQuestionInfoList.getAdminQuestionInfoList().stream().map(this::getQuestionModle).collect(Collectors.toList());
        questionListModel.setQuestionList(questionModelList);
        return questionListModel;
    }

    private QuestionModel getQuestionModle(TAdminQuestionInfo tAdminQuestionInfo) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setAnswer(tAdminQuestionInfo.getAnswer());
        questionModel.setContent(tAdminQuestionInfo.getContent());
        questionModel.setCounts(tAdminQuestionInfo.getCounts());
        questionModel.setCreateUser(tAdminQuestionInfo.getCreateUser());
        questionModel.setId(tAdminQuestionInfo.getId());
        questionModel.setCorrectNum(tAdminQuestionInfo.getCorrectNum());
        questionModel.setType(tAdminQuestionInfo.getType());
        questionModel.setOptions(tAdminQuestionInfo.getOptions());
        questionModel.setSubjectId(tAdminQuestionInfo.getSubjectId());
        return questionModel;
    }

    private TPAdminQuerryQuestionInfo getTPAdminQuerryQuestionInfo(int page, int size, int subjectId, int type) {
        TPAdminQuerryQuestionInfo tpAdminQuerryQuestionInfo = new TPAdminQuerryQuestionInfo();
        TPagination tPagination = new TPagination();
        tPagination.setPage(page);
        tPagination.setSize(size);
        tpAdminQuerryQuestionInfo.setPagination(tPagination);
        tpAdminQuerryQuestionInfo.setSubjectId(subjectId);
        tpAdminQuerryQuestionInfo.setType(type);
        return tpAdminQuerryQuestionInfo;
    }


}
