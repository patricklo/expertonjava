package expert.java.common.designpatterns.producers;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Patrick on 14/1/17.
 */
public class Consumer implements Runnable {
    private BlockingQueue<Msg> queue;

    public Consumer(BlockingQueue<Msg> q){
        this.queue=q;
    }

    @Override
    public void run() {
        try{
            Msg msg;
            //consuming messages until exit message is received
            while((msg = queue.take()).getMsg() !="exit"){
                Thread.sleep(10);
                System.out.println("Consumed "+msg.getMsg());
            }
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
