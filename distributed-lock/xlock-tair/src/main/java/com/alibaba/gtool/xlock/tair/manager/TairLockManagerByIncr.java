package com.alibaba.gtool.xlock.tair.manager;

import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tair 锁管理器(通过 incr 实现)
 *
 * @author gumao
 * @since 2016-11-16
 */
public class TairLockManagerByIncr extends AbstractTairLockManager {
    private static Logger logger = LoggerFactory.getLogger(TairLockManagerByIncr.class);
    private static int INCR_DEFAULT = 0;
    private static int LOCK_INCR_MIN = INCR_DEFAULT;
    private static int LOCK_INCR_MAX = 5;


    /**
     * 加锁。
     * 成功返回true,失败返回false
     */
    protected boolean atomicLock(Thread thread, AtomicLock lock) {
        //原子加1
        Integer val = this.tairIncr(lock.getName(), 1);
        //从0到1,表示加锁成功。其他,表示失败。
        if (1 == val) {
            return true;
        }
        return false;
    }

    /**
     * 让锁的有效期延长。
     * 返回线程是否有锁。
     */
    protected boolean atomicTouch(Thread thread, AtomicLock lock) {
        //加0。
        Integer val = this.tairIncr(lock.getName(), 0);
        //大于0,表示有锁
        if (val > 0) {
            return true;
        }
        return false;
    }

    /**
     * 线程是否有锁。
     */
    protected boolean atomicIsLocked(Thread thread, AtomicLock lock) {
        String lockName = lock.getName();
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                Result<DataEntry> result = tairManager.get(tairNamespace, lockName);
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(result)) {
                    String log = this.buildLog(" Tair timeout ", thread, lock, "  " + result);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (null != result && null != result.getValue()) {
                    DataEntry entry = result.getValue();
                    Integer val = (Integer) entry.getValue();
                    if (null != val && val > 0) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                logger.error("atomicIsLocked", e);
                continue;
            }
        }
        return false;
    }

    /**
     * 锁是否被锁住了。
     */
    protected boolean atomicIsLocked(AtomicLock lock) {
        String lockName = lock.getName();
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                Result<DataEntry> result = tairManager.get(tairNamespace, lockName);
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(result)) {
                    String log = this.buildLog(" Tair timeout ", null, lock, "  " + result);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (null != result && null != result.getValue()) {
                    DataEntry entry = result.getValue();
                    Integer val = (Integer) entry.getValue();
                    //值为正,就是锁被加了
                    if (null != val && val > 0) {
                        return true;
                    }
                }
                return false;
            } catch (Exception e) {
                logger.error("atomicIsLocked", e);
                continue;
            }
        }
        return false;
    }

    /**
     * 解锁
     * 成功返回true,失败返回false
     */
    protected boolean atomicUnlock(Thread thread, AtomicLock lock) {
        String lockName = lock.getName();
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                //先看线程是否有锁
                boolean hasLock = this.atomicIsLocked(thread, lock);
                //如果线程没有锁,就失败
                if (!hasLock) {
                    return false;
                }
                //----------------------------------------------------------
                //删除锁
                ResultCode result = tairManager.invalid(this.tairNamespace, lockName);
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(result)) {
                    String log = this.buildLog(" Tair timeout ", thread, lock, "  " + result);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (result != null && result.isSuccess()) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                logger.error("atomicUnlock", e);
                continue;
            }
        }
        return false;
    }

    private Integer tairIncr(String key, int value) {
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                //原子加数
                Result<Integer> result = this.tairManager.incr(this.tairNamespace, key, value, INCR_DEFAULT, this.getSessionSeconds(),
                        LOCK_INCR_MIN, LOCK_INCR_MAX);
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(result)) {
                    String log = this.buildLog(" Tair timeout ", null, null, "  " + result);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (result != null && result.isSuccess()) {
                    return result.getValue();
                }
                return INCR_DEFAULT;
            } catch (Exception e) {
                logger.error("tairManager # incr", e);
                continue;
            }
        }
        return INCR_DEFAULT;
    }

}


//   private static int UUID_INCR_MIN = LOCK_INCR_MIN + 1;
//    private static int UUID_INCR_MAX = LOCK_INCR_MAX - 1;
/**
 * 创建线程的uuid,全局标识一个线程。
 * 必须全局唯一。
 */
//    public String createThreadUUID(Thread thread, AtomicLock lock) {
//        //uuid的序列。每个锁对应一个序列,防止单点压力。
//        String key = lock.getName() + "_thread_uuid";
//        for (int i = 1; i <= tairRetryTimes; ++i) {
//            try {
//                //原子加数
//                Result<Integer> result = this.tairManager.incr(this.tairNamespace, key, 1, INCR_DEFAULT, 0, UUID_INCR_MIN, UUID_INCR_MAX);
//                if (result != null) {
//                    //如果成功
//                    if (result.isSuccess()) {
//                        //uuid值
//                        Integer uuidNum = result.getValue();
//                        if (null == uuidNum) {
//                            continue;
//                        }
//                        //返回uuid
//                        String uuid = uuidNum.toString();
//                        logger.error(">>>>>>>>>>>>>>>    thread=" + thread.getName() + "   uuid=" + uuid);
//                        return uuid;
//                    }
//                    //如果失败
//                    else {
//                        //如果是超过了范围值,就重置uuid序列。(这种情况在序列用完时会发生)
//                        ResultCode code = result.getRc();
//                        if (ResultCode.COUNTER_OUT_OF_RANGE == code) {
//                            //重置uuid序列
//                            tairManager.invalid(this.tairNamespace, key);
//
//                            String extra = " COUNTER_OUT_OF_RANGE .  uuidKey=" + key + " . So invalid it . ";
//                            this.buildLog(" TairLockManager # createThreadUUID  COUNTER_OUT_OF_RANGE !", thread, lock, extra);
//                            continue;
//                        }
//                    }
//                }
//            } catch (Exception e) {
//                logger.error("tairManager # createThreadUUID", e);
//                continue;
//            }
//        }
//        String extra = " cannot create uuid . uuidKey=" + key + " . Please find reason !";
//        this.buildLog(" TairLockManager # createThreadUUID  fail !", thread, lock, extra);
//        return null;
//    }
