package expert.java.common.datastructure.sorting;

import java.util.Arrays;

/**
 * Created by patrick on 6/14/2015.
 */
public class BubbleSort {
    public static void main(String args[]){
        int [] unsorted = {32,39,21,45,23,3};
        bubbleSort(unsorted);

        int[] test = {5,3,2,1};
        bubbleSort(test);
    }

    public static void bubbleSort(int[] unsorted){
        for(int i=0; i <= unsorted.length-1;i++){

            for(int j= 1; j<unsorted.length-i; j++){
                if(unsorted[j-1] > unsorted[j]){
                    int temp =  unsorted[j];
                    unsorted[j] = unsorted[j-1];
                    unsorted[j-1] = temp;
                }
            }
            System.out.printf("unsort array after %d pass %s: %n", i+1, Arrays.toString(unsorted));
        }

    }
}
