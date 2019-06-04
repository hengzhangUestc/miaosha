package com.imooc.miaosha.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.thymeleaf.util.StringUtils;

public class ValidatorUtil {

    private static final Pattern PATTERN = Pattern.compile("1\\d{10}");

    public static boolean isMobile(String src) {
        if (StringUtils.isEmpty(src)) {
            return false;
        }

        Matcher m = PATTERN.matcher(src);

        return m.matches();
    }

       /* public static void main (String[] args) {

            System.out.println(isMobile("12345678909"));
            System.out.println(isMobile("1234567890"));
        }*/

}
