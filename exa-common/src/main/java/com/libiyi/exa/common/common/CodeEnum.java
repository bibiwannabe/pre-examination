package com.libiyi.exa.common.common;

public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(1000, "成功"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(3000, "未知错误"),
    /**
     * 数据不合法
     */
    DATA_ILEAGLE(1001, "数据不合法"),
    /**
     * 用户未登录
     */
    NO_LOGIN(1002, "未登录");

    private Integer code;

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

    private String desc;

    CodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CodeEnum getEnum(Integer code) {
        switch (code){
            case 1000:
                return SUCCESS;
            case 1001:
                return DATA_ILEAGLE;
            case 3000:
                return UNKNOWN_ERROR;
            case 1002:
                return NO_LOGIN;
            default:
                return UNKNOWN_ERROR;
        }

    }
}
