package com.alibaba.gtool.xlock.common.bean;

import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;

/**
 * 分布式锁的事件监听器
 *
 * @author gumao
 * @since 2016-11-20
 */
public interface XLockEventListener {
    /**
     * 事件
     */
    public XLockEventEnum getEvent();

    /**
     * 触发监听器
     */
    public void fire(XLockEventEnum event, Thread thread, XLock xlock);

}
