package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 */
public class Test {

    public static int solution(int[] A) {
        // write your code in Java SE 8
        int maxDistance = -1;

        int _count = A.length;

        for (int indx = 0; indx < _count; indx++) {
            for (int indx2 = indx + 1; indx2 < _count; indx2++) {
                boolean albeToCount = true;
                if (A[indx] != A[indx2]) {
                    for(int indx3 = 0;indx3<_count;indx3++){
                        if(indx3==indx || indx3==indx2){
                            continue;
                        }
                        if((A[indx]<A[indx3] && A[indx3] < A[indx2]) || (A[indx2]<A[indx3] && A[indx3]<A[indx])){
                            albeToCount = false;
                            break;
                        }
                    }
                    if(albeToCount) {
                        if (maxDistance == -1) {
                            maxDistance = indx2 - indx;
                        } else {
                            if ((indx2 - indx) > maxDistance) {
                                maxDistance = indx2 - indx;
                            }
                        }
                    }
                }else{
                    if(maxDistance==-1){
                        maxDistance = indx2-indx;
                    }else{
                        if((indx2-indx)>maxDistance){
                            maxDistance = indx2-indx;
                        }
                    }
                }
            }
        }
        return maxDistance;
    }

//    public static int solution2(int[] A){
////        int N = A.length;
////        int result = 0;
////        int j = 0;
////        for(int i=0;i<N;i++){
////            while(j<N){
////                if((A[i]-A[j])<=k){
////                    j+=1;
////                }else{
////                    break;
////                }
////            }
////            result += (j-i);
////            if(result>= Integer.MAX_VALUE){
////                return Integer.MAX_VALUE;
////            }
////        }
////
////        return result;
//    }



    public static void main(String[] args){
        int[] arr = {1,4,5,6,7,8};
        System.out.println(solution(arr));

        int[] arrs = {0,3,4,7,5,3,11,1};

        System.out.println(solution(arrs));

        int[] arr2 = {1,4,7,3,3,5};

        System.out.println(solution(arr2));

        int[] A =new int[10];
        int indx = 0;
        int indx3 = 1;
        int indx2 = 5;
        A[indx] = 1;
        A[indx2] = 5;
        A[indx3] = 4;
        if((A[indx]<A[indx3] && A[indx3] < A[indx2]) || (A[indx2]<A[indx3] && A[indx3]<A[indx])){
            System.out.println("true");
        }
    }


}
