package expert.java.codility;

/**
 * Created by patricklo on 11/11/16.
 *
 * http://blog.csdn.net/caopengcs/article/details/41841625
 * 给定n段绳子——一个正整数数组，和一个正整数K，
 * 每次只能连接相邻的两根绳子，连接好了绳子长度为之前的绳子长度和，
 * 并且位置不变，问这么连接下去，最多能形成多少根长度至少为K的绳子？
 * 数据范围： N[1..10^5], 数组元素和K的范围[1..10^9]。
 * 要求复杂度： 时间O(N), 空间O(1)。
 */
public class TieRopes {
    private static int solution(int k,int[] arr){
        int combinedNo = 0;
        for(int startIndex = 0;startIndex<arr.length;startIndex++){
            int sumLength=0;

            if((startIndex+1)<arr.length){
                sumLength = arr[startIndex] + arr[startIndex+1];
            }
            if(sumLength>=k){
                combinedNo++;
            }

        }
        return combinedNo;
    }

    public static void main(String[] args){
        int[] arr = {6,1,1,1,1};
        System.out.println(solution(5,arr));
    }
}
