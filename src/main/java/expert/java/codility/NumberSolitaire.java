package expert.java.codility;

/**
 * Created by patricklo on 11/11/16.
 * 一个游戏是从一排N个格子开始，格子编号0..N - 1，起初，棋子在A[0]，
 * 每个格子里有一个整数（可能正，可能负）。你在格子I，你扔骰子，得到点数X = [1..6]，
 * 然后走到编号为I + X的格子，如果这个格子不存在就再投一次骰子，
 * 直到I + X号格子存在。你走到N - 1号格子时，游戏结束。
 * 你所经过格子里的整数的和是你的得分，求最大可能得分？
 * 数据范围： N [2..10^5]， 格子里的数的范围 [-10000, +10000]。
 * 要求复杂度： 时间O(N),空间O(N)
 * 分析： 一个显然的dp  dp[i] = A[i] + max(dp[i - x])  1<=x<=min(6,i)
 */
public class NumberSolitaire {

    public static void main(String[] args){
        int[] arr = {4,5,6,-1,5,8,10};
        int maxInt = solution(arr);
        System.out.println(maxInt);
    }


    public static int solution(int[] arr) {
        int inf = 2000000000;
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];

        for (int i = 1; i < n; ++i) {
            dp[i] = -inf;
            for (int j = min(6, i); j>0; --j) {
                dp[i] = max(dp[i], dp[i - j]);
            }
            dp[i] += arr[i];
        }
        return dp[n - 1];
    }


    public static int min(int a,int b){
        return a>b?b:a;
    }

    public static int max(int a,int b){
        return a>b?a:b;
    }



}
