package expert.java.common.datastructure.sorting;

/**
 * Created by Patrick on 6/3/16.
 */
public class BinarySearch {

    public static void main(String[] args){
       int[]tree = init();
        int goal = 2;

       int index= binarySearch(tree,tree.length,goal);
        System.out.println(index);
    }

    private static int binarySearch(int[] tree,int len, int goal){
        int low = 0;
        int high = len - 1;

        while(low<high){
            int middle = high/2;

            if(tree[middle]==goal){
                return  middle;
            }else if(tree[middle]>goal){
               high = middle-1;
            }else if(tree[middle]<goal){
                low = middle+1;
            }
        }
        return -1;

    }


    private static int[] init(){
        int LEN  = 10000;
        int [] a = new int[LEN];
        for(int i = 0; i < LEN; i++)
            a[i] = i - 5000;
        return a;
    }

}
