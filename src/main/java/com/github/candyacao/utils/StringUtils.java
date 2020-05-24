package com.github.candyacao.utils;

public class StringUtils {
    public static Boolean isEmpty(String str){
        if(null == str || "".equals(str)){
            return true;
        }else {
            return false;
        }
    }
}
