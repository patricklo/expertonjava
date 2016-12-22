package expert.java.common.datastructure.sorting;

import java.util.Arrays;

/**
 * Created by patrick on 6/14/2015.
 */
public class QuickSort {

    public static void main(String [] args){
        int [] unsorted = {49,38,65,97,76,13,27};
        //int[] unsorted = {23,43,11,25,50,65,67,55};
        quickSort(unsorted,0,unsorted.length-1);
        for(int i=0;i<unsorted.length;i++){
            System.out.print(unsorted[i]+"; ");
        }
    }

    public static void quickSort(int[] unsorted, int left, int right){
        int pivotIndex;
        if(left < right){
            pivotIndex = partition(unsorted,left,right);
            quickSort(unsorted,left,pivotIndex-1);
            quickSort(unsorted,pivotIndex+1, right);
        }
    }

    public static int partition(int[] unsorted, int left, int right){
        int pivotValue  = unsorted[left];
        while(left < right) {
            while (left < right && unsorted[right] >= pivotValue) --right;
            unsorted[left] = unsorted[right];

            while (left < right && unsorted[left] <= pivotValue) ++left;
            unsorted[right] = unsorted[left];
        }
        System.out.printf("unsort array after pass %s: %n",  Arrays.toString(unsorted));
        unsorted[left] = pivotValue;
        return left;
    }
}
