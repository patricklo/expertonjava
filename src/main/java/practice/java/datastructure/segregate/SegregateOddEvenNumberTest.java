package practice.java.datastructure.segregate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by patrick on 6/17/2015.
 */
public class SegregateOddEvenNumberTest {
    public static void main(String[] args){
        int[] unsorted = {23,22,35,44,55,66,99,10};
        segregateEvenOdd(unsorted, 0, unsorted.length - 1);
        for(int node : unsorted){
            System.out.println(node);
        }
    }

    public static void segregateEvenOdd(int[] unsorted, int left, int right){
        int pivot = 0;
        if(left<right){
            pivot = partition(unsorted,left,right);
            segregateEvenOdd(unsorted, left, pivot - 1);
            segregateEvenOdd(unsorted,pivot+1,right);
        }
    }

    public static int partition(int[] unsorted, int left, int right){
        int pivotKey = unsorted[left];

        while(left<right){
            while(left<right && unsorted[right]%2 != 0) --right;
            unsorted[left] = unsorted[right];

            //
            //while(left<right && unsorted[left] & 1 == 0)
            while(left<right&& unsorted[left]%2 == 0) ++left;
            unsorted[right] = unsorted[left];
        }
       unsorted[left] = pivotKey;
        return  left;

    }

}

