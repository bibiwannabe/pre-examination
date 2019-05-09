package com.libiyi.exa.admin.api.controller;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.admin.api.param.*;
import com.libiyi.exa.admin.api.util.ParserPaperQuestionUtil;
import com.libiyi.exa.common.common.AccountTypeEnum;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.RequestConst;
import com.libiyi.exa.common.common.Result;
import com.libiyi.exa.common.service.ExaServerService;
import com.libiyi.exa.common.thrift.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paper")
public class PaperInfoController {
    static Logger logger = LogManager.getLogger(PaperInfoController.class);

    @Autowired
    private ExaServerService.Iface exaServerService;

    @PostMapping("/create")
    @ResponseBody
    public Result<Integer> createPaper(@RequestBody PaperCreateParam paperCreateParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<Integer>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        Integer userId = trUserLoginInfo.getId();
        TPAdminCreatePaperInfo createPaperInfo = getTPAdminCreatePaperInfo(paperCreateParam, userId);
        TRIdResult trIdResult;
        try {
            trIdResult = exaServerService.createPaper(createPaperInfo);
            if (trIdResult.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<Integer>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("创建试卷失败", e);
            return new Result.Builder<Integer>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<Integer>(trIdResult.getId()).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminCreatePaperInfo getTPAdminCreatePaperInfo(PaperCreateParam paperCreateParam, Integer userId) {
        TPAdminCreatePaperInfo tpAdminCreatePaperInfo = new TPAdminCreatePaperInfo();
        tpAdminCreatePaperInfo.setCreateUser(userId);
        tpAdminCreatePaperInfo.setName(paperCreateParam.getName());
        tpAdminCreatePaperInfo.setQuestionList(ParserPaperQuestionUtil.getQuestionList(paperCreateParam));
        tpAdminCreatePaperInfo.setSubjectId(paperCreateParam.getSubjectId());
        return tpAdminCreatePaperInfo;
    }

    @PostMapping("/update")
    @ResponseBody
    public Result<String> updatePaper(@RequestBody PaperModifyParam paperModifyParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminModifyPaperInfo modifyPaperInfo = getTPAdminModifyPaperInfo(paperModifyParam);
        try {
            TRResponse trResponse = exaServerService.modifyPaper(modifyPaperInfo);
            if (trResponse.getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error(" 修改试卷失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TPAdminModifyPaperInfo getTPAdminModifyPaperInfo(PaperModifyParam paperModifyParam) {
        TPAdminModifyPaperInfo tpAdminModifyPaperInfo = new TPAdminModifyPaperInfo();
        tpAdminModifyPaperInfo.setId(paperModifyParam.getId());
        tpAdminModifyPaperInfo.setName(paperModifyParam.getName());
        tpAdminModifyPaperInfo.setSubjectId(paperModifyParam.getSubjectId());
        tpAdminModifyPaperInfo.setQuestionList(ParserPaperQuestionUtil.getModifyQuestionList(paperModifyParam));
        return tpAdminModifyPaperInfo;
    }

    @GetMapping("/list")
    @ResponseBody
    public Result<PaperListModel> getPaperList(@RequestParam("subjectId") int subjectId,
                                           @RequestParam(value = "page",required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                           HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<PaperListModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TPAdminQueryPaperInfo queryPaperInfo = getTPAdminQueryPaperInfo(page, size, subjectId);
        TRAdminPaperInfoList trAdminPaperInfoList;
            synchronized (PaperInfoController.class){
                try {
                    trAdminPaperInfoList = exaServerService.getPaperListByParam(queryPaperInfo);
                    if (trAdminPaperInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                        return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
                    }
                } catch (Throwable e) {
                    logger.error("获取试卷列表失败", e);
                    return new Result.Builder<PaperListModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
                }
            }
        PaperListModel paperListModel = getPaperListModel(trAdminPaperInfoList);
        return new Result.Builder<PaperListModel>(paperListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
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
        paperModel.setAvgPoints(tPaperInfo.getAvgPoints());
        paperModel.setCounts(tPaperInfo.getCounts());
        paperModel.setCreateTime(tPaperInfo.getCreateTime());
        paperModel.setName(tPaperInfo.getName());
        paperModel.setUpdateTime(tPaperInfo.getUpdateTime());
        paperModel.setId(tPaperInfo.getId());
        paperModel.setSubjectId(tPaperInfo.getSubjectId());
        paperModel.setCreateUser(tPaperInfo.getCreateUser());
        return paperModel;
    }

    private TPAdminQueryPaperInfo getTPAdminQueryPaperInfo(int page, int size, int subjectId) {
        TPAdminQueryPaperInfo tpAdminQueryPaperInfo = new TPAdminQueryPaperInfo();
        tpAdminQueryPaperInfo.setSubjectId(subjectId);
        TPagination tPagination = new TPagination();
        tPagination.setPage(page);
        tPagination.setSize(size);
        tpAdminQueryPaperInfo.setPagination(tPagination);
        return tpAdminQueryPaperInfo;
    }

    @GetMapping("/{paperId}")
    @ResponseBody
    public Result<PaperAndQuestionInfoModel> getPaperList(@PathVariable("paperId") int paperId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<PaperAndQuestionInfoModel>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRAdminPaperAndQuestionInfo trAdminPaperAndQuestionInfo;
        try {
            trAdminPaperAndQuestionInfo = exaServerService.getPaperById(paperId);
            if (trAdminPaperAndQuestionInfo.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<PaperAndQuestionInfoModel>().setCode(trAdminPaperAndQuestionInfo.getResponse().getCode()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷失败", e);
            return new Result.Builder<PaperAndQuestionInfoModel>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        PaperAndQuestionInfoModel paperAndQuestionInfoModel = getPaperAndQuestionInfoModel(trAdminPaperAndQuestionInfo);
        return new Result.Builder<PaperAndQuestionInfoModel>(paperAndQuestionInfoModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private PaperAndQuestionInfoModel getPaperAndQuestionInfoModel(TRAdminPaperAndQuestionInfo trAdminPaperAndQuestionInfo) {
        PaperAndQuestionInfoModel paperAndQuestionInfoModel = new PaperAndQuestionInfoModel();
        paperAndQuestionInfoModel.setId(trAdminPaperAndQuestionInfo.getId());
        paperAndQuestionInfoModel.setAvgPoints(trAdminPaperAndQuestionInfo.getAvgPoints());
        paperAndQuestionInfoModel.setCounts(trAdminPaperAndQuestionInfo.getCounts());
        paperAndQuestionInfoModel.setCreateTime(trAdminPaperAndQuestionInfo.getCreateTime());
        paperAndQuestionInfoModel.setName(trAdminPaperAndQuestionInfo.getName());
        paperAndQuestionInfoModel.setCreateUser(trAdminPaperAndQuestionInfo.getCreateUser());
        paperAndQuestionInfoModel.setSubjectId(trAdminPaperAndQuestionInfo.getSubjectId());
        paperAndQuestionInfoModel.setUpdateTime(trAdminPaperAndQuestionInfo.getUpdateTime());
        if(trAdminPaperAndQuestionInfo.getChoice()!=null) {
            paperAndQuestionInfoModel.setChoice(getQuestionInfoListAndPoints(trAdminPaperAndQuestionInfo.getChoice()));
        }
        if(trAdminPaperAndQuestionInfo.getSelection()!=null) {
            paperAndQuestionInfoModel.setSelection(getQuestionInfoListAndPoints(trAdminPaperAndQuestionInfo.getSelection()));
        }
        if(trAdminPaperAndQuestionInfo.getFilling()!=null) {
            paperAndQuestionInfoModel.setFilling(getQuestionInfoListAndPoints(trAdminPaperAndQuestionInfo.getFilling()));
        }
        return paperAndQuestionInfoModel;
    }

    private QuestionInfoListAndPoints getQuestionInfoListAndPoints(TQuestionPointsAndInfoList tQuestionPointsAndInfoList) {
        QuestionInfoListAndPoints questionInfoListAndPoints = new QuestionInfoListAndPoints();
        logger.info(JSON.toJSONString(tQuestionPointsAndInfoList));
        questionInfoListAndPoints.setPoint(tQuestionPointsAndInfoList.getPoint());
        questionInfoListAndPoints.setQuestionList(tQuestionPointsAndInfoList.getQuestionInfoList().stream().map(this::getQuestionModel).collect(Collectors.toList()));
        return questionInfoListAndPoints;
    }

    private QuestionModel getQuestionModel(TAdminQuestionInfo tAdminQuestionInfo) {
        QuestionModel questionModel = new QuestionModel();
        questionModel.setId(tAdminQuestionInfo.getId());
        questionModel.setSubjectId(tAdminQuestionInfo.getSubjectId());
        questionModel.setOptions(tAdminQuestionInfo.getOptions());
        questionModel.setType(tAdminQuestionInfo.getType());
        questionModel.setCorrectNum(tAdminQuestionInfo.getCorrectNum());
        questionModel.setCreateUser(tAdminQuestionInfo.getCreateUser());
        questionModel.setContent(tAdminQuestionInfo.getContent());
        questionModel.setCounts(tAdminQuestionInfo.getCounts());
        questionModel.setAnswer(tAdminQuestionInfo.getAnswer());
        return questionModel;
    }

    @GetMapping("/lowestList")
    @ResponseBody
    public Result<List<PaperModel>> getLowestFivePaper(@RequestParam("subjectId") int subjectId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRAdminPaperInfoList trAdminPaperInfoList;
        List<PaperModel> paperListModel;
        try {
            trAdminPaperInfoList = exaServerService.getAvgLowestFive(subjectId);
            if (trAdminPaperInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error("获取试卷列表失败", e);
            return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        if (!CollectionUtils.isEmpty(trAdminPaperInfoList.getPaperInfoList())) {
            paperListModel = trAdminPaperInfoList.getPaperInfoList().stream().map(this::getPaperModel).collect(Collectors.toList());
        } else {
            paperListModel = new ArrayList<>();
        }
        return new Result.Builder<List<PaperModel>>(paperListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @GetMapping("/all")
    @ResponseBody
    public Result<List<PaperModel>> getAllPaperBySubject(@RequestParam("subjectId") int subjectId, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TRAdminPaperList trAdminPaperInfoList;
        synchronized (PaperInfoController.class){
            try {
                trAdminPaperInfoList = exaServerService.getAllPaperBySubject(subjectId);
                if (trAdminPaperInfoList.getResponse().getCode() != CodeEnum.SUCCESS.getCode()) {
                    return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
                }
            } catch (Throwable e) {
                logger.error("获取试卷列表失败", e);
                return new Result.Builder<List<PaperModel>>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        }
        List<PaperModel> paperListModel = trAdminPaperInfoList.getPaperInfoList().stream().map(this::getPaperModel).collect(Collectors.toList());
        return new Result.Builder<List<PaperModel>>(paperListModel).setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    @PostMapping("/addQuestion")
    @ResponseBody
    public Result<String> updatePaper(@RequestBody PaperAddQuestionParam paperAddQuestionParam, HttpSession session) {
        Object object = session.getAttribute(RequestConst.USER_INFO);
        TRUserLoginInfo trUserLoginInfo = JSON.parseObject(JSON.toJSONString(object), TRUserLoginInfo.class);
        if (trUserLoginInfo == null || trUserLoginInfo.getAccType() != AccountTypeEnum.TEACHER.getCode()) {
            return new Result.Builder<String>().setCode(CodeEnum.NO_LOGIN.getCode()).setMessage(CodeEnum.NO_LOGIN.getDesc()).build();
        }
        TAddQuestionParam addQuestionParam = getTAddQuestionParam(paperAddQuestionParam);
        try {
            TRResponse trResponse = exaServerService.addQuestionToPaper(addQuestionParam);
            if (trResponse.getCode() != CodeEnum.SUCCESS.getCode()) {
                return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
            }
        } catch (Throwable e) {
            logger.error(" 添加试题到试卷失败", e);
            return new Result.Builder<String>().setCode(CodeEnum.UNKNOWN_ERROR.getCode()).setMessage(CodeEnum.UNKNOWN_ERROR.getDesc()).build();
        }
        return new Result.Builder<String>().setCode(CodeEnum.SUCCESS.getCode()).build();
    }

    private TAddQuestionParam getTAddQuestionParam(PaperAddQuestionParam paperAddQuestionParam) {
        TAddQuestionParam tAddQuestionParam = new TAddQuestionParam();
        tAddQuestionParam.setPaperId(paperAddQuestionParam.getId());
        tAddQuestionParam.setQuestionId(paperAddQuestionParam.getQuestinId());
        tAddQuestionParam.setQuestionType(paperAddQuestionParam.getQuestionType());
        return tAddQuestionParam;
    }
}
