package practice.java.datastructure.binarysearch;

/**
 * Created by patrick on 1/29/15.
 */
public class BinarySearchTest {


   public static void main(String[] args)
    {
        int LEN  = 10000;
        int [] a = new int[LEN];
        for(int i = 0; i < LEN; i++)
            a[i] = i - 5000;
        int goal = 0;
        int index = binary_search(a, LEN, goal);

        if(index != -1)
            System.out.println("在数组中的下标为"+binary_search(a, LEN, goal));
        else
            System.out.println("不存在"+goal);

    }

    //二分查找
    private static int binary_search(int[] a, int len, int goal)
    {
        int low = 0;
        int high = len - 1;
        while(low <= high)
        {
            int middle = (low + high)/2;
            if(a[middle] == goal)
                return middle;
                //在左半边
            else if(a[middle] > goal)
                high = middle - 1;
                //在右半边
            else
                low = middle + 1;
        }
        //没找到
        return -1;
    }
}
