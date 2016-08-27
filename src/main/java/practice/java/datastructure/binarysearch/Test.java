package practice.java.datastructure.binarysearch;

/**
 * Created by patrick on 6/26/2015.
 */
public class Test {
    public static void main(String[] args){
        int LEN  = 10000;
        int [] a = new int[LEN];
        for(int i = 0; i < LEN; i++)
            a[i] = i - 5000;
        int goal = 0;
        int index = binary_search(a, goal);

        if(index != -1)
            System.out.println("�������е��±�Ϊ"+binary_search(a, goal));
        else
            System.out.println("������"+goal);
    }

    public static int binary_search(int[] data,int target){

        return -1;//not found
    }
}
