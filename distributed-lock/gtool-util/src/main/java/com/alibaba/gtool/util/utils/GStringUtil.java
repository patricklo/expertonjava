package com.alibaba.gtool.util.utils;

/**
 * 字符串工具类
 *
 * @author gumao
 */
public class GStringUtil {
    /**
     * 数字
     */
    public static String STRING_NUM = "0123456789";
    /**
     * 小写字母
     */
    public static String STRING_SMALL_LETTER = "abcdefghijkmlnopqrstuvwxyz";
    /**
     * 大写字母
     */
    public static String STRING_BIG_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * 字母：小写字母和大写字母
     */
    public static String STRING_LETTER = STRING_SMALL_LETTER + STRING_BIG_LETTER;
    /**
     * 基础：数字，小写字母，大写字母
     */
    public static String STRING_BASE = STRING_NUM + STRING_SMALL_LETTER + STRING_BIG_LETTER;

    private GStringUtil() {
    }

}
