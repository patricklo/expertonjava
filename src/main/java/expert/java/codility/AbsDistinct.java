package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 * 给定一个按非递减顺序排好顺序的非空整数数组，问里面右多少种不同的绝对值。
 * 数据范围：整数数组长度[1..10^5], 整数范围[-2147483648, +2147483647]。
 * 要求复杂度 ： 时间O(N),空间O(1)
 * 分析： 题目不难…… 但是细节很重要。因为整数直接取绝对值可能回溢出（例如-2147483648),而且我们没有额外空间hash。
 * 所以一个好办法是类似合并两个有序序列。我们从最小的负数和最大的正数开始类似归并排序那么做。
 * 这样，正负数都是按照绝对值逐渐减小的顺序遍历的。我们把正数和负数的绝对值想像成两个递减的序列，然后按归并排序思路，
 * 每次大的动一下就可以了，直到一个列表为空的时候，我们需要把另外一个列表计算进去。
 * 要点就是可以用x + y的符号来代替绝对值比较，因为一个正数 ＋ 负数 不会溢出。。。。
 */
public class AbsDistinct {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
       System.out.println(solution(1000000000));

        System.out.println(solution(1));
        //System.out.println(solution(955));
    }

    private static int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p < 1 + l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i <= l- p; ++i) {
                int di = d[i];
                int dip = d[i+p];
                if (d[i] != d[i + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }
}
