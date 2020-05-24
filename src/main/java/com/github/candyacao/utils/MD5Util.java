package com.github.candyacao.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class MD5Util {
    /**
     * 计算字符串的MD5值
     *
     * @param string 明文
     * @return 字符串的MD5值
     */
    public static String md5(String input) {
        return Hashing.md5().hashBytes(input.getBytes(Charset.forName("UTF-8"))).toString();
    }
}
