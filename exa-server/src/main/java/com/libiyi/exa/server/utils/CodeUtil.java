package com.libiyi.exa.server.utils;


public class CodeUtil {

    public static int getCode(){
        return (int)((Math.random()*9+1)*1000);
    }
}
