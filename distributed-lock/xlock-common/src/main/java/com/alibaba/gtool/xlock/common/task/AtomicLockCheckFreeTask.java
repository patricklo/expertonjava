package com.alibaba.gtool.xlock.common.task;

import java.util.concurrent.Future;
import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.manager.AtomicLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 检查锁是否空闲。如果空闲,就可以尝试加锁。
 *
 * @author gumao
 * @since 2016-11-16
 */
public class AtomicLockCheckFreeTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(AtomicLockCheckFreeTask.class);
    private AtomicLockManager atomicLockManager;
    private AtomicLock atomicLock;
    private Future future;

    public void run() {
        try {
            //如果锁空闲,就唤醒等待队列里面的线程,去尝试加锁
            this.atomicLockManager.signalAllIfFree(atomicLock);
        } catch (Exception e) {
            logger.error("AtomicLockCheckFreeTask", e);
        }
    }

    public AtomicLockManager getAtomicLockManager() {
        return atomicLockManager;
    }

    public void setAtomicLockManager(AtomicLockManager atomicLockManager) {
        this.atomicLockManager = atomicLockManager;
    }

    public AtomicLock getAtomicLock() {
        return atomicLock;
    }

    public void setAtomicLock(AtomicLock atomicLock) {
        this.atomicLock = atomicLock;
    }

    public Future getFuture() {
        return future;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

}
