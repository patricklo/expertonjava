package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 *
 * 给定一个正负整数数组A[]
 * 求: 当A[0] = A[2] + ...+A[N] 则返回 index 1 ,称为左右平衡
 *
 *
 */
public class EquiliIndex {
    public static int solution(int[] A) {
        // write your code in Java SE 8
        int equilibriumIndex = -1;
        if(A==null || A.length==0)
            return equilibriumIndex;
        for(int i=0;i<A.length;i++){
            int leftSum = 0;
            for(int leftIndex=0;leftIndex<=i;leftIndex++){
                leftSum+=A[leftIndex];
            }

            int rightSum = 0;
            for(int rightIndex=i+2;rightIndex<A.length;rightIndex++){
                rightSum += A[rightIndex];
            }
            if(rightSum==leftSum){
                equilibriumIndex = i+1;
                break;
            }
        }
        return equilibriumIndex;
    }

    public static void main(String[] args){
        long[] a = {4294967296l, 3, -4, 5, 1, -6, 2, 1};
        //solution(a);
        int i = equi(a);
        System.out.print(i);
    }

    private static int equi ( long[] a ) {
        // write your code here
        int size = a.length;
        if (size == 0) return -1;

        long[] c_left = new long[size];

        c_left[0] = a[0];
        for(int i = 1; i < size; i++) {
            c_left[i] = c_left[i-1] + a[i];
        }

        long total_right = c_left[size-1]-a[0];

        if (total_right == 0) return 0;

        for (int k = 1; k < size; k++) {
            total_right = total_right - a[k];

            if (c_left[k-1] == total_right) return k;
        }

        return -1;
    }
}
