package test.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.alibaba.gtool.util.thread.BaseThreadFactory;
import com.alibaba.gtool.xlock.common.bean.XLock;
import com.alibaba.gtool.xlock.common.enums.XLockEventEnum;
import com.alibaba.gtool.xlock.tair.manager.TairLockManagerByIncr;
import com.taobao.tair.impl.mc.MultiClusterTairManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gumao
 * @since 2016-11-20
 */
public class TairLockTestM2 {
    public static MultiClusterTairManager tairManager;
    public static int tairNamespace = 2260;
    private static Logger logger = LoggerFactory.getLogger(BizLockTestTask.class);

    public static void main(String args[]) throws Exception {
        logger.error(">>>>  begin ");

        tairManager = new MultiClusterTairManager();
        tairManager.setConfigID("ldbcount-daily");
        tairManager.setDynamicConfig(true);
        tairManager.setTimeout(1000);
        tairManager.init();
//        ResultCode ret = tairManager.put(tairNamespace, "name", "jack__" + XlockUtil.nowDatetime());
//        ResultCode ret2 = tairManager.put(tairNamespace, "name2", "valxx", 100, 200);
//        System.out.println("   ResultCode = " + ret2);

        start();

        System.out.println("----- main done -------");
    }

    public static void start() throws Exception {

        //tair 锁管理器(通过 incr 实现)
        TairLockManagerByIncr lockManager = new TairLockManagerByIncr();
        lockManager.setTairManager(tairManager);//参数
        lockManager.setTairNamespace(tairNamespace);//参数
        lockManager.setEnableLogWarn(true);//是否使用logWarn输出。默认false

        //由锁管理器创建锁
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
