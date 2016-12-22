package expert.java.codility;

/**
 * Created by Patrick on 12/11/16.
 */
public class CountBoundedSlices {



    private static int solution(int k,int[] a){
        int _ret_numer = 0;
        int _count = a.length;
        for(int _inx=0;_inx<_count;_inx++){
            int _max_int = 0;
            int _min_int = 0;
            for(int _inx2 = _inx;_inx2<_count;_inx2++){
                if(_max_int==0){
                    _max_int = a[_inx2];
                }else if(_max_int<a[_inx2]){
                    _max_int = a[_inx2];
                }

                if(_min_int==0){
                    _min_int = a[_inx2];

                }else if(_min_int > a[_inx2]){
                    _min_int = a[_inx2];
                }

                if(_max_int-_min_int<=k){
                    _ret_numer++;
                }
            }
        }
        return  _ret_numer;
    }

    private static int solution2(int k,int[] a){
        int N = a.length;
        int result = 0;
        int j = 0;
        for(int i=0;i<N;i++){
            while(j<N){
                if((a[i]-a[j])<=k){
                    j+=1;
                }else{
                    break;
                }
            }
            result += (j-i);
            if(result>= Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }
        }

        return result;


    }

    public static void main(String[] args){
        int[] arr = {3,5,7,6,3};
      int count =  solution2(2,arr);
        System.out.print(count);
    }
}
