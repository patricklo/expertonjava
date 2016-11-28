package test.thread;

import java.util.concurrent.atomic.AtomicInteger;
import com.alibaba.gtool.util.utils.GTimeUtil;

/**
 * @author gumao
 * @since 2016-11-25
 */
public class ThreadTestTask implements Runnable {
    public static AtomicInteger COUNT = new AtomicInteger(0);
    public int count = COUNT.incrementAndGet();
    public AtomicInteger runCount = new AtomicInteger(0);
    public String threadName;
    public int exceptionCount = 0;

    public void run() {
        try {
                Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

        runCount.incrementAndGet();
        Thread thread = Thread.currentThread();
        threadName = thread.getName();
        String log = "  thread=" + thread.getName() + "  task=" + count + "  runCount=" + runCount.get();
        if (count == 1) {
            if (exceptionCount == 0) {
                exceptionCount++;
                throw new IllegalArgumentException(" count is invalid . " + log);
            }
        }


        try {
            while (true) {

                String log2 = "\n" + GTimeUtil.nowDatetime() + " heartbeat >> " + log + "";
                System.out.println(log2);
                Thread.sleep(3000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
