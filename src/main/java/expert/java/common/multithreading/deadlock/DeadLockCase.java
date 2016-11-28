package expert.java.common.multithreading.deadlock;



/**
 * DeadLock Case:
 *
 * Created by Patrick on 27/8/16.
 */
public class DeadLockCase {
    private static Object lock =  new Object();
    private static TestSuspendedThread testSuspendedThread1 = new TestSuspendedThread("t1");
    private static TestSuspendedThread testSuspendedThread2 = new TestSuspendedThread("t2");

    public static class TestSuspendedThread extends Thread{
        public TestSuspendedThread(String name){
            this.setName(name);
        }


        @Override
        public void run(){
            synchronized (lock){
                System.out.println("in "+getName());
                /* deprecated*/
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args){
        testSuspendedThread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testSuspendedThread2.start();
        testSuspendedThread1.resume();
        testSuspendedThread2.resume();
        try {
            testSuspendedThread1.join();
            testSuspendedThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
