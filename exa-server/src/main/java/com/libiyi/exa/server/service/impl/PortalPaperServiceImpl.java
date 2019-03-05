package com.libiyi.exa.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.CodeEnum;
import com.libiyi.exa.common.common.QuestionTypeEnum;
import com.libiyi.exa.common.model.PaperQuestionListModel;
import com.libiyi.exa.common.thrift.*;
import com.libiyi.exa.common.util.DateUtil;
import com.libiyi.exa.common.util.ParseUserAnswer;
import com.libiyi.exa.common.util.SetUtil;
import com.libiyi.exa.server.dao.PaperInfoMapper;
import com.libiyi.exa.server.dao.PracticeRecordMapper;
import com.libiyi.exa.server.dao.QuestionInfoMapper;
import com.libiyi.exa.server.dao.WrongRecordMapper;
import com.libiyi.exa.server.entity.PaperInfo;
import com.libiyi.exa.server.entity.PracticeRecord;
import com.libiyi.exa.server.entity.QuestionInfo;
import com.libiyi.exa.server.entity.WrongRecord;
import com.libiyi.exa.server.service.PortalPaperPaperService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortalPaperServiceImpl implements PortalPaperPaperService {
    static Logger logger = LogManager.getLogger(PortalPaperServiceImpl.class);

    @Autowired
    private PaperInfoMapper paperInfoMapper;

    @Autowired
    private QuestionInfoMapper questionInfoMapper;

    @Autowired
    private WrongRecordMapper wrongRecordMapper;

    @Autowired
    private PracticeRecordMapper practiceRecordMapper;

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
        tPortalPaperInfo.setAvgPoints(paperInfo.getAvgPoints());
        tPortalPaperInfo.setCounts(paperInfo.getCounts());
        return tPortalPaperInfo;
    }


    @Override
    public TREvaluateResult updateEvaluateResult(TPCreatPracticeRecordParam practiceParam) {
        TREvaluateResult trEvaluateResult = new TREvaluateResult();
        TPortalPaperAndQuestion tPortalPaperAndQuestion = new TPortalPaperAndQuestion();
        Integer grades = 0;
        PaperInfo paperInfo = paperInfoMapper.getById(practiceParam.getPaperId());
        PaperQuestionListModel paperQuestionListModel = JSON.parseObject(paperInfo.getQuestionList(), PaperQuestionListModel.class);
        Integer userId = practiceParam.getUserId();
        TRResponse trResponse = new TRResponse();
        if(!CollectionUtils.isEmpty(practiceParam.getChoice())){
            TEvaluateResultListAndPoints tEvaluateResultListAndPoints = checkAnswerCorrectAndSaveRecord(practiceParam.getChoice(), userId, paperQuestionListModel.getChoice().getPoint());
            tPortalPaperAndQuestion.setChoice(tEvaluateResultListAndPoints);
            grades += tEvaluateResultListAndPoints.getTotalPoints();
        }
        if(!CollectionUtils.isEmpty(practiceParam.getSelection())){
            TEvaluateResultListAndPoints tEvaluateResultListAndPoints = checkAnswerCorrectAndSaveRecord(practiceParam.getSelection(), userId, paperQuestionListModel.getSelection().getPoint());
            tPortalPaperAndQuestion.setSelection(tEvaluateResultListAndPoints);
            grades += tEvaluateResultListAndPoints.getTotalPoints();
        }
        if(!CollectionUtils.isEmpty(practiceParam.getFilling())){
            TEvaluateResultListAndPoints tEvaluateResultListAndPoints = checkAnswerCorrectAndSaveRecord(practiceParam.getFilling(), userId, paperQuestionListModel.getFilling().getPoint());
            tPortalPaperAndQuestion.setFilling(tEvaluateResultListAndPoints);
            grades += tEvaluateResultListAndPoints.getTotalPoints();
        }
        try {
            paperInfoMapper.updateAvgAndCountsByIdAndPoints(paperInfo.getId(), grades);
            PracticeRecord practiceRecord = getPracticeRecord(practiceParam, grades, paperInfo.getSubjectId());
            practiceRecordMapper.createRecord(practiceRecord);
        } catch (Exception e) {
            logger.error("更新平均分失败", e);
        }
        tPortalPaperAndQuestion.setId(paperInfo.getId());
        tPortalPaperAndQuestion.setSubjectId(paperInfo.getSubjectId());
        tPortalPaperAndQuestion.setName(paperInfo.getName());
        trEvaluateResult.setTPortalPaperAndQuestion(tPortalPaperAndQuestion);
        trEvaluateResult.setGrades(grades);
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trEvaluateResult.setResponse(trResponse);
        return trEvaluateResult;
    }

    /**
     * 获取推荐试卷列表
     * @param userId
     * @return
     */
    @Override
    public TRPortalPaperInfoList getRecommendPaperList(int userId) {
        List<PaperInfo> paperInfoList;
        TRResponse trResponse = new TRResponse();
        TRPortalPaperInfoList trPortalPaperInfoList = new TRPortalPaperInfoList();
        PracticeRecord practiceRecord = practiceRecordMapper.getRecentOne(userId);
        if (practiceRecord == null) {
            paperInfoList = paperInfoMapper.getMostCountsThree();
        }else {
            paperInfoList = paperInfoMapper.getThreeBySubjectId(practiceRecord.getSubjectId(), practiceRecord.getPaperId());
        }
        List<TPortalPaperInfo> tPortalPaperInfos = paperInfoList.stream().map(this::getTPortalPaperInfo).collect(Collectors.toList());
        if (tPortalPaperInfos != null) {
            trPortalPaperInfoList.setPaperInfoList(tPortalPaperInfos);
        } else {
            trPortalPaperInfoList.setPaperInfoList(new ArrayList<>());
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trPortalPaperInfoList.setResponse(trResponse);
        return trPortalPaperInfoList;
    }

    /**
     * 根据id获取做卷记录
     *
     * @param id
     * @return
     */
    @Override
    public TREvaluateResult getEvaluateResult(int id) {
        TREvaluateResult trEvaluateResult = new TREvaluateResult();
        TRResponse response = new TRResponse();
        PaperInfo paperInfo;
        PracticeRecord practiceRecord;
        try{
            practiceRecord = practiceRecordMapper.getById(id);
            paperInfo = paperInfoMapper.getById(practiceRecord.getPaperId());
            if (paperInfo == null) {
                response.setCode(CodeEnum.DATA_ILEAGLE.getCode());
                trEvaluateResult.setResponse(response);
                return trEvaluateResult;
            }
            trEvaluateResult.setGrades(practiceRecord.getPoints());
        } catch (Exception e) {
            logger.error("获取做题记录失败", e);
            response.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trEvaluateResult.setResponse(response);
            return trEvaluateResult;
        }
        response.setCode(CodeEnum.SUCCESS.getCode());
        try {
            TPortalPaperAndQuestion tPortalPaperAndQuestion = getTPortalPaperAndQuestion(paperInfo, practiceRecord);
            trEvaluateResult.setTPortalPaperAndQuestion(tPortalPaperAndQuestion);
            trEvaluateResult.setResponse(response);
        } catch (Exception e){
            logger.error("转化出错", e);
        }
        return trEvaluateResult;
    }

    /**
     *  获取最热门的试卷
     *
     * @return
     */
    @Override
    public TRPortalPaperInfoList getHotestPaperList() {
        TRPortalPaperInfoList trPortalPaperInfoList = new TRPortalPaperInfoList();
        TRResponse trResponse = new TRResponse();
        try {
            List<PaperInfo> paperInfoList = paperInfoMapper.getMostCountsThree();
            List<TPortalPaperInfo> portalPaperInfos = paperInfoList.stream().map(this::getTPortalPaperInfo).collect(Collectors.toList());
            trPortalPaperInfoList.setPaperInfoList(portalPaperInfos);
        } catch (Exception e) {
            logger.error("获取最热门的试卷列表失败", e);
            trResponse.setCode(CodeEnum.UNKNOWN_ERROR.getCode());
            trPortalPaperInfoList.setResponse(trResponse);
            return trPortalPaperInfoList;
        }
        trResponse.setCode(CodeEnum.SUCCESS.getCode());
        trPortalPaperInfoList.setResponse(trResponse);
        return trPortalPaperInfoList;
    }

    /**
     * 根据做卷记录和试卷信息获取返回的答案
     *
     * @param paperInfo
     * @param practiceRecord
     * @return
     */
    private TPortalPaperAndQuestion getTPortalPaperAndQuestion(PaperInfo paperInfo, PracticeRecord practiceRecord){
        TPortalPaperAndQuestion tPortalPaperAndQuestion = new TPortalPaperAndQuestion();
        tPortalPaperAndQuestion.setId(paperInfo.getId());
        tPortalPaperAndQuestion.setSubjectId(paperInfo.getSubjectId());
        tPortalPaperAndQuestion.setName(paperInfo.getName());
        TPCreatPracticeRecordParam creatPracticeRecordParam = ParseUserAnswer.getTPCreatPracticeRecordParam(practiceRecord.getAnswer());
        PaperQuestionListModel paperQuestionListModel = JSON.parseObject(paperInfo.getQuestionList(), PaperQuestionListModel.class);
        tPortalPaperAndQuestion.setFilling(checkAnswerCorrect(creatPracticeRecordParam.getFilling(), paperQuestionListModel.getFilling().getPoint()));
        tPortalPaperAndQuestion.setSelection(checkAnswerCorrect(creatPracticeRecordParam.getSelection(), paperQuestionListModel.getSelection().getPoint()));
        tPortalPaperAndQuestion.setChoice(checkAnswerCorrect(creatPracticeRecordParam.getChoice(), paperQuestionListModel.getChoice().getPoint()));
        return tPortalPaperAndQuestion;
    }


    private PracticeRecord getPracticeRecord(TPCreatPracticeRecordParam practiceParam, Integer points, Integer subjectId) {
        PracticeRecord practiceRecord = new PracticeRecord();
        practiceRecord.setCreateTime(DateUtil.getNow());
        practiceRecord.setPaperId(practiceParam.getPaperId());
        practiceRecord.setPoints(points);
        practiceRecord.setUpdateTime(DateUtil.getNow());
        practiceRecord.setSubjectId(subjectId);
        practiceRecord.setUserId(practiceParam.getUserId());
        practiceRecord.setAnswer(ParseUserAnswer.getUserAnswerStr(practiceParam));
        return practiceRecord;
    }


    /**
     * 只检查答案并填充返回信息，不记录
     * @param tQuestionAnswerMap
     * @param eachPoint
     * @return
     */
    private TEvaluateResultListAndPoints checkAnswerCorrect(Map<Integer, String> tQuestionAnswerMap, Integer eachPoint) {
        Integer grades = 0;
        TEvaluateResultListAndPoints tEvaluateResultListAndPoints = new TEvaluateResultListAndPoints();
        List<Integer> idList = new ArrayList<>(tQuestionAnswerMap.keySet());
        if (CollectionUtils.isEmpty(idList)){
           return tEvaluateResultListAndPoints;
        }
        List<QuestionInfo> questionInfoList = questionInfoMapper.getByIdList(idList);
        List<TEvaluateResult> tEvaluateResults = new ArrayList<>();
        for (QuestionInfo questionInfo : questionInfoList) {
            Boolean isCorrect = false;
            TEvaluateResult tEvaluateResult = new TEvaluateResult();
            String userAnswer = tQuestionAnswerMap.get(questionInfo.getId());
            tEvaluateResult.setAnswer(questionInfo.getAnswer());
            tEvaluateResult.setId(questionInfo.getId());
            tEvaluateResult.setContent(questionInfo.getContent());
            tEvaluateResult.setOptions(questionInfo.getOptions());
            tEvaluateResult.setSubjectId(questionInfo.getSubjectId());
            if (questionInfo.getType().intValue() == QuestionTypeEnum.MULTIPLE_CHOICE.getCode().intValue()){
                isCorrect = checkChoiceIsCorrect(tQuestionAnswerMap.get(questionInfo.getId()), questionInfo);
            }
            if (questionInfo.getType().intValue() == QuestionTypeEnum.MULTIPLE_SELECTION.getCode().intValue()) {
                isCorrect = checkSelectionIsCorrect(tQuestionAnswerMap.get(questionInfo.getId()), questionInfo);
            }
            if (questionInfo.getType().intValue() == QuestionTypeEnum.BLANK_FILLING.getCode().intValue()) {
                isCorrect = checkFillingIsCorrect(tQuestionAnswerMap.get(questionInfo.getId()), questionInfo);
            }
            tEvaluateResult.setIsCorrect(isCorrect);
            tEvaluateResult.setType(questionInfo.getType());
            if (isCorrect) {
                grades += eachPoint;
            }else {
                tEvaluateResult.setWrongAnswer(userAnswer);
            }
            tEvaluateResults.add(tEvaluateResult);
        }
        tEvaluateResultListAndPoints.setQuestionAndResult(tEvaluateResults);
        tEvaluateResultListAndPoints.setTotalPoints(grades);
        return tEvaluateResultListAndPoints;
    }


    /**
     * 检查答案 并保存记录
     * @param tQuestionAnswerMap
     * @param userId
     * @param eachPoints
     * @return
     */
    private TEvaluateResultListAndPoints checkAnswerCorrectAndSaveRecord(Map<Integer, String> tQuestionAnswerMap, Integer userId, Integer eachPoints) {
        Integer grades = 0;
        TEvaluateResultListAndPoints tEvaluateResultListAndPoints = new TEvaluateResultListAndPoints();
        List<Integer> idList = new ArrayList<>(tQuestionAnswerMap.keySet());
        if(CollectionUtils.isEmpty(idList)) {
            return tEvaluateResultListAndPoints;
        }
        List<QuestionInfo> questionInfoList = questionInfoMapper.getByIdList(idList);
        List<TEvaluateResult> tEvaluateResults = new ArrayList<>();
        for (QuestionInfo questionInfo : questionInfoList) {
            TEvaluateResult tEvaluateResult = new TEvaluateResult();
            String userAnswer = tQuestionAnswerMap.get(questionInfo.getId());
            tEvaluateResult.setAnswer(questionInfo.getAnswer());
            tEvaluateResult.setId(questionInfo.getId());
            tEvaluateResult.setContent(questionInfo.getContent());
            tEvaluateResult.setOptions(questionInfo.getOptions());
            tEvaluateResult.setSubjectId(questionInfo.getSubjectId());
            Boolean isCorrect = checkIsCorrectAndSaveRecords(userAnswer, questionInfo, userId);
            tEvaluateResult.setIsCorrect(isCorrect);
            tEvaluateResult.setType(questionInfo.getType());
            if (isCorrect) {
                grades += eachPoints;
            }else {
                tEvaluateResult.setWrongAnswer(userAnswer);
            }
            tEvaluateResults.add(tEvaluateResult);
        }
        tEvaluateResultListAndPoints.setQuestionAndResult(tEvaluateResults);
        tEvaluateResultListAndPoints.setTotalPoints(grades);
        return tEvaluateResultListAndPoints;
    }

    private Boolean checkIsCorrectAndSaveRecords(String userAnswer, QuestionInfo questionInfo, Integer userId) {
        if(questionInfo.getType().intValue() == QuestionTypeEnum.MULTIPLE_CHOICE.getCode().intValue()) {
            return checkChoiceIsCorrectAndSaveRecords(userAnswer, questionInfo, userId);
        }
        if(questionInfo.getType().intValue() == QuestionTypeEnum.MULTIPLE_SELECTION.getCode().intValue()) {
            return checkSelectionIsCorrectAndSaveRecords(userAnswer, questionInfo, userId);
        }
        if(questionInfo.getType().intValue() == QuestionTypeEnum.BLANK_FILLING.getCode().intValue()) {
            return checkFillingIsCorrectAndSaveRecords(userAnswer, questionInfo, userId);
        }
        return true;
    }

    /**
     * 检查填空题是否正确并保存记录
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkFillingIsCorrectAndSaveRecords(String userAnswer, QuestionInfo questionInfo, Integer userId) {
        if(userAnswer.equals(questionInfo.getAnswer())) {
            try {
                questionInfoMapper.updateCorrectNumById(questionInfo.getId());
            } catch (Exception e) {
                logger.info("更新题目正确率失败", e);
            }
            return true;
        } else {
            try {
                WrongRecord wrongRecord = getWrongRecord(questionInfo, userId);
                questionInfoMapper.updateCountsById(questionInfo.getId());
                wrongRecordMapper.createRecord(wrongRecord);
            }catch (Exception e) {
                logger.info("更新题目做题总人数失败", e);
            }
           return false;
        }
    }


    /**
     * 检查多选题是否正确并保存记录
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkSelectionIsCorrectAndSaveRecords(String userAnswer, QuestionInfo questionInfo, Integer userId) {
        List<String> userAnswerList = JSON.parseArray(userAnswer, String.class);
        Set userAnswerSet = new HashSet(userAnswerList);
        List<String> answerList = JSON.parseArray(questionInfo.getAnswer(), String.class);
        Set answerSet = new HashSet(answerList);

        if(SetUtil.isSetEqual(userAnswerSet, answerSet)) {
            try {
                questionInfoMapper.updateCorrectNumById(questionInfo.getId());
            } catch (Exception e) {
                logger.info("更新题目正确率失败", e);
            }
            return true;
        } else {
            try {
                WrongRecord wrongRecord = getWrongRecord(questionInfo, userId);
                questionInfoMapper.updateCountsById(questionInfo.getId());
                wrongRecordMapper.createRecord(wrongRecord);
            }catch (Exception e) {
                logger.info("更新题目做题总人数失败", e);
            }
            return false;
        }
    }

    /**
     * 检查单选题是否正确并保存记录
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkChoiceIsCorrectAndSaveRecords(String userAnswer, QuestionInfo questionInfo, Integer userId) {
        return checkFillingIsCorrectAndSaveRecords(userAnswer, questionInfo, userId);
    }

    private WrongRecord getWrongRecord(QuestionInfo questionInfo, Integer userId) {
        WrongRecord wrongRecord = new WrongRecord();
        wrongRecord.setCreateTime(DateUtil.getNow());
        wrongRecord.setUpdateTime(DateUtil.getNow());
        wrongRecord.setUserId(userId);
        wrongRecord.setQuestionId(questionInfo.getId());
        wrongRecord.setSubjectId(questionInfo.getSubjectId());
        wrongRecord.setWrongCounts(1);
        return wrongRecord;
    }

    /**
     * 检查填空题是否正确
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkFillingIsCorrect(String userAnswer, QuestionInfo questionInfo) {
        if (userAnswer.equals(questionInfo.getAnswer())) {
            return true;
        }
        return false;
    }


    /**
     * 检查多选题是否正确
     *
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkSelectionIsCorrect(String userAnswer, QuestionInfo questionInfo) {
        List<String> userAnswerList = JSON.parseArray(userAnswer, String.class);
        Set userAnswerSet = new HashSet(userAnswerList);
        List<String> answerList = JSON.parseArray(questionInfo.getAnswer(), String.class);
        Set answerSet = new HashSet(answerList);

        if (SetUtil.isSetEqual(userAnswerSet, answerSet)) {
            return true;
        }
        return false;
    }

    /**
     * 检查单选题是否正确并保存记录
     * @param userAnswer
     * @param questionInfo
     * @return
     */
    private Boolean checkChoiceIsCorrect(String userAnswer, QuestionInfo questionInfo) {
        return checkFillingIsCorrect(userAnswer, questionInfo);
    }


}
