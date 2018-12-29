package com.libiyi.exa.common.common;

public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(1000, "成功"),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(3000, "未知错误");

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
            case 3000:
                return UNKNOWN_ERROR;
            default:
                return UNKNOWN_ERROR;
        }

    }
}
