package com.alibaba.gtool.util.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author gumao
 * @since 2016-10-28
 */
public class GTimeUtil {
    // 按常用顺序排列
    public static String FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public static String FORMAT_2 = "yyyy-MM-dd";
    public static String FORMAT_3 = "HH:mm:ss";
    public static String FORMAT_4 = "yyyy-MM-dd HH:mm:ss,SSS";
    public static String FORMAT_5 = "yyyy年MM月dd日 HH:mm:ss";
    public static String FORMAT_6 = "yyyy年MM月dd日";
    public static String FORMAT_7 = "yyyyMMddHHmmssSSS";

    public static String nowDatetime() {
        return dateToString(new Date(), FORMAT_1);
    }

    public static String nowDateStr() {
        return dateToString(new Date(), FORMAT_2);
    }

    // -------------------------------------------------------------------
    // 日期时间到字符串
    public static String dateToString(Date date, String format) {
        if (null == format)
            format = FORMAT_1;
        SimpleDateFormat form = new SimpleDateFormat(format);
        return form.format(date);
    }

    // 字符串到日期时间
    public static Date stringToDate(String dateString, String format) {
        if (null == format)
            format = FORMAT_1;
        SimpleDateFormat form = new SimpleDateFormat(format);
        try {
            return form.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
