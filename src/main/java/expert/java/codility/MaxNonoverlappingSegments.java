package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 *
 * 给定N条线段，每条线段是[A[i],B[i]]的形式（闭区间），线段已经按照结束端点排序了，
 * 求最多能选出多少条没有公共点的线段。
 数据范围 N [0..30000], A, B数组都是整数，范围[0..10^9]。
 要求复杂度： 时间空间都是O(1)。

 分析： 这个就是活动安排问题……而且区间都按右端点排序了，贪心一个一个取，相交就扔掉就可以了。


 理解:两个整数数组,
 */
public class MaxNonoverlappingSegments {
    private static int solution(int[] a, int[] b) {
        int last = -1, answer = 0;
        for (int i = 0; i < a.length; i++) {
            if ((last < 0) || (a[i] > b[last])) {
                last = i;
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args){
        int[] a ={5,3,4,1,5};
        int[] b = {3,4,5,6,7};
        solution(a,b);
    }
}