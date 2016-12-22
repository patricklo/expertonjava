package expert.java.common.datastructure.sorting;

/**
 * Created by Patrick on 19/11/16.
 */
public class QuicSorting2 {

    public static void main(String[] args){
        int[] arr = {};
        quickSort(arr,0,arr.length);
    }

    private static void quickSort(int[]arr, int left ,int right){
        int pivotIndex = left;
        while(left<right){
            pivotIndex = pivot(arr,left,right);
            pivot(arr,left,pivotIndex-1);
            pivot(arr,pivotIndex+1,right);
        }
    }

    private static int pivot(int[] arr,int left,int right){
        int pivotValue = arr[left];
        while(left<right){
            while(left<right && arr[right]>pivotValue) --right;
            arr[left] = arr[right];

            while(left<right && pivotValue>arr[left]) ++left;
            arr[right] = arr[left];
        }
        arr[left] =pivotValue;
        return left;
    }
}
