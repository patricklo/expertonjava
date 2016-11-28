package com.alibaba.gtool.util.counter;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import com.alibaba.gtool.util.utils.GTimeUtil;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作次数统计
 *
 * @author gumao
 * @since 2016-10-28
 */
public class OperateCounterBase implements OperateCounter {
    private static Logger logger = LoggerFactory.getLogger(OperateCounterBase.class);
    //统计
    private LoadingCache<Long, AtomicLong> countMap = null;
    private TimeUnit timeUnit;
    private int rangeMillis;//毫秒
    private int rangeSecond;//秒

    /**
     * 按N个时间单位统计。
     *
     * @param cacheValidTime 缓存有效期
     * @param rangeTime      每隔N个时间单位
     */
    public OperateCounterBase(TimeUnit timeUnit, int cacheValidTime, int rangeTime) {
        //统计
        this.countMap = CacheBuilder.newBuilder().concurrencyLevel(8).expireAfterWrite(cacheValidTime, timeUnit).build(
                new CacheLoader<Long, AtomicLong>() {
                    public AtomicLong load(Long key) throws Exception {
                        return new AtomicLong(0);
                    }
                }
        );
        this.timeUnit = timeUnit;
        //1个单位对应的毫秒值
        long millis = timeUnit.toMillis(1);
        //N个区间的毫秒值
        this.rangeMillis = (int) (rangeTime * millis);
        this.rangeSecond = this.rangeMillis / 1000;
    }

    private OperateCounterBase() {
    }

    /**
     * 按N小时统计。
     *
     * @param cacheValidHours 缓存有效期
     * @param rangeHours      每隔几小时
     */
    public static OperateCounter byHour(int cacheValidHours, int rangeHours) {
        return new OperateCounterBase(TimeUnit.HOURS, cacheValidHours, rangeHours);
    }

    /**
     * 按1小时统计。
     *
     * @param cacheValidHours 缓存有效期
     */
    public static OperateCounter byHour(int cacheValidHours) {
        return byHour(cacheValidHours, 1);
    }

    /**
     * 按N分钟统计。
     *
     * @param cacheValidMinutes 缓存有效期
     * @param rangeMinutes      每隔几分钟
     */
    public static OperateCounter byMinute(int cacheValidMinutes, int rangeMinutes) {
        return new OperateCounterBase(TimeUnit.MINUTES, cacheValidMinutes, rangeMinutes);
    }

    /**
     * 按1分钟统计。
     *
     * @param cacheValidMinutes 缓存有效期
     */
    public static OperateCounter byMinute(int cacheValidMinutes) {
        return byMinute(cacheValidMinutes, 1);
    }

    /**
     * 按N秒统计。
     *
     * @param cacheValidSeconds 缓存有效期
     * @param rangeSeconds      每隔几秒
     */
    public static OperateCounter bySecond(int cacheValidSeconds, int rangeSeconds) {
        return new OperateCounterBase(TimeUnit.SECONDS, cacheValidSeconds, rangeSeconds);
    }

    /**
     * 按1秒统计。
     *
     * @param cacheValidSeconds 缓存有效期
     */
    public static OperateCounter bySecond(int cacheValidSeconds) {
        return bySecond(cacheValidSeconds, 1);
    }

    //-------------------------------------------------------------------

    /**
     * 输出统计
     */
    public String print(int count) {
        StringBuilder buf = new StringBuilder(count * 200);
        buf.append("\n OperateCounter TimeUnit=").append(this.timeUnit.name());
        buf.append(" rangeSecond=").append(this.rangeSecond);
        buf.append(" rangeMillis=").append(this.rangeMillis);
        long timeNum = currentTimeNum();
        try {
            for (int i = 1; i <= count; ++i) {
                timeNum = timeNum - 1;
                //计数值
                long countVal = countMap.get(timeNum).get();
                //时间
                Date time = this.timeNumToDate(timeNum);
                String timeStr = GTimeUtil.dateToString(time, GTimeUtil.FORMAT_1);
                buf.append("\n  >  Index=").append(timeNum);
                buf.append("  Time=").append(timeStr);
                buf.append("  Count=").append(countVal);
                buf.append("  PerSecond=").append(countVal / this.rangeSecond);
                buf.append("  PerMillis=").append(countVal / this.rangeMillis);
            }
            return buf.toString();
        } catch (Exception e) {
            logger.error("print ", e);
        }
        return "";
    }

    /**
     * 取过去时间的统计值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     */
    public long pastCount(int pastIndex) {
        long timeNum = currentTimeNum();
        long timeNum2 = timeNum - pastIndex;
        long countVal = 0;
        try {
            //计数值
            countVal = countMap.get(timeNum2).get();
        } catch (Exception e) {
            logger.error("pastCount ", e);
        }
        return countVal;
    }

    /**
     * 取过去时间的统计值的每秒平均值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     */
    public long pastCountPerSecond(int pastIndex) {
        long count = pastCount(pastIndex);
        return count / this.rangeSecond;
    }

    /**
     * 取过去时间的统计值的每毫秒平均值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     */
    public long pastCountPerMillis(int pastIndex) {
        long count = pastCount(pastIndex);
        return count / this.rangeMillis;
    }

    /**
     * 当前单位时间数字
     */
    public long currentTimeNum() {
        long millis = System.currentTimeMillis();
        long num = millis / rangeMillis;
        return num;
    }

    /**
     * 记录操作
     */
    public void touch() {
        long num = currentTimeNum();
        try {
            countMap.get(num).incrementAndGet();
        } catch (Exception e) {
            logger.error("incr ", e);
        }
    }

    /**
     * 单位时间转换成日期对象
     */
    public Date timeNumToDate(long timeNum) {
        long theMillis = timeNum * rangeMillis;
        Date time = new Date(theMillis);
        return time;
    }

    /**
     * 日期对象转换成单位时间
     */
    public Long dateToTimeNum(Date date) {
        long millis = date.getTime();
        long timeNum = millis / rangeMillis;
        return timeNum;
    }

    /**
     * 转成map值
     */
    public Map<Long, Long> toMap() {
        Map<Long, AtomicLong> map = this.countMap.asMap();
        Map<Long, Long> map2 = new TreeMap<Long, Long>();
        for (Long key : map.keySet()) {
            Long val = map.get(key).get();
            map2.put(key, val);
        }
        return map2;
    }
}
