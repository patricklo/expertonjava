package com.alibaba.gtool.xlock.common.bean;

/**
 * 分布式锁。指定锁的基础功能。
 *
 * @author gumao
 * @since 2016-11-15
 */
public interface XLock {
    /**
     * 尝试一次加锁。不阻塞,不等待。
     * 加锁成功返回true,否则返回false。
     */
    public boolean tryLock() throws Exception;

    /**
     * 尝试一次加锁。
     * 加锁成功,返回true。
     * 加锁失败,就等待最大时长。最终加锁成功,返回true;否则,返回false。
     */
    public boolean tryLock(long maxWaitMillis) throws Exception;

    /**
     * 加锁。
     * 加锁成功,返回true。
     * 否则,一直等待,直到加锁成功。
     */
    public void lock() throws Exception;

    /**
     * 解锁
     */
    public boolean unLock() throws Exception;

    /**
     * 锁的名称
     */
    public String getName();

    /**
     * 线程是否加锁成功
     */
    public boolean isThreadSuccess(Thread thread);

    /**
     * 添加一个事件监听器
     */
    public void addEventListener(XLockEventListener listener);

    /**
     * 删除一个事件监听器
     */
    public void removeEventListener(XLockEventListener listener);

}
