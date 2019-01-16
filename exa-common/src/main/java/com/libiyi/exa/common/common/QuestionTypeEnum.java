package com.libiyi.exa.common.common;

public enum QuestionTypeEnum {
    /**
     * 单选题
     */
    MULTIPLE_CHOICE(0, "单选题"),

    /**
     * 多选题
     */
    MULTIPLE_SELECTION(1, "多选题"),

    /**
     * 填空题
     */
    BLANK_FILLING(2, "填空题");

    private Integer code;

    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    QuestionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static QuestionTypeEnum getByCode(Integer code) {
        switch (code){
            case 0:
                return MULTIPLE_CHOICE;
            case 1:
                return MULTIPLE_SELECTION;
            case 3:
                return BLANK_FILLING;
            default:
                return MULTIPLE_CHOICE;
        }
    }
}
