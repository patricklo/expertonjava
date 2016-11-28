package com.alibaba.gtool.xlock.common.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.bean.XLock;
import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;
import com.alibaba.gtool.xlock.common.task.AtomicLockFireEventTask;
import com.alibaba.gtool.xlock.common.task.AtomicLockRemainTask;
import com.alibaba.gtool.xlock.common.util.XlockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * atomic 锁管理器
 *
 * @author gumao
 * @since 2016-11-16
 */
public abstract class AtomicLockManager implements XLockManager {
    private static Logger logger = LoggerFactory.getLogger(AtomicLockManager.class);
    //心跳秒值
    private int heartbeatSeconds = 2;
    //会话秒值
    private int sessionSeconds = heartbeatSeconds * 10;
    //检查锁空闲的毫秒值
    private long checkLockFreeMillis = 200L;
    //锁名称和锁对象的映射
    private Map<String, AtomicLock> lockMap = new HashMap<String, AtomicLock>(20);
    //是否启用warn日志。很多应用把级别设置为warn。
    private boolean enableLogWarn = false;

    /**
     * 创建锁对象。
     */
    public XLock createLock(String lockName) {
        synchronized (this.lockMap) {
            AtomicLock lock = this.lockMap.get(lockName);
            if (null != lock) {
                return lock;
            }
            lock = new AtomicLock(lockName);
            lock.setAtomicLockManager(this);
            this.lockMap.put(lockName, lock);
            return lock;
        }
    }

    /**
     * 移除锁对象。由使用方自己调用,谨慎使用。
     */
    public boolean removeLock(AtomicLock lock) {
        synchronized (this.lockMap) {
            atomicKill(lock);
            this.lockMap.remove(lock.getName());
        }
        return true;
    }

    /**
     * 删除锁的处理
     */
    protected void atomicKill(AtomicLock lock) {
    }

    /**
     * 加锁。
     * 成功返回true,失败返回false
     */
    protected abstract boolean atomicLock(Thread thread, AtomicLock lock);

    /**
     * 让锁的有效期延长。
     * 返回线程是否有锁。
     */
    protected abstract boolean atomicTouch(Thread thread, AtomicLock lock);

    /**
     * 线程是否有锁。
     */
    protected abstract boolean atomicIsLocked(Thread thread, AtomicLock lock);

    /**
     * 锁是否被锁住了。
     */
    protected abstract boolean atomicIsLocked(AtomicLock lock);

    /**
     * 解锁
     * 成功返回true,失败返回false
     */
    protected abstract boolean atomicUnlock(Thread thread, AtomicLock lock);

    /**
     * 某线程尝试一次加锁。加锁成功返回true,否则返回false。
     */
    public boolean tryLock(Thread thread, AtomicLock lock) {
        boolean lockSuccess = false;
        String extra = "";
        //如果已经有锁了,就返回成功,不用重复加锁
        if (lock.isThreadSuccess(thread)) {
            lockSuccess = true;
            extra = "alreadyHaveLock . no need to lock again . ";
        } else {
            //加锁成功
            if (this.atomicLock(thread, lock)) {
                lockSuccess = true;
                //保持锁的任务
                lock.startRemainTask(thread);
                //设置线程拥有锁
                lock.markThreadSuccess(thread);
            } else {
                //设置线程没有锁
                lock.markThreadFail(thread);
            }
        }
        if (this.enableLogWarn) {
            StringBuilder buf = new StringBuilder(130);
            buf.append(" >> ").append(XlockUtil.nowDatetime()).append(" tryLock");
            buf.append(" lockResult=").append(lockSuccess);
            buf.append(" thread=").append(thread.getName());
            buf.append(" lock=").append(lock.getName());
            buf.append(" extra=").append(extra);
            logger.warn(buf.toString());
        }

        return lockSuccess;
    }

