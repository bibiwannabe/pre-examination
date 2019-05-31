package com.libiyi.exa.admin.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.param.*;
import com.libiyi.exa.admin.api.util.ParseCSVUtil;
import com.libiyi.exa.common.common.*;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
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
    public Result<Integer> createQuestion(@RequestBody QuestionParam questionParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<Integer>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TRIdResult trIdResult;
        TPAdminCreateQuestionInfo createQuestionInfo = getTPAdminCreateQuestionInfo(questionParam, userId);
        try {
            trIdResult = exaServerService.addQuestion(createQuestionInfo);
            if (trIdResult.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<Integer>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("创建题目失败", e);
            return new Result.Builder<Integer>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<Integer>(trIdResult.getId()).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminCreateQuestionInfo getTPAdminCreateQuestionInfo(QuestionParam questionParam, Integer userId) {
        TPAdminCreateQuestionInfo tpAdminCreateQuestionInfo = new TPAdminCreateQuestionInfo();
        tpAdminCreateQuestionInfo.setSubjectId(questionParam.getSubjectId());
        tpAdminCreateQuestionInfo.setAnswer(questionParam.getAnswer());
        tpAdminCreateQuestionInfo.setContent(questionParam.getContent());
        tpAdminCreateQuestionInfo.setCreateUser(userId);
        if (questionParam.getType() != QuestionTypeEnum.BLANK_FILLING.getCode()) {
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
                                                     @RequestParam(value = "page", required = false, defaultValue = "1") int page,
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


    @GetMapping("/{questionId}")
    @ResponseBody
    public Result<QuestionModel> getQuestionInfo(@PathVariable("questionId") int questionId,
                                                 HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<QuestionModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRAdminQuestionInfo trAdminQuestionInfo;
        try {
            trAdminQuestionInfo = exaServerService.getQuestionById(questionId);
            if (trAdminQuestionInfo.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<QuestionModel>().setCode(trAdminQuestionInfo.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取题目失败", e);
            return new Result.Builder<QuestionModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        QuestionModel questionModel = getQuestionModle(trAdminQuestionInfo.getAdminQuestionInfo());
        return new Result.Builder<QuestionModel>(questionModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }


    @GetMapping("/search")
    @ResponseBody
    public Result<List<QuestionModel>> searchQuestionList(@RequestParam("subjectId") int subjectId,
                                                          @RequestParam("type") int type,
                                                          @RequestParam(value = "key") String keywords,
                                                          HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<List<QuestionModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminSearchQuestionParam searchQuestionParam = getTPAdminSearchQuestionParam(keywords, subjectId, type);
        TRAdminQuestionSearchList trAdminQuestionSearchList;
        try {
            trAdminQuestionSearchList = exaServerService.getQuestionListBySearchKey(searchQuestionParam);
            if (trAdminQuestionSearchList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<List<QuestionModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取题目失败", e);
            return new Result.Builder<List<QuestionModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        List<QuestionModel> questionListModel = trAdminQuestionSearchList.getAdminQuestionInfoList().stream().map(this::getQuestionModle).collect(Collectors.toList());
        return new Result.Builder<List<QuestionModel>>(questionListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminSearchQuestionParam getTPAdminSearchQuestionParam(String keywords, int subjectId, int type) {
        TPAdminSearchQuestionParam tpAdminSearchQuestionParam = new TPAdminSearchQuestionParam();
        tpAdminSearchQuestionParam.setKeyWords(keywords);
        tpAdminSearchQuestionParam.setType(type);
        tpAdminSearchQuestionParam.setSubjectId(subjectId);
        return tpAdminSearchQuestionParam;
    }


    @GetMapping("/wrongMax")
    @ResponseBody
    public Result<List<PaperQuestionDataModel>> getQuestionListByPaperId(@RequestParam("paperId") int paperId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<List<PaperQuestionDataModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRPaperQuestionDataList trPaperQuestionDataList;
        try {
            trPaperQuestionDataList = exaServerService.getPaperQuestionDataList(paperId);
            if (trPaperQuestionDataList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<List<PaperQuestionDataModel>>().setCode(trPaperQuestionDataList.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取题目失败", e);
            return new Result.Builder<List<PaperQuestionDataModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        List<PaperQuestionDataModel> questionListModel = trPaperQuestionDataList.getPaperQuestionDataList().stream().map(this::getPaperQuestionDataModel).collect(Collectors.toList());
        return new Result.Builder<List<PaperQuestionDataModel>>(questionListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private PaperQuestionDataModel getPaperQuestionDataModel(TPaperQuestionData tPaperQuestionData) {
        PaperQuestionDataModel paperQuestionDataModel = new PaperQuestionDataModel();
        paperQuestionDataModel.setCounts(tPaperQuestionData.getCount());
        paperQuestionDataModel.setPaperId(tPaperQuestionData.getPaperId());
        paperQuestionDataModel.setQuestionContent(tPaperQuestionData.getQuestionContent());
        paperQuestionDataModel.setSubjectId(tPaperQuestionData.getSubjectId());
        paperQuestionDataModel.setQuestionId(tPaperQuestionData.getQuestionId());
        return paperQuestionDataModel;
    }


    @PostMapping("/batchUpload")
    @ResponseBody
    public Result<String> batchUploadQuestion(@RequestParam("files") MultipartFile file, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TRIdResult trIdResult;
        Integer count = 0;
        try {
            List<ParseCSVUtil.QuestionBean> questionList = ParseCSVUtil.parseCSV2QuestionList(file, userId);
            List<TPAdminCreateQuestionInfo> questionInfoList = new ArrayList<>();
            questionList.forEach(question -> {
                questionInfoList.add(getTPAdminQuestionInfo(question, userId));
                logger.info(JSON.toJSONString(questionInfoList));
            });
            trIdResult = exaServerService.batchUploadQuestion(questionInfoList);
            count = trIdResult.getId();
            if(trIdResult.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<String>("批量上传失败").setCode(trIdResult.getResponse().getCode()).build();
            }
        } catch (IOException e) {
            logger.error("解析错误：", e);
            return new Result.Builder<String>("批量上传失败, 请检查格式").setCode(CodeEnum.DATA_ILEAGLE.getCode()).build();
        } catch (Exception e) {
            logger.error("未知错误", e);
            return new Result.Builder<String>("批量上传失败, 未知错误").setCode(CodeEnum.UNKNOWN_ERROR.getCode()).build();
        }
        return new Result.Builder<String>("批量上传成功, 成功上传" + count + "条").setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminCreateQuestionInfo getTPAdminQuestionInfo(ParseCSVUtil.QuestionBean q, Integer userId) {
        TPAdminCreateQuestionInfo tpAdminCreateQuestionInfo = new TPAdminCreateQuestionInfo();
        tpAdminCreateQuestionInfo.setContent(q.getContent());
        if (q.getType().intValue() == QuestionTypeEnum.MULTIPLE_CHOICE.getCode().intValue() || q.getType().intValue() == QuestionTypeEnum.MULTIPLE_SELECTION.getCode().intValue()) {
            tpAdminCreateQuestionInfo.setOptions(JSON.toJSONString(q.getOptions().split(";")));
        } else {
            tpAdminCreateQuestionInfo.setOptions("");

        }
        if (q.getType().intValue() == QuestionTypeEnum.MULTIPLE_SELECTION.getCode().intValue()) {
            tpAdminCreateQuestionInfo.setAnswer(JSON.toJSONString(q.getAnswer().split(";")));
        } else {
            tpAdminCreateQuestionInfo.setAnswer(q.getAnswer());

        }
        tpAdminCreateQuestionInfo.setCreateUser(userId);
        tpAdminCreateQuestionInfo.setSubjectId(q.getSubjectId());
        tpAdminCreateQuestionInfo.setType(q.getType());
        return tpAdminCreateQuestionInfo;
    }
}
