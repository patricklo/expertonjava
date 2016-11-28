package com.alibaba.gtool.xlock.common.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;
import com.alibaba.gtool.xlock.common.manager.AtomicLockManager;
import com.alibaba.gtool.xlock.common.task.AtomicLockCheckFreeTask;
import com.alibaba.gtool.xlock.common.task.AtomicLockFireEventTask;
import com.alibaba.gtool.xlock.common.task.AtomicLockRemainTask;
import com.alibaba.gtool.xlock.common.util.XlockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * atomic 锁对象。
 *
 * @author gumao
 * @since 2016-11-15
 */
public class AtomicLock implements XLock {
    private static Logger logger = LoggerFactory.getLogger(AtomicLock.class);
    //名称
    private String name;
    //锁管理器
    private AtomicLockManager atomicLockManager;
    //加锁成功的线程
    private Set<Thread> successThreads = new HashSet<Thread>(4);
    //等待队列
    private Set<Thread> waitQueue = new HashSet<Thread>(10);
    //并发锁
    private Lock reentrantLock = new ReentrantLock();
    //可以重试加锁的条件
    private Condition canTryLockCondition = reentrantLock.newCondition();
    //校验锁是否空闲
    private AtomicLockCheckFreeTask checkFreeTask = new AtomicLockCheckFreeTask();
    //事件监听器
    private Map<XLockEventEnum, Set<XLockEventListener>> eventListenerMap = new ConcurrentHashMap<XLockEventEnum, Set<XLockEventListener>>(8);
    //线程保持锁的任务
    private Map<Thread, AtomicLockRemainTask> remainTaskMap = new HashMap<Thread, AtomicLockRemainTask>();
    //线程的唯一键
    private Map<Thread, String> threadUUIDMap = new HashMap<Thread, String>();
    //线程的扩展数据
    private Map<Thread, Object> threadExtendMap = new HashMap<Thread, Object>();

    //--------------------------------------------------

    public AtomicLock(String name) {
        String regexp = "^[\\w\\d\\._/]*$";
        if (!name.matches(regexp)) {
            throw new IllegalArgumentException("AtomicLock name must match regexp " + regexp);
        }
        this.name = name;

        init();
    }

    /**
     * 某些初始化
     */
    private void init() {
        for (XLockEventEnum event : XLockEventEnum.values()) {
            eventListenerMap.put(event, new HashSet<XLockEventListener>(4));
        }
    }