    /**
     * 加锁。里面用到了线程阻塞,必须用当前线程来调用此方法。
     * maxWaitMillis <= 0 时,加锁失败会一直阻塞。直到成功。
     * maxWaitMillis > 0 时,加锁失败会最多阻塞maxWaitMillis毫秒。
     *
     * @param lock          锁
     * @param maxWaitMillis 最大等待毫秒值
     * @return 加锁成功true, 加锁失败false
     * @throws Exception
     */
    public boolean tryLock(AtomicLock lock, long maxWaitMillis) throws Exception {
        Thread thread = Thread.currentThread();
        //最终的毫秒值
        long finalMillis = System.currentTimeMillis() + maxWaitMillis;
        //--------------------------------------------------------
        //--------------------------------------------------------
        try {
            //加锁
            lock.getReentrantLock().lock();
            //把线程加入锁的等待队列
            lock.addToWaitQueue(thread);
            //----------------------------------------------------------
            //无限阻塞
            if (maxWaitMillis <= 0) {
                while (true) {
                    //如果指定线程获得了锁,就成功
                    if (this.tryLock(thread, lock)) {
                        break;
                    }
                    //等待可以重试这个条件
                    lock.getCanTryLockCondition().await();
                }
                return true;
            }
            //----------------------------------------------------------
            //指定超时时间
            else {
                boolean lockSuccess = false;
                while (true) {
                    //如果指定线程获得了锁,就成功
                    if (this.tryLock(thread, lock)) {
                        lockSuccess = true;
                        break;
                    }
                    //可以休眠的毫秒值
                    long sleepMillis = finalMillis - System.currentTimeMillis();
                    //如果超过最大等待时间,就退出
                    if (sleepMillis <= 0) {
                        break;
                    }
                    //等待可以重试这个条件
                    lock.getCanTryLockCondition().await(sleepMillis, TimeUnit.MILLISECONDS);
                }
                return lockSuccess;
            }
            //----------------------------------------------------------
        } catch (Exception e) {
            logger.error("tryLock", e);
            throw e;
        } finally {
            // 把线程移出锁的等待队列
            lock.removeFromWaitQueue(thread);
            //解锁
            lock.getReentrantLock().unlock();
        }
    }

    /**
     * 构建日志。统一格式。
     */
    protected String buildLog(String title, Thread thread, AtomicLock lock, String extra) {
        StringBuilder buf = new StringBuilder(200);
        buf.append("\n ==============================");
        buf.append("\n ").append(title);
        buf.append("\n  now    =").append(XlockUtil.nowDatetime());
        if (null != thread) {
            buf.append("\n  thread =").append(thread.getName()).append("  ").append(thread.getId());
        }
        if (null != lock) {
            buf.append("\n  lock   =").append(lock.getName());
        }
        if (null != extra) {
            buf.append("\n  extra  =").append(extra);
        }
        buf.append("\n ==============================");
        return buf.toString();
    }

    /**
     * 解锁。必须是当前线程解锁。
     */
    public boolean unLock(AtomicLock lock) {
        Thread thread = Thread.currentThread();
        //如果已经没有该锁,就不能解锁
        if (!lock.isThreadSuccess(thread)) {
            String log = this.buildLog("AtomicLockManager unLock fail !", thread, lock,
                    "Current thread does not have this lock , so cannot unlock this lock .");
            logger.error(log);
            return false;
        }
        //解锁
        boolean result = this.atomicUnlock(thread, lock);
        //如果解锁成功
        if (result) {
            //设置当前线程没有锁
            lock.markThreadFail(thread);
            //停止持有锁
            lock.stopRemainTask(thread);
            //唤醒正在等待此锁的线程
            lock.signalAll();
        }
        return result;
    }


