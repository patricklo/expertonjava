package expert.java.common.datastructure.fibonacci;

/**
 * Created by patrick on 1/29/15.
 */
public class FibonaccciTest {

    /*
      斐波那契数特点：
      斐波那契数列的解法，一般解法都是效率低下的，如下列方法，考官会要求性能提升：

      时间复杂度：随着n的大小以指数级方式增加
          >O(n*n)
     */

    private static long fibonacci ( int n ){
        if (n <= 0){
            return 0;
        }

        if(n == 1){
            return 1;
        }
        return fibonacci(n-1) + fibonacci(n-2);

    }

    /*
      改进算法：
      时间复杂度只为： O(n)
    */

    private static long fibonacci02(int n){
        int[] result = {0,1};
        if(n<2 ){
            return result[n];
        }

        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;

        for(int i=2; i<=n; i++){
            fibN = fibNMinusOne + fibNMinusTwo;

            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }
        return fibN;

    }

    /*
      还有时间复杂度更小的算法，这里不再详述
     */

    /*
      变种题目：
        一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级台阶总共有多少种跳法？
     */

    public static void main(String[] args){
//        FibonaccciTest.fibonacci(100);
        System.out.println(FibonaccciTest.fibonacci02(10));
    }



}
