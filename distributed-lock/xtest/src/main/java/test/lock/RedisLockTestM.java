package test.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.alibaba.gtool.util.thread.BaseThreadFactory;
import com.alibaba.gtool.xlock.common.bean.XLock;
import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;
import com.alibaba.gtool.xlock.redis.manager.RedisLockManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author gumao
 * @since 2016-11-20
 */
public class RedisLockTestM {
    private static Logger logger = LoggerFactory.getLogger(RedisLockTestM.class);
    private static JedisPool jedisPool;

    public static void main(String[] ss) throws Exception {
        // 配置如下的4个参数就够了。
        JedisPoolConfig config = new JedisPoolConfig();
        // 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        // 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(1000);
        // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(100);
        // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(10000L);
        // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(true);

        jedisPool = new JedisPool(config, "127.0.0.1", 6379);

        start();
    }

    public static void start() throws Exception {
        logger.info(">>>> RedisLockTestM ");

        //redis 锁管理器
        RedisLockManager lockManager = new RedisLockManager();
        lockManager.setJedisPool(jedisPool);//JedisPool是线程安全的。
        lockManager.setJedisDBIndex(0);//redis的库。默认0
        lockManager.setEnableLogWarn(true);//是否使用logWarn输出。默认false

        XLock lock = lockManager.createLock(LockConst.lockName);

        BlockingQueue queue = new LinkedBlockingQueue<Runnable>(200);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.SECONDS, queue,
                new BaseThreadFactory("TXX_"), new ThreadPoolExecutor.DiscardPolicy());

        for (XLockEventEnum one : XLockEventEnum.values()) {
            LockListener listener = new LockListener(one);
            lock.addEventListener(listener);
        }

        for (int i = 1; i <= 3; ++i) {
            BizLockTestTask task = new BizLockTestTask();
            task.setManager(lockManager);
            executor.execute(task);
        }


        System.out.println("\n\n  main done =============");

    }


}
