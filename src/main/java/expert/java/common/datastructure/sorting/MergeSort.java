package expert.java.common.datastructure.sorting;

/**
 * Created by patrick on 6/14/2015.
 */
public class MergeSort {

    public static void main(String[] args){
        int[] arr = {7,3,6,2,66,77,55,44,66,79,56,37};
        int[] temp = new int[arr.length];
        mergeSort(arr,temp,0,arr.length-1);

        for(int i: arr){
            System.out.println(i);
        }

    }

    public static void mergeSort(int[] unsorted, int[] temp,int begin,int end){
        if(begin == end) return;
        int mid = (begin+end)/2;
        mergeSort(unsorted,temp,begin,mid);
        mergeSort(unsorted,temp,mid+1,end);
        for(int i=begin;i<=end;i++){
            temp[i] = unsorted[i];
        }

        int index1 = begin;
        int index2 = mid + 1;
        int index = begin;

        while (index1 <= mid && index2 <= end) {
            if (temp[index1] < temp[index2]) {
                unsorted[index++] = temp[index1++];
            } else {
                unsorted[index++] = temp[index2++];
            }
        }
        while(index1 <= mid) {
            unsorted[index++] = temp[index1++];
        }
        while(index2 <= end) {
            unsorted[index++] = temp[index2++];
        }
    }

}
