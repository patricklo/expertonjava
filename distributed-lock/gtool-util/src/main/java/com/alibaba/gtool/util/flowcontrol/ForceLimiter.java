package com.alibaba.gtool.util.flowcontrol;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 强制限流
 *
 * @author gumao
 * @since 2016-11-01
 */
public class ForceLimiter {
    private static Logger logger = LoggerFactory.getLogger(ForceLimiter.class);
    //统计
    private LoadingCache<Long, AtomicLong> countMap = null;
    private int rangeMillis;//毫秒
    private int rangeSeconds;//秒

    /**
     * 强制限流
     */
    public ForceLimiter(long maxCount) {
        this(maxCount, 1);
    }

    /**
     * 强制限流
     */
    public ForceLimiter(long maxCount, int rangeSeconds) {
        this.rangeSeconds = rangeSeconds;
        this.rangeMillis = rangeSeconds * 1000;
        final long tmp = maxCount;
        int expire = rangeSeconds * 5;
        //统计
        this.countMap = CacheBuilder.newBuilder().concurrencyLevel(8).expireAfterWrite(expire, TimeUnit.SECONDS).build(
                new CacheLoader<Long, AtomicLong>() {
                    public AtomicLong load(Long key) throws Exception {
                        return new AtomicLong(tmp);
                    }
                }
        );
    }

    public String print() {
        long millis = System.currentTimeMillis();
        long index = this.currentIndex();
        long val = -100;
        try {
            val = this.countMap.get(index).get();
        } catch (Exception e) {
            logger.error("canGo ", e);
        }
        StringBuilder buf = new StringBuilder(200);
        buf.append(" rangeSeconds=").append(this.rangeSeconds);
        buf.append(" rangeMillis=").append(this.rangeMillis);
        buf.append(" currentMillis=").append(millis);
        buf.append(" currentIndex=").append(index);
        buf.append(" currentValue=").append(val);
        return buf.toString();
    }

    /**
     * 当前秒值
     */
    public long currentIndex() {
        long millis = System.currentTimeMillis();
        long num = millis / rangeMillis;
        return num;
    }

    /**
     * 是否可以运行
     */
    public boolean canGo() {
        long index = currentIndex();
        try {
            AtomicLong count = this.countMap.get(index);
            //如果没有令牌了,就返回
            if (count.get() <= 0) {
                return false;
            }
            //减一
            long countVal = count.getAndDecrement();
            if (countVal > 0) {
                return true;
            }
        } catch (Exception e) {
            logger.error("canGo ", e);
        }
        return false;
    }

    /**
     * 是否不可以运行
     */
    public boolean cannotGo() {
        return !this.canGo();
    }

    /**
     * 超限了,就休眠
     */
    public void sleepWhenCannotGo() {
        while (true) {
            boolean canGo = this.canGo();
            if (canGo) {
                break;
            }
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                logger.error("sleepWhenCannotGo ", e);
            }
        }
    }

}
