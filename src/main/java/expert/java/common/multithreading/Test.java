package expert.java.common.multithreading;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 6/22/2015.
 */
public class Test {
    public static void main(String[] args){
        //int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11,1, 22, 33, 4, 52, 61, 7, 48, 10, 11, 1, 22, 33, 4, 52, 61, 7, 48, 10, 11 };
        int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11};

       // int arr[] = new int[]{1, 22, 33, 4, 52, 61, 7, 48, 10, 11,1, 22, 33, 4, 52, 61};
//        int sum = 0;
//        for(int i : arr){
//            sum += i;
//        }
//        System.out.println(sum);

        List arrList= new ArrayList();
        //List<Integer> arrSubList = arrList.subList(0,4);
        for(int i : arr){
            arrList.add(i);
        }

        List arrSubList = arrList.subList(0,4);
        for(Object i : arrSubList){
           System.out.println((int)i);
        }
    }
}
