package com.libiyi.exa.admin.api.util;

import com.libiyi.exa.admin.api.param.PaperCreateParam;
import com.libiyi.exa.admin.api.param.PaperModifyParam;
import com.libiyi.exa.admin.api.param.PaperQuestionParam;


public class ParserPaperQuestionUtil {

    private static final String CHOICE = "choice";
    private static final String SELECTION = "selection";
    private static final String FILLING = "filling";
    private static final String POINT = "point";
    private static final String ID_LIST = "idList";

    public static String getQuestionList(PaperCreateParam paperCreateParam) {
        PaperQuestionParam choiceList = paperCreateParam.getChoiceList();
        PaperQuestionParam selectionList = paperCreateParam.getSelectionList();
        PaperQuestionParam fillingList = paperCreateParam.getFillingList();
        String questionList = "{\"" + CHOICE + "\":" + getQuestionModelStr(choiceList) + ",\"" + FILLING + "\":"
                + getQuestionModelStr(fillingList) + ",\"" + SELECTION + "\":" + getQuestionModelStr(selectionList) + "}";
        return questionList;
    }
    public static String getModifyQuestionList(PaperModifyParam paperModifyParam) {
        PaperQuestionParam choiceList = paperModifyParam.getChoiceList();
        PaperQuestionParam selectionList = paperModifyParam.getSelectionList();
        PaperQuestionParam fillingList = paperModifyParam.getFillingList();
        String questionList = "{\"" + CHOICE + "\":" + getQuestionModelStr(choiceList) + ",\"" + FILLING + "\":"
                + getQuestionModelStr(fillingList) + ",\"" + SELECTION + "\":" + getQuestionModelStr(selectionList) + "}";
        return questionList;
    }

    private static String getQuestionModelStr(PaperQuestionParam questionParam) {
        return "{\"" + POINT + "\":" + questionParam.getEachPoints() + ",\"" + ID_LIST + "\":" + questionParam.getQuestionIdList() + "}";
    }
}
