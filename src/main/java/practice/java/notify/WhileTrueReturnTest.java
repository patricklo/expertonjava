package practice.java.notify;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by patrick on 2015/10/30.
 * Copyright @ EEGSmart
 */
public class WhileTrueReturnTest {
    private volatile CopyOnWriteArrayList<String> smsQueue = new CopyOnWriteArrayList<String>();
    private static volatile WhileTrueReturnTest _instance;
    private int queueMaxSize = 100;
    public static WhileTrueReturnTest getInstance(){
        if(_instance == null){
            synchronized (WhileTrueReturnTest.class){
                if(_instance ==null)
                    _instance =  new WhileTrueReturnTest();
            }
        }
        return _instance;
    }


    public synchronized void retrieve() throws InterruptedException {
        while(true){
            if(smsQueue.isEmpty()){
                System.out.println("I'm waiting...");
                wait();
            }
            String smsContent = smsQueue.remove(0);
           //return smsContent;
        }
    }

    public synchronized void adds(String smsContent) throws InterruptedException {
        while(smsQueue.size() == queueMaxSize){
            wait();
        }
        smsQueue.add(smsContent);
        notifyAll();
    }
}
