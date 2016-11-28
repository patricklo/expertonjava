package test.lock;

import com.alibaba.gtool.xlock.common.bean.XLock;
import com.alibaba.gtool.xlock.common.manager.XLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gumao
 * @since 2016-11-20
 */
public class BizLockTestTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(BizLockTestTask.class);
    private XLockManager manager;
    private XLock lock;

    public void run() {
        try {
            lock = manager.createLock(LockConst.lockName);

            one();
//            two();

        } catch (Exception e) {
            logger.error("xx", e);
        }
    }

    public void three() {

        try {
            boolean lockResult = false;
            try {
                /**
                 * 尝试一次加锁。不阻塞,不等待。
                 * 加锁成功返回true,否则返回false。
                 */
                lockResult = lock.tryLock();

                if (lockResult) {
                    //do something
                    Thread.sleep(2000L);
                }

            } catch (Exception e) {
                logger.error("ex", e);
            } finally {
                if (lockResult) {
                    /**
                     * 解锁
                     */
                    lock.unLock();
                }
            }


            Thread.sleep(10000L);

            lock.lock();

        } catch (Exception e) {
            logger.error("xx", e);
        }
    }

    public void two() {
        try {
            try {
                /**
                 * 加锁。
                 * 加锁成功,返回true。
                 * 否则,一直等待,直到加锁成功。
                 */
                lock.lock();

                //do something
                Thread.sleep(2000L);

            } catch (Exception e) {
                logger.error("ex", e);
            } finally {
                /**
                 * 解锁
                 */
                lock.unLock();
            }


            Thread.sleep(10000L);

            lock.lock();

        } catch (Exception e) {
            logger.error("xx", e);
        }
    }


    public void one() {
        try {
            boolean lockResult = false;
            try {
                /**
                 * 尝试一次加锁。
                 * 加锁成功,返回true。
                 * 加锁失败,就等待最大时长。最终加锁成功,返回true;否则,返回false。
                 */
                lockResult = lock.tryLock(1000L);
                if (lockResult) {
                    //do something
                    Thread.sleep(3000);
                }
            } catch (Exception e) {
                logger.error("ex", e);
            } finally {
//                if (lockResult) {
                    /**
                     * 解锁
                     */
                    lock.unLock();
//                }
            }


            Thread.sleep(1000);

            boolean result2 = lock.tryLock(10000L);
            if (result2) {
                //do something
                Thread.sleep(3000);
                lock.unLock();
            }

            lock.lock();

        } catch (Exception e) {
            logger.error("xx", e);
        }
    }

    public XLockManager getManager() {
        return manager;
    }

    public void setManager(XLockManager manager) {
        this.manager = manager;
    }

    public XLock getLock() {
        return lock;
    }

    public void setLock(XLock lock) {
        this.lock = lock;
    }
}
