package com.libiyi.exa.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.libiyi.exa.common.model.RecordAnswerModel;
import com.libiyi.exa.common.thrift.TPCreatPracticeRecordParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class ParseUserAnswer {
    public static String getUserAnswerStr(TPCreatPracticeRecordParam practiceParam) {
        RecordAnswerModel recordAnswerModel = new RecordAnswerModel();
        recordAnswerModel.setChoice(practiceParam.getChoice());
        recordAnswerModel.setFilling(practiceParam.getFilling());
        recordAnswerModel.setSelection(practiceParam.getSelection());
        return JSON.toJSONString(recordAnswerModel);
    }

    public static TPCreatPracticeRecordParam getTPCreatPracticeRecordParam(String answer) {
        TPCreatPracticeRecordParam tpCreatPracticeRecordParam = new TPCreatPracticeRecordParam();
        RecordAnswerModel recordAnswerModel = JSON.parseObject(answer, RecordAnswerModel.class);
        tpCreatPracticeRecordParam.setChoice(recordAnswerModel.getChoice());
        tpCreatPracticeRecordParam.setSelection(recordAnswerModel.getSelection());
        tpCreatPracticeRecordParam.setFilling(recordAnswerModel.getFilling());
        return tpCreatPracticeRecordParam;
    }
}
