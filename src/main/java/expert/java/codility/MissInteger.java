package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 *  A[0] = 1
 A[1] = 3
 A[2] = 6
 A[3] = 4
 A[4] = 1
 A[5] = 2

 */
public class MissInteger {

    private static int solution(int A[], int N){
        int i = 0;
        int[] B = new int[N];
        int temp = 0;
        for(i=0;i<N;i++){
            temp = A[i];
            if(temp>-1&&temp<=N){
                B[temp-1] = 1;
            }
        }
        for(i=0;i<N;i++){
            if(B[i]!=1){
                return i+1;
            }
        }
        return N+1;
    }

    public static void main(String[] args){
        int[] arr = {1,2,3,4,5};
        int index = solution(arr,arr.length);
        System.out.println(index);
    }
}
