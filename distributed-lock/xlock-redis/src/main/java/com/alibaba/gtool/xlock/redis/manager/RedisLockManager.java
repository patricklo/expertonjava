package com.alibaba.gtool.xlock.redis.manager;

import com.alibaba.gtool.xlock.common.bean.AtomicLock;
import com.alibaba.gtool.xlock.common.manager.AtomicLockManager;
import com.alibaba.gtool.xlock.common.util.XlockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 锁管理器
 *
 * @author gumao
 * @since 2016-11-16
 */
public class RedisLockManager extends AtomicLockManager {
    private static Logger logger = LoggerFactory.getLogger(RedisLockManager.class);
    private static String REDIS_OK = "OK";
    //redis 客户端池,线程安全
    private JedisPool jedisPool;
    //redis 库
    private int jedisDBIndex = 0;
    //重试次数
    private int redisRetryTimes = 3;

    //状态是成功
    private static boolean isRedisSuccess(String statusCode) {
        if (null != statusCode && REDIS_OK.equals(statusCode)) {
            return true;
        }
        return false;
    }

/**
 * public String set(final String key, final String value, final String nxxx, final String expx, final long time)
 *
 * NX -- Only set the key if it does not already exist.
 * XX -- Only set the key if it already exist.
 *
 * EX = seconds
 * PX = milliseconds
 */

    /**
     * 加锁。
     * 成功返回true,失败返回false
     */
    protected boolean atomicLock(Thread thread, AtomicLock lock) {
        Jedis jedis = null;
        try {
            String uuid = lock.findThreadUUID(thread);
            jedis = jedisPool.getResource();
            jedis.select(this.jedisDBIndex);
            String lockName = lock.getName();
            for (int i = 1; i <= redisRetryTimes; ++i) {
                try {
                    //NX,如果key不存在,才能set成功。
                    String val = uuid + "__" + XlockUtil.nowDatetime();
                    //更新值和过期时间
                    String ret = jedis.set(lockName, val, "NX", "EX", this.getSessionSeconds());
                    //操作是否成功
                    boolean isSucc = isRedisSuccess(ret);
                    return isSucc;
                } catch (Exception e) {
                    logger.error("atomicLock", e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("jedis", e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    /**
     * 让锁的有效期延长。
     * 返回线程是否有锁。
     */
    protected boolean atomicTouch(Thread thread, AtomicLock lock) {
        Jedis jedis = null;
        try {
            String uuid = lock.findThreadUUID(thread);
            jedis = jedisPool.getResource();
            jedis.select(this.jedisDBIndex);
            String lockName = lock.getName();
            for (int i = 1; i <= redisRetryTimes; ++i) {
                try {
                    //先校验当前线程是否有锁
                    boolean hasLock = this.atomicIsLocked(thread, lock);
                    //没有锁就不能后续操作
                    if (!hasLock) {
                        return false;
                    }
                    //-------------------------------------------------
                    //XX,如果key已经存在,才能set成功。
                    String val = uuid + "__" + XlockUtil.nowDatetime();
                    //更新值和过期时间
                    String ret = jedis.set(lockName, val, "XX", "EX", this.getSessionSeconds());
                    //操作是否成功
                    boolean isSucc = isRedisSuccess(ret);
                    return isSucc;
                } catch (Exception e) {
                    logger.error(" xx", e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("jedis", e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    /**
     * 线程是否有锁。
     */
    protected boolean atomicIsLocked(Thread thread, AtomicLock lock) {
        Jedis jedis = null;
        try {
            String uuid = lock.findThreadUUID(thread);
            jedis = jedisPool.getResource();
            jedis.select(this.jedisDBIndex);
            String lockName = lock.getName();
            for (int i = 1; i <= redisRetryTimes; ++i) {
                try {
                    //有值就表示被锁住了
                    String val = jedis.get(lockName);
                    if (null != val) {
                        //如果是线程uuid前缀,表示线程拥有锁
                        if (val.startsWith(uuid)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Exception e) {
                    logger.error("atomicIsLocked", e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("jedis", e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    /**
     * 锁是否被锁住了。
     */
    protected boolean atomicIsLocked(AtomicLock lock) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(this.jedisDBIndex);
            String lockName = lock.getName();
            for (int i = 1; i <= redisRetryTimes; ++i) {
                try {
                    //有值就表示被锁住了
                    String val = jedis.get(lockName);
                    //有值就表示被锁了
                    if (null != val) {
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    logger.error("atomicIsLocked", e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("jedis", e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    /**
     * 解锁
     * 成功返回true,失败返回false
     */
    protected boolean atomicUnlock(Thread thread, AtomicLock lock) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(this.jedisDBIndex);
            String lockName = lock.getName();
            for (int i = 1; i <= redisRetryTimes; ++i) {
                try {
                    //先看线程是否有锁
                    boolean hasLock = this.atomicIsLocked(thread, lock);
                    //如果线程没有锁,就失败
                    if (!hasLock) {
                        return false;
                    }
                    //----------------------------------------------------------
                    //解锁
                    Long ret = jedis.del(lockName);
                    //如果删除成功
                    if (null != ret && 1 == ret) {
                        //删除线程的uuid
                        lock.deleteThreadUUID(thread);
                        return true;
                    }
                    return false;
                } catch (Exception e) {
                    logger.error("atomicUnlock", e);
                    continue;
                }
            }
        } catch (Exception e) {
            logger.error("jedis", e);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public int getRedisRetryTimes() {
        return redisRetryTimes;
    }

    public void setRedisRetryTimes(int redisRetryTimes) {
        this.redisRetryTimes = redisRetryTimes;
    }

    public int getJedisDBIndex() {
        return jedisDBIndex;
    }

    public void setJedisDBIndex(int jedisDBIndex) {
        this.jedisDBIndex = jedisDBIndex;
    }
}
