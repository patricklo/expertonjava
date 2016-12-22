package expert.java.codility;

/**
 * Created by patricklo on 11/11/16.
 * 给定n段绳子——一个正整数数组，和一个正整数K，每次只能连接相邻的两根绳子，连接好了绳子长度为之前的绳子长度和，
 * 并且位置不变，问这么连接下去，最多能形成多少根长度至少为K的绳子？
 数据范围： N[1..10^5], 数组元素和K的范围[1..10^9]。

 要求复杂度： 时间O(N), 空间O(1)。

 分析： 假设最终扔掉一根绳子，那么为什么不把这根绳子连接到它相邻的绳子上呢？ 所以不会扔绳子的…… 于是就线性扫一下 总和 >= K就是一条。。。

 解析: 有一个正整数数组,求数组内相加数==K的所有组合,求出组合数
 */
public class TieRopes {

    private static int solution(int K, int[] arr) {
        int r = 0;
        for (int i = 0; i < arr.length;) {
            int length = 0;
            for (; (i < arr.length) && (length < K); length += arr[i++]);

            if (length >= K) {
                ++r;
            }
        }
        return r;
    }

    public static void main(String[] args){
        int[] arr = {5,1,1};
        int count = solution(5,arr);//expect 1
        System.out.println(count);
    }
}
