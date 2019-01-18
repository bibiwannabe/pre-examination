package com.libiyi.exa.common.util;

import com.libiyi.exa.common.thrift.TPCreatPracticeRecordParam;

public class ParseUserAnswer {
    public static String getUserAnswerStr(TPCreatPracticeRecordParam practiceParam) {
        return "{\"choice\":"+practiceParam.getChoice().toString()+",\"selection\":"
                +practiceParam.getSelection().toString()+",\"filling\":"+practiceParam.getFilling().toString()+"}";
    }
}
