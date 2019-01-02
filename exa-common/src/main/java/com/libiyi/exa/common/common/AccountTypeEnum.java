package com.libiyi.exa.common.common;

public enum AccountTypeEnum {
    /**
     * 学生账号
     */
    STUDENT(0, "学生账号"),

    /**
     * 教师账号
     */
    TEACHER(1, "教师账号");

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

    private Integer code;

    private String desc;

    AccountTypeEnum(Integer code, String desc) {
    }

    public static AccountTypeEnum getByCode(Integer code) {
       switch (code){
           case 0:
               return STUDENT;
           case 1:
               return TEACHER;
           default:
               return STUDENT;
       }
    }
}
