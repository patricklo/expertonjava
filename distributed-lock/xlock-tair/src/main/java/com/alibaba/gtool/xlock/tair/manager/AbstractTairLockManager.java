package com.alibaba.gtool.xlock.tair.manager;

import com.alibaba.gtool.xlock.common.manager.AtomicLockManager;
import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * tair 锁管理器
 *
 * @author gumao
 * @since 2016-11-16
 */
public abstract class AbstractTairLockManager extends AtomicLockManager {
    private static Logger logger = LoggerFactory.getLogger(AbstractTairLockManager.class);
    //超时次数的最大值
    private static int maxTimeoutTimes = 3;
    //tair namespace
    protected int tairNamespace;
    //建议使用ldb。不能使用mdb。
    protected TairManager tairManager;
    //重试次数
    protected int tairRetryTimes = 3;

    /**
     * 是否超时
     */
    protected boolean isTimeout(Result result) {
        if (result == null) {
            return true;
        }
        ResultCode code = result.getRc();
        return this.isTimeout(code);
    }

    /**
     * 是否超时
     */
    protected boolean isTimeout(ResultCode code) {
        if (ResultCode.CONNERROR.equals(code) || ResultCode.TIMEOUT.equals(code) || ResultCode.UNKNOW.equals(code)) {
            return true;
        }
        return false;
    }

    /**
     * 超时发生时,是否应该继续。
     */
    protected boolean shouldTimeoutContinue(Result result, Integer timeoutCount) {
        if (this.isTimeout(result)) {
            timeoutCount++;
            if (timeoutCount > maxTimeoutTimes) {
                String log = ">>   Tair timeoutCount beyond .  maxTimeoutTimes=" + maxTimeoutTimes + "  timeoutCount="
                        + timeoutCount + "  Result=" + result;
                logger.error(log);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 超时发生时,是否应该继续。
     */
    protected boolean shouldTimeoutContinue(ResultCode code, Integer timeoutCount) {
        if (this.isTimeout(code)) {
            timeoutCount++;
            if (timeoutCount > maxTimeoutTimes) {
                String log = ">>   Tair timeoutCount beyond .  maxTimeoutTimes=" + maxTimeoutTimes + "  timeoutCount="
                        + timeoutCount + "  ResultCode=" + code;
                logger.error(log);
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 取版本号
     */
    protected int getTairElementVersion(String key) {
        for (int i = 1; i <= tairRetryTimes; ++i) {
            try {
                Result<DataEntry> result = tairManager.get(tairNamespace, key);
                //---------------------------------------------------
                //判断超时
                if (this.isTimeout(result)) {
                    String log = " Tair timeout  key=" + key + "  Result=" + result;
                    logger.error(log);
                    continue;
                }
                //---------------------------------------------------
                if (null != result && null != result.getValue()) {
                    DataEntry entry = result.getValue();
                    int version = entry.getVersion();
                    return version;
                } else {
                    break;
                }
            } catch (Exception e) {
                logger.error("getTairElementVersion", e);
                continue;
            }
        }
        String log = " Tair getTairElementVersion Error !  key=" + key;
        logger.error(log);
        return -1;
    }

    public int getTairNamespace() {
        return tairNamespace;
    }

    public void setTairNamespace(int tairNamespace) {
        this.tairNamespace = tairNamespace;
    }

    public TairManager getTairManager() {
        return tairManager;
    }

    public void setTairManager(TairManager tairManager) {
        this.tairManager = tairManager;
    }

    public int getTairRetryTimes() {
        return tairRetryTimes;
    }

    public void setTairRetryTimes(int tairRetryTimes) {
        this.tairRetryTimes = tairRetryTimes;
    }
}
