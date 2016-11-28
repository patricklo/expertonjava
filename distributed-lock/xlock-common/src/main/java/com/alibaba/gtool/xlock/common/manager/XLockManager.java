package com.alibaba.gtool.xlock.common.manager;

import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.bean.XLock;

/**
 * 分布式锁的管理器
 *
 * @author gumao
 * @since 2016-11-21
 */
public interface XLockManager {
    /**
     * 创建锁对象。
     */
    public XLock createLock(String lockName);

    /**
     * 移除锁对象。由使用方自己调用,谨慎使用。
     */
    public boolean removeLock(AtomicLock lock);

}
