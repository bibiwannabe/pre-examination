package com.libiyi.exa.common.common;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private int code;

    private String message = "";

    private T data;

    public static class Builder<T> {

        private Result<T> result;

        public Builder(){
            result = new Result<T>();
        }

        public Builder(T data){
            result = new Result<T>();
            result.setData(data);
        }

        public Builder<T> setCode(int code){
            result.setCode(code);
            result.setMessage(CodeEnum.getEnum(code).getDesc());
            return this;
        }

        public Builder<T> setData(T data){
            result.setData(data);
            return this;
        }

        public Builder<T> setMessage(String message){
            result.setMessage(message);
            return this;
        }

        public Result<T> build(){
            return result;
        }

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
