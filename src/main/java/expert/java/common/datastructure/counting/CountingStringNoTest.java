package expert.java.common.datastructure.counting;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by patrick on 6/19/2015.
 */
public class CountingStringNoTest {
    public static void main(String [] args){
        CountingStringNoTest countingStringNoTest = new CountingStringNoTest();
        String[] unsorted = {
                "penumbra",
                "annulus",
                "eclipse",
                "eclipse",
                "annulus",
                "eclipse",
                "penumbra",
                "eclipse",
                "nova",
                "eclipse",
                "eclipse",
                "syzygy",
                "eclipse",
                "totality",
                "eclipse",
                "eclipse",
                "nova",
                "annulus",
                "penumbra",
                "eclipse",
                "penumbra",
                "eclipse"

        };


        countingStringNoTest.sorting(unsorted, 0, unsorted.length - 1);

        for(String str:unsorted){
            System.out.printf("%s;",str);
        }
        System.out.println();


        countingStringNoTest.countSorted(unsorted);



    }

    public void sorting(String[] unsorted, int left, int right){
        int pivot;
        if(left < right){
            pivot = partition(unsorted, left, right);
            sorting(unsorted, left, pivot - 1);
            sorting(unsorted, pivot + 1, right);
        }
    }

    public int partition(String[] unsorted, int left, int right){
        String pivotKey = unsorted[left];
        while(left<right){
            while(left<right && unsorted[right].compareTo(pivotKey)>=0) --right;
            unsorted[left] = unsorted[right];

            while(left<right && unsorted[left].compareTo(pivotKey) < 0 ) ++left;
            unsorted[right] = unsorted[left];

        }
        unsorted[left] = pivotKey;
        return left;
    }


    public static void countSorted(String[] sorted) {
        Map<String, Integer> charMap = new HashMap<String, Integer>();
        for (String ch : sorted) {
            if (charMap.containsKey(ch)) {
                charMap.put(ch, charMap.get(ch) + 1);
            } else {
                charMap.put(ch, 1);
            }
        }

        Set<Map.Entry<String, Integer>> entrySet = charMap.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
                System.out.printf("%s : %d \n", entry.getKey(), entry.getValue());
        }
    }
}