    /**
     * 添加一个事件监听器
     */
    public void addEventListener(XLockEventListener listener) {
        Set<XLockEventListener> listeners = eventListenerMap.get(listener.getEvent());
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    /**
     * 删除一个事件监听器
     */
    public void removeEventListener(XLockEventListener listener) {
        Set<XLockEventListener> listeners = eventListenerMap.get(listener.getEvent());
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    /**
     * 触发某事件
     */
    public void fireEvent(XLockEventEnum event, Thread thread) {
        Set<XLockEventListener> listeners = eventListenerMap.get(event);
        synchronized (listeners) {
            for (XLockEventListener listener : listeners) {
                try {
                    listener.fire(event, thread, this);
                } catch (Exception e) {
                    logger.error("fireEvent", e);
                    continue;
                }
            }
        }
    }

    /**
     * 把线程放入等待队列
     */
    public void addToWaitQueue(Thread thread) {
        synchronized (this.waitQueue) {
            //加入等待队列
            this.waitQueue.add(thread);
            //如果为1,表示刚开始有线程在等待,就启动任务去检测锁是否空闲
            int size = this.waitQueue.size();
            if (1 == size) {
                //重试加锁的任务
                checkFreeTask.setAtomicLockManager(atomicLockManager);
                checkFreeTask.setAtomicLock(this);
                Future taskFuture = XlockUtil.getScheduler().scheduleAtFixedRate(checkFreeTask, atomicLockManager.getCheckLockFreeMillis(),
                        atomicLockManager.getCheckLockFreeMillis(), TimeUnit.MILLISECONDS);
                checkFreeTask.setFuture(taskFuture);
                if (this.atomicLockManager.isEnableLogWarn()) {
                    logger.warn(" >> " + XlockUtil.nowDatetime() + " waitQueue size is 1 , so start AtomicLockCheckFreeTask ");
                }
            }
        }
    }

    /**
     * 把线程移出等待队列
     */
    public void removeFromWaitQueue(Thread thread) {
        synchronized (this.waitQueue) {
            //移出等待队列
            this.waitQueue.remove(thread);
            //如果小于等于0,表示没有线程在等待,就不需要检查锁的状态
            int size = this.waitQueue.size();
            if (size <= 0) {
                //终止任务
                checkFreeTask.getFuture().cancel(true);
                if (this.atomicLockManager.isEnableLogWarn()) {
                    logger.warn(" >> " + XlockUtil.nowDatetime() + " waitQueue size is 0 , so stop AtomicLockCheckFreeTask ");
                }
            }
        }
    }

    /**
     * 标记线程加锁成功
     */
    public void markThreadSuccess(Thread thread) {
        synchronized (successThreads) {
            successThreads.add(thread);
        }
    }

    /**
     * 标记线程加锁失败
     */
    public void markThreadFail(Thread thread) {
        synchronized (successThreads) {
            successThreads.remove(thread);
        }
    }

    /**
     * 线程是否加锁成功
     */
    public boolean isThreadSuccess(Thread thread) {
        synchronized (successThreads) {
            return successThreads.contains(thread);
        }
    }

    /**
     * 触发某事件
     */
    private void fireEventTask(XLockEventEnum event, Thread thread, AtomicLock lock) {
        //如果没有监听器,就不需要触发
        Set<XLockEventListener> listeners = eventListenerMap.get(event);
        synchronized (listeners) {
            if (listeners.isEmpty()) {
                return;
            }
        }
        //异步执行事件触发器
        AtomicLockFireEventTask task = new AtomicLockFireEventTask(event, thread, lock);
        XlockUtil.getScheduler().execute(task);
    }


    /**
     * 尝试一次加锁。不阻塞,不等待。
     * 加锁成功返回true,否则返回false。
     */
    public boolean tryLock() throws Exception {
        Thread thread = Thread.currentThread();
        boolean result = this.atomicLockManager.tryLock(thread, this);
        if (result) {
            fireEventTask(XLockEventEnum.LOCK_SUCCESS, thread, this);
        } else {
            fireEventTask(XLockEventEnum.LOCK_FAIL, thread, this);
        }
        return result;
    }

    /**
     * 尝试一次加锁。
     * 加锁成功,返回true。
     * 加锁失败,就等待最大时长。最终加锁成功,返回true;否则,返回false。
     */
    public boolean tryLock(long maxWaitMillis) throws Exception {
        Thread thread = Thread.currentThread();
        boolean result = this.atomicLockManager.tryLock(this, maxWaitMillis);
        if (result) {
            fireEventTask(XLockEventEnum.LOCK_SUCCESS, thread, this);
        } else {
            fireEventTask(XLockEventEnum.LOCK_FAIL, thread, this);
        }
        return result;
    }

    /**
     * 加锁。
     * 加锁成功,返回true。
     * 否则,一直等待,直到加锁成功。
     */
    public void lock() throws Exception {
        this.atomicLockManager.tryLock(this, 0);
        Thread thread = Thread.currentThread();
        fireEventTask(XLockEventEnum.LOCK_SUCCESS, thread, this);
    }

    /**
     * 解锁
     */
    public boolean unLock() throws Exception {
        Thread thread = Thread.currentThread();
        boolean result = this.atomicLockManager.unLock(this);
        if (result) {
            fireEventTask(XLockEventEnum.UNLOCK_SUCCESS, thread, this);
        } else {
            fireEventTask(XLockEventEnum.UNLOCK_FAIL, thread, this);
        }
        return result;
    }

    /**
     * 唤醒正在等待此锁的线程
     */
    public void signalAll() {
        try {
            this.reentrantLock.lock();
            //唤醒等待线程
            this.canTryLockCondition.signalAll();
        } catch (Exception e) {
            logger.error("signalAll", e);
        } finally {
            this.reentrantLock.unlock();
        }
    }

    /**
     * 启动线程保持锁
     */
    public void startRemainTask(Thread thread) {
        synchronized (this.remainTaskMap) {
            AtomicLockRemainTask task = this.remainTaskMap.get(thread);
            if (null == task) {
                //保持锁的任务
                task = new AtomicLockRemainTask();
                task.setAtomicLockManager(this.atomicLockManager);
                task.setAtomicLock(this);
                task.setThread(thread);
                //放入缓存
                this.remainTaskMap.put(thread, task);
            }
            //定时器执行
            Future taskFuture = XlockUtil.getScheduler().scheduleAtFixedRate(task, 1, this.atomicLockManager.getHeartbeatSeconds(), TimeUnit.SECONDS);
            task.setFuture(taskFuture);
        }
    }

    /**
     * 停止线程保持锁
     */
    public void stopRemainTask(Thread thread) {
        synchronized (this.remainTaskMap) {
            AtomicLockRemainTask task = this.remainTaskMap.remove(thread);
            if (null != task) {
                //取消任务
                task.getFuture().cancel(true);
            }
        }
    }

    /**
     * 找到线程的唯一键
     */
    public String findThreadUUID(Thread thread) {
        synchronized (this.threadUUIDMap) {
            String uuid = this.threadUUIDMap.get(thread);
            if (null == uuid) {
                //创建线程的uuid
                uuid = this.atomicLockManager.createThreadUUID(thread, this);
                //放入缓存
                this.threadUUIDMap.put(thread, uuid);
            }
            return uuid;
        }
    }

    /**
     * 删除线程的唯一键
     */
    public void deleteThreadUUID(Thread thread) {
        synchronized (this.threadUUIDMap) {
            this.threadUUIDMap.remove(thread);
        }
    }

    /**
     * 线程的扩展对象
     */
    public void setThreadExtend(Thread thread, Object obj) {
        synchronized (this.threadExtendMap) {
            this.threadExtendMap.put(thread, obj);
        }
    }

    /**
     * 线程的扩展对象
     */
    public Object getThreadExtend(Thread thread) {
        synchronized (this.threadExtendMap) {
            return this.threadExtendMap.get(thread);
        }
    }

    /**
     * 线程的扩展对象
     */
    public void deleteThreadExtend(Thread thread) {
        synchronized (this.threadExtendMap) {
            this.threadExtendMap.remove(thread);
        }
    }

    /**
     * 锁的名称
     */
    public String getName() {
        return name;
    }

    public AtomicLockManager getAtomicLockManager() {
        return atomicLockManager;
    }

    public void setAtomicLockManager(AtomicLockManager atomicLockManager) {
        this.atomicLockManager = atomicLockManager;
    }

    public Lock getReentrantLock() {
        return reentrantLock;
    }

    public void setReentrantLock(Lock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    public Condition getCanTryLockCondition() {
        return canTryLockCondition;
    }

    public void setCanTryLockCondition(Condition canTryLockCondition) {
        this.canTryLockCondition = canTryLockCondition;
    }
}
