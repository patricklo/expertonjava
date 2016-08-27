package practice.java.datastructure.exponent;

/**
 * Created by patrick on 4/29/15.
 */
public class PowerTest {

    public static void main(String[] args){
        double result = powerCalculte(4,8);
        System.out.println(result);

        System.out.println(5>>1);
    }
    //不使用power函数或其它辅助函数的情况下实现
    public static double powerCalculte(double base, int exponent){
        if(exponent == 0){
            return 1;
        }

        if(exponent == 1){
            return base;
        }

        double result = powerCalculte(base, exponent >> 1);//exponent >>1  ==  exponent/2
        result *= result;


        //判断当exponent 为奇数，因为上面计算只计算到偶数，最后一位奇数需在后面乘上
        //一种可行的方法：利用2进制进行判断，奇数的最低位一定是1，而偶数的最低位一定是0.所以我们可以根据这个特性，
        // 让需要判定的整数和1进行“与”运算，这样就只留下了原数的最低位，然后直接判断这个数等于1还是等于0即可
        //if((exponent & 1) == 1)
        if((exponent & 0x1) == 1){
            result *= base;
        }

        return result;
    }

}
