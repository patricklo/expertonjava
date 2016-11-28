package com.alibaba.gtool.xlock.common.task;

import java.util.concurrent.Future;
import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.manager.AtomicLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 心跳法,让一个线程一直持有一个锁。
 *
 * @author gumao
 * @since 2016-11-16
 */
public class AtomicLockRemainTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(AtomicLockRemainTask.class);
    private AtomicLockManager atomicLockManager;
    private AtomicLock atomicLock;
    private Thread thread;
    private Future future;

    public void run() {
        try {
            this.atomicLockManager.remainLock(this);
        } catch (Exception e) {
            logger.error("AtomicLockRemainTask", e);
        }
    }

    public AtomicLockManager getAtomicLockManager() {
        return atomicLockManager;
    }

    public void setAtomicLockManager(AtomicLockManager atomicLockManager) {
        this.atomicLockManager = atomicLockManager;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
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
