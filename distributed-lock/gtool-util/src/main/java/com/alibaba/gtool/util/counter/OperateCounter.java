package com.alibaba.gtool.util.counter;

import java.util.Date;
import java.util.Map;

/**
 * 操作次数统计
 *
 * @author gumao
 * @since 16/10/28
 */
public interface OperateCounter {
    /**
     * 输出统计
     */
    public String print(int count);

    /**
     * 当前单位时间数字
     */
    public long currentTimeNum();

    /**
     * 记录1次操作
     */
    public void touch();

    /**
     * 转成map值
     */
    public Map<Long, Long> toMap();

    /**
     * 单位时间转换成日期对象
     */
    public Date timeNumToDate(long timeNum);

    /**
     * 日期对象转换成单位时间
     */
    public Long dateToTimeNum(Date date);

    /**
     * 取过去时间的统计值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     * @return
     */
    public long pastCount(int pastIndex);

    /**
     * 取过去时间的统计值的每秒平均值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     */
    public long pastCountPerSecond(int pastIndex);

    /**
     * 取过去时间的统计值的每毫秒平均值
     *
     * @param pastIndex 过去的下标。当前是0,往前加1
     */
    public long pastCountPerMillis(int pastIndex);
}
