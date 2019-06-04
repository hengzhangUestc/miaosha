package com.imooc.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5util {

    public static String md5 (String src) {
        return DigestUtils.md2Hex(src);
    }

    private static final String salt = "1a2b3c4d";
    public  static String inputPassToFromPass (String inputPass) {
        String str =salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(4) + salt.charAt(5);
        return md5(str);
    }
    public  static String fromPassToDBPass (String inputPass , String salt) {
        String str =salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(4) + salt.charAt(5);
        return md5(str);
    }

    public  static  String inputPassToDBPass (String inputPass , String DBsalt)  {
        String fromPass =inputPassToFromPass(inputPass);
        String dbPass = fromPassToDBPass(fromPass, DBsalt);
        return dbPass;

    }

    public static void main (String[] args) {
//        System.out.println(inputPassToFromPass("123456"));
//        System.out.println(fromPassToDBPass((inputPassToFromPass("123456")),"1a2b3c4d"));
          System.out.println(inputPassToDBPass("123456","1a2b3c4d5e"));
    }
}
