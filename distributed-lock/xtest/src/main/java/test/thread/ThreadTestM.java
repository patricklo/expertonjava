package test.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import com.alibaba.gtool.util.thread.BaseThreadFactory;

/**
 * @author gumao
 * @since 2016-11-25
 */
public class ThreadTestM {
    public static void  main(String [] aa) throws  Exception{

        //多线程
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>(10000);
        ThreadPoolExecutor  executor = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.SECONDS, taskQueue,
                new BaseThreadFactory("my_thread-"), new ThreadPoolExecutor.DiscardPolicy());

        for(int i=1;i<=3;++i){
            ThreadTestTask task=new ThreadTestTask();
            executor.execute(task);
        }

        Thread.sleep(1000);

        ThreadTestTask task=new ThreadTestTask();
        executor.execute(task);

        Thread.sleep(1000);

        ThreadTestTask task2=new ThreadTestTask();
        executor.execute(task2);

    }
}
