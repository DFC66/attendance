package com.dfc.utils;

import com.twmacinta.util.MD5;

/**
 * 32位小写 MD5加密工具类
 */

public class ToDd5 {
    public static String encode(String password){
        MD5 m = new MD5(password);
        return m.asHex();
    }

    public static void main(String[] args) {
        String encode = encode("123");
        System.out.println(encode);
    }
}
