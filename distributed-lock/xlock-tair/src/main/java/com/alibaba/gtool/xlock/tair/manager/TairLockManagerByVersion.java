package com.alibaba.gtool.xlock.tair.manager;

import java.util.concurrent.atomic.AtomicInteger;
import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.util.XlockUtil;
import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tair 锁管理器(通过 version 实现)
 *
 * @author gumao
 * @since 2016-11-16
 */
public class TairLockManagerByVersion extends AbstractTairLockManager {
    private static Logger logger = LoggerFactory.getLogger(TairLockManagerByVersion.class);
    //除0和1之外的正整数。
    private static int VERSION_LOCK = 100;

    /**
     * 加锁。
     * 成功返回true,失败返回false
     */
    protected boolean atomicLock(Thread thread, AtomicLock lock) {
        //先看下是否已经有锁
        boolean lockSuccess = this.atomicIsLocked(thread, lock);
        //如果已经有锁,就直接返回成功
        if (lockSuccess) {
            return true;
        }
        //---------------------------------------------------
        String lockName = lock.getName();
        //线程的uuid
        String uuid = lock.findThreadUUID(thread);
        String val = uuid + "__" + XlockUtil.nowDatetime();
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                //用version put。当key不存在,此操作才会成功。
                //把uuid作为锁值,方便匹配线程。
                ResultCode ret = tairManager.put(tairNamespace, lockName, val, VERSION_LOCK, this.getSessionSeconds());
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(ret)) {
                    String log = this.buildLog(" Tair timeout ", thread, lock, "  " + ret);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (null != ret && ret.isSuccess()) {
                    //加锁成功
                    lockSuccess = true;
                } else {
                    //因为超时的原因,有可能tair已经加锁成功,但是返回值是失败,所以这里多校验一次。
                    lockSuccess = this.atomicIsLocked(thread, lock);
                }
                //---------------------------------------------------
                //如果加锁成功
                if (lockSuccess) {
                    //key被更新了一次,当前version是1
                    AtomicInteger version = new AtomicInteger(1);
                    //保存
                    lock.setThreadExtend(thread, version);
                    return true;
                }
                return false;
            } catch (Exception e) {
                logger.error("atomicLock", e);
                continue;
            }
        }
        return false;
    }

    /**
     * 让锁的有效期延长。
     * 返回线程是否有锁。
     */
    protected boolean atomicTouch(Thread thread, AtomicLock lock) {
        //线程是否有锁
        boolean hasLock2 = this.atomicIsLocked(thread, lock);
        //没有锁,就不能操作。
        if (!hasLock2) {
            return false;
        }
        //---------------------------------------------------
        String lockName = lock.getName();
        //线程对应的版本
        AtomicInteger version = (AtomicInteger) lock.getThreadExtend(thread);
        //没有version,表示已经主动解锁了
        if (null == version) {
            return false;
        }
        //线程的uuid
        String uuid = lock.findThreadUUID(thread);
        String val = uuid + "__" + XlockUtil.nowDatetime();
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                //用version put。version匹配成功,才能写成功
                //把uuid作为锁值,方便匹配线程。
                ResultCode ret = tairManager.put(tairNamespace, lockName, val, version.get(), this.getSessionSeconds());
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(ret)) {
                    String log = this.buildLog(" Tair timeout ", thread, lock, "  " + ret);
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (null != ret && ret.isSuccess()) {
                    //版本加1
                    version.incrementAndGet();
                    return true;
                } else {
                    //因为超时的原因,有可能tair已经更新成功,但是返回值是失败,所以这里多校验一次。
                    //线程是否有锁
                    boolean hasLock = this.atomicIsLocked(thread, lock);
                    if (hasLock) {
                        //取远程版本号
                        int remoteVersion = this.getTairElementVersion(lockName);
                        //更新版本号
                        version.set(remoteVersion);
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
     * 线程是否有锁。
     */
    protected boolean atomicIsLocked(Thread thread, AtomicLock lock) {
        String lockName = lock.getName();
        //线程的uuid
        String uuid = lock.findThreadUUID(thread);
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
                    String val = (String) entry.getValue();
                    //线程的uuid,匹配锁值。
                    if (null != val && val.startsWith(uuid)) {
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
                    String val = (String) entry.getValue();
                    //有值,就是锁被加了
                    if (null != val && val.length() > 0) {
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
        boolean unlockSuccess = false;
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                //先看线程是否有锁
                boolean hasLock = this.atomicIsLocked(thread, lock);
                //如果线程没有锁,就不能继续
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
                    unlockSuccess = true;
                } else {
                    //因为超时的原因,有可能tair已经操作成功,但是返回值是失败,所以这里多校验一次。
                    //线程是否有锁
                    boolean hasLock2 = this.atomicIsLocked(thread, lock);
                    // 如果线程已经没有锁了,表示解锁成功了。
                    if (!hasLock2) {
                        unlockSuccess = true;
                    }
                }
                //---------------------------------------------------
                //如果解锁成功
                if (unlockSuccess) {
                    //删除线程的uuid。下次加锁的时候,可以重新生成uuid。
                    lock.deleteThreadUUID(thread);
                    //删除线程对应的锁version。下次加锁的时候,可以重新生成version。
                    lock.deleteThreadExtend(thread);
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

}
