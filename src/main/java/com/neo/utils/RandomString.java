package com.neo.utils;

import java.util.Random;

public class RandomString {

    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    public static String getRandomStringSingalNumber(int length){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int number = random.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
}
