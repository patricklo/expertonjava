package expert.java.common.designpatterns.producers;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Patrick on 14/1/17.
 */
public class Producer implements Runnable {
    private BlockingQueue<Msg> msgBlockingQueue;

    public Producer(BlockingQueue<Msg> queue){
        this.msgBlockingQueue = queue;
    }

    @Override
    public void run() {
        for(int i =0; i<100;i++){
            try {
                Msg msg = new Msg(" "+i);
                msgBlockingQueue.put(msg);
                System.out.println("Produced " + msg.getMsg());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //adding exit message
        Msg msg = new Msg("exit");
        try {
            msgBlockingQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
