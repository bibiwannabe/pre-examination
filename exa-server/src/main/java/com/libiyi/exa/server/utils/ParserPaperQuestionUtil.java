package com.libiyi.exa.server.utils;

import com.alibaba.fastjson.JSON;
import com.libiyi.exa.common.common.QuestionTypeEnum;
import com.libiyi.exa.common.model.PaperQuestionListModel;
import com.libiyi.exa.common.model.PaperQuestionTypeModel;

import java.util.List;

public class ParserPaperQuestionUtil {

    private static final String CHOICE = "choice";
    private static final String SELECTION = "selection";
    private static final String FILLING = "filling";
    private static final String POINT = "point";
    private static final String ID_LIST = "idList";

    public static String addQuestion(Integer questionId, Integer questionType, String questionListStr) {
        PaperQuestionListModel questionListModel = JSON.parseObject(questionListStr, PaperQuestionListModel.class);
        switch (questionType){
            case 0:
                questionListModel.getChoice().getIdList().add(questionId);
            case 1:
                questionListModel.getSelection().getIdList().add(questionId);
            case 2:
                questionListModel.getFilling().getIdList().add(questionId);
        }
        String questionList = "{\"" + CHOICE + "\":" + getQuestionModelStr(questionListModel.getChoice()) + ",\"" + FILLING + "\":"
                + getQuestionModelStr(questionListModel.getFilling()) + ",\"" + SELECTION + "\":" + getQuestionModelStr(questionListModel.getSelection()) + "}";
        return questionList;
    }

    private static String getQuestionModelStr(PaperQuestionTypeModel questionParam) {
        return "{\"" + POINT + "\":" + questionParam.getPoint() + ",\"" + ID_LIST + "\":" + questionParam.getIdList() + "}";
    }


}
