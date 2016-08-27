package expert.java.common.multithreading.forkAndJoin;

import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

/**
 * Created by patrick on 6/18/2015.
 */
public class CalculatorTest extends RecursiveTask<Long> {
    private static final int THRESHOLD = 5;

    private int[] nums;

    public CalculatorTest(int[] nums){
        this.nums = nums;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        int len = nums.length;
        boolean canCompute = (len <= THRESHOLD);

        if(canCompute){
            for(int num : nums){
                sum += num;
            }
        }else{
            int mid = len/2;
            CalculatorTest leftTask = new CalculatorTest(Arrays.copyOfRange(nums,0,mid));
            CalculatorTest rightTask = new CalculatorTest(Arrays.copyOfRange(nums,mid,len));

            leftTask.fork();
            rightTask.fork();

            long leftResult = leftTask.join();
            long rightResult = rightTask.join();

            sum += leftResult + rightResult;
        }
        return sum;
    }


}
