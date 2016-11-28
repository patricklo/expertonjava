package com.alibaba.gtool.util.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import com.alibaba.gtool.util.utils.GTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共的线程工厂
 *
 * @author gumao
 * @since 2016/11/1
 */
public class BaseThreadFactory implements ThreadFactory {
    private static final Logger logger = LoggerFactory.getLogger(BaseThreadFactory.class);
    private AtomicInteger count = new AtomicInteger(1);//从1开始
    private String namePrefix = "unnamed-";

    public BaseThreadFactory(String namePrefix) {
        this.namePrefix = namePrefix;
    }

    public Thread newThread(Runnable r) {
        //线程名称
        int num = this.count.getAndIncrement();
        String name = this.namePrefix + num;
        Thread th = new Thread(r, name);
        //日志
        StringBuilder buf = new StringBuilder(200);
        buf.append("\n===========================================");
        buf.append("\n   BaseThreadFactory  #  newThread  ");
        buf.append("\n   now        = ").append(GTimeUtil.nowDatetime());
        buf.append("\n   currentNum = ").append(num);
        buf.append("\n   threadId   = ").append(th.getId());
        buf.append("\n   threadName = ").append(th.getName());
        buf.append("\n===========================================");
        logger.info(buf.toString());
        return th;
    }
}
