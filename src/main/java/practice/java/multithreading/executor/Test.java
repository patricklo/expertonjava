package practice.java.multithreading.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by patrick on 7/14/2015.
 */
public class Test {

    private ExecutorService executorService;
    private static int CPU_NUMS = Runtime.getRuntime().availableProcessors();
    private List<FutureTask> futureTaskList = new ArrayList<FutureTask>();
    public Test(){
        executorService = Executors.newFixedThreadPool(CPU_NUMS);
    }

    public long sum(int[] data){
        int start,end,increment;
        // ����CPU���ĸ���������񣬴���FutureTask���ύ��Executor
        for(int i=0;i<CPU_NUMS;i++) {
            increment = data.length / CPU_NUMS + 1;
            start = i * increment;
            end = start + increment;
            if (end > data.length) {
                end = data.length;
            }

            SumCalculator sumCalculator = new SumCalculator(data,start,end);
            FutureTask futureTask = new FutureTask(sumCalculator);
            futureTaskList.add(futureTask);
            if(!executorService.isShutdown()){
                executorService.submit(sumCalculator);
            }
        }
        return getFinaResult();
    }

    public long getFinaResult(){
        long sum = 0;

        for(FutureTask task:futureTaskList){
           try{
               sum += (long)task.get();
           } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        }
        return sum;
    }

    public static void main(String[] args){
        int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11,1, 22, 33, 4, 52, 61, 7, 48, 10, 11, 1, 22, 33, 4, 52, 61, 7, 48, 10, 11 };
        long sum = new Test().sum(arr);
        System.out.println("sum: " + sum);
    }



    class SumCalculator implements Callable<Long> {
        private int[] data;
        private int start;
        private int end;

        public SumCalculator(int[] data,int start,int end){
            this.data = data;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            long sum =0;
            for(int i=start;i<end;i++){
                sum += data[i];
            }
            return sum;
        }
    }

}





