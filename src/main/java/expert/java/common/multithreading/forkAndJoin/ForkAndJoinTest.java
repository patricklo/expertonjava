package expert.java.common.multithreading.forkAndJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by patrick on 6/18/2015.
 */
public class ForkAndJoinTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11,1, 22, 33, 4, 52, 61, 7, 48, 10, 11, 1, 22, 33, 4, 52, 61, 7, 48, 10, 11 };
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CalculatorTest calculatorTest = new CalculatorTest(arr);
        Future<Long> result = forkJoinPool.submit(calculatorTest);

        System.out.println((long)result.get());
    }
}
