package com.alibaba.gtool.xlock.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author gumao
 * @since 2016-11-16
 */
public class XlockUtil {

    private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }

    /**
     * 当然时间
     */
    public static String nowDatetime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        Date now = new Date();
        return format.format(now);
    }
}
