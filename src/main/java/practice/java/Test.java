package practice.java;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;

/**
 * Created by Patrick on 1/25/2015.
 */
public class Test {


    HashSet test ;
    Hashtable hashtable;
    Float aFloat;
    Lock lock;


    public int solution(int[] array){
        int count =0;

        for(int i=0; i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[i] == array[j]){
                    count +=1;
                }
            }

        }
        if(count >= 1000000000){
            return 100000000;
        }
        return count;
    }



    public int solution2(int[] array){
        int count =0;
        for(int i=0; i<array.length;i++){
            int match = Test.binarySearch(Test.copyOfRange(array,i+1,array.length),array[i]);
            if(match >=0){
                count +=1;
            }
//            for(int j=i+1;j<array.length;j++){
//                if(array[i] == array[j]){
//                    count +=1;
//                }
//            }

        }
        if(count >= 1000000000){
            return 100000000;
        }
        return count;
    }

   public static int[] copyOfRange(int[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        int[] copy = new int[newLength];
        System.arraycopy(original, from, copy, 0,
                Math.min(original.length - from, newLength));
        return copy;
    }

    public static int binarySearch(int[] a, int key) {
        return binarySearch0(a, 0, a.length, key);
    }

    private static int binarySearch0(int[] a, int fromIndex, int toIndex,
                                     int key) {
        int low = fromIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal < key)
                low = mid + 1;
            else if (midVal > key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static String reverseString(String str){
        if(str == null){
            System.out.println("the string is empty");
            return null;
        }

        if(str.length() == 1){
            return str;
        }

        return reverseString(str.substring(1)) + str.charAt(0);
    }
    public static void main(String [] args){
        HashMap hashMap;
        String test= "abcde";
        System.out.println(reverseString(test));
        ArrayList arrayList;
        LinkedList linkedList;
        ConcurrentHashMap concurrentHashMap;
        System.out.println(test.substring(1, 3));

        int DEFAULT_CONCURRENCY_LEVEL = 16;
        int ssize = 1;
        while (ssize < DEFAULT_CONCURRENCY_LEVEL) {
            //++sshift;
            ssize <<= 1;
        }
        System.out.println(ssize);
       // int segmentShift = 32 - sshift;
        int segmentMask = ssize - 1;
        System.out.println(segmentMask);

    }


}
