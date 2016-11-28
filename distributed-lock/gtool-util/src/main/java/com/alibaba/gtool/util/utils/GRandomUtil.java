package com.alibaba.gtool.util.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 随机工具类
 *
 * @author by gumao
 */
public class GRandomUtil {
    private GRandomUtil() {
    }

    private static String getNowDateTimeStr() {
        SimpleDateFormat form = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return form.format(new Date());
    }

    /**
     * 生成随机字符串。有时间前缀
     */
    public static String randomStringWithTime(int length) {
        return randomStringWithTime(GStringUtil.STRING_BIG_LETTER, length);
    }

    /**
     * 生成随机字符串。有时间前缀
     */
    public static String randomStringWithTime(String chars, int length) {
        String time = getNowDateTimeStr();
        if (length <= time.length()) {
            throw new IllegalArgumentException("param length must be bigger than " + time.length());
        }
        StringBuilder buff = new StringBuilder(length);
        buff.append(time);
        int left = length - time.length();
        String str2 = randomString(chars, left);
        buff.append(str2);
        return buff.toString();
    }

    /**
     * 生成随机字符串
     */
    public static String randomString(int length) {
        String str = randomString(GStringUtil.STRING_BASE, length);
        return str;
    }

    /**
     * 生成随机字符串
     */
    public static String randomString(String chars, int length) {
        StringBuilder buff = new StringBuilder(length);
        for (int i = 0; i < length; ++i) {
            Character ch = randomChar(chars);
            buff.append(ch);
        }
        String str = buff.toString();
        return str;
    }

    /**
     * 取单个字符
     */
    public static Character randomChar(String chars) {
        Double dd = Math.random();
        int index = (int) (dd * chars.length());
        return chars.charAt(index);
    }

    /**
     * 随机区间数字
     */
    public int randomRangeNum(int rangeMin, int rangeMax) {
        double ddd = Math.random();
        int off = rangeMax - rangeMin;
        int rand = (int) (ddd * off);
        return rangeMin + rand;
    }
}