package expert.java.codility;


/**
 * Created by Patrick on 12/11/16.
 */
public class Test2 {

    public static int solution(int A, int B) {
        // write your code in Java SE 8
        if(A<0 || B<0 || A>100000000 || B>100000000){
            return -1;
        }
        if((Integer.MAX_VALUE/B)<=A || (Integer.MAX_VALUE/A)<=B){
           return -1;
        }
        int n = 100*100000000;
        int totalCount = 0;
        while (n > 0) {
            if(n%2==1){
                totalCount+=1;
            }
            n /= 2;
        }

        return totalCount;
    }

    public static int solution2(int A,int B){
        int totalCount = 0;
        int n = A*B;
        while (n>0) {
            if((n&1)==1){
                totalCount += 1;
            }
            n>>=1;
        }
        return totalCount;
    }

    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println(solution(0x7fffffff, 10));
    }
}