    /**
     * 保持锁。
     */
    public void remainLock(AtomicLockRemainTask task) {
        //锁对象
        AtomicLock lock = task.getAtomicLock();
        //指定的线程
        Thread thread = task.getThread();
        //----------------------------------------------------------
        // 该key是否被锁定
        boolean isLocked = this.atomicTouch(thread, lock);

        if (this.enableLogWarn) {
            StringBuilder buf = new StringBuilder(120);
            buf.append(" >> ").append(XlockUtil.nowDatetime()).append(" remainLock thread=").append(thread.getName()).append(" lock=").append(lock
                    .getName()).append(" isLocked=").append(isLocked);
            logger.warn(buf.toString());
        }
        //----------------------------------------------------------
        //如果指定的线程,不在加锁成功的线程队列中,就终止任务。这种情况在线程主动unlock后会发生。
        if (!lock.isThreadSuccess(thread)) {
            //终止任务
            task.getFuture().cancel(true);

            if (this.enableLogWarn) {
                String log = this.buildLog("AtomicLockRemainTask is cancelled  ", thread, lock,
                        "thread not lock success anymore , this may be normal !");
                logger.warn(log);
            }
            return;
        }
        //----------------------------------------------------------
        //如果没有锁。就尝试重新加锁。(正常情况不应该发生此事)
        if (!isLocked) {
            StringBuilder buf = new StringBuilder(100);
            //尝试重新加锁。加锁成功。
            if (this.atomicLock(thread, lock)) {
                buf.append(" >> retry lock success ! GOOD !");
                //加锁成功。
            }
            //加锁失败。
            else {
                buf.append(" >> retry lock fail , then cancel task , this may be normal ! Please find reason !");
                //标记线程加锁失败
                lock.markThreadFail(thread);
                //终止任务
                task.getFuture().cancel(true);
                //某线程被动丢失了锁
                AtomicLockFireEventTask task2 = new AtomicLockFireEventTask(XLockEventEnum.LOSE_LOCK, thread, lock);
                XlockUtil.getScheduler().execute(task2);
            }

            String log = this.buildLog("AtomicLockRemainTask  #  lose lock , so retry lock again ", thread, lock,
                    buf.toString());
            logger.error(log);
        }
    }

    /**
     * 创建线程的uuid,全局标识一个线程。
     * 必须全局唯一。
     */
    public String createThreadUUID(Thread thread, AtomicLock lock) {
        return UUID.randomUUID().toString();
    }

    /**
     * 如果锁空闲,就唤醒等待队列里面的线程,去尝试加锁
     */
    public void signalAllIfFree(AtomicLock atomicLock) {
        //锁是否被锁住了
        boolean isLocked = this.atomicIsLocked(atomicLock);
        //如果锁没有被锁,可以尝试加锁
        if (!isLocked) {
            //唤醒正在等待此锁的线程
            atomicLock.signalAll();
            //------------------------------------------------
            if (this.enableLogWarn) {
                StringBuilder buf = new StringBuilder(120);
                buf.append(" >> ").append(XlockUtil.nowDatetime()).append(" AtomicLockCheckFreeTask ").append(" lock=").append(atomicLock
                        .getName()).append(" isLocked=").append(isLocked).append(" . So signalAll to try lock !");
                logger.warn(buf.toString());
            }
        }
    }

    //--------------------------------------------------

    public int getHeartbeatSeconds() {
        return heartbeatSeconds;
    }

    public void setHeartbeatSeconds(int heartbeatSeconds) {
        this.heartbeatSeconds = heartbeatSeconds;
    }

    public int getSessionSeconds() {
        return sessionSeconds;
    }

    public void setSessionSeconds(int sessionSeconds) {
        this.sessionSeconds = sessionSeconds;
    }

    public boolean isEnableLogWarn() {
        return enableLogWarn;
    }

    public void setEnableLogWarn(boolean enableLogWarn) {
        this.enableLogWarn = enableLogWarn;
    }

    public long getCheckLockFreeMillis() {
        return checkLockFreeMillis;
    }

    public void setCheckLockFreeMillis(long checkLockFreeMillis) {
        this.checkLockFreeMillis = checkLockFreeMillis;
    }
}
