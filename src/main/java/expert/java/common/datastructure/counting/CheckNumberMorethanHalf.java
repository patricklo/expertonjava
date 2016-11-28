package expert.java.common.datastructure.counting;

import java.util.Random;

/**
 * Created by patrick on 6/22/2015.
 */
public class CheckNumberMorethanHalf {
    public static void main(String[] args){
        int numbers[] = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        CheckNumberMorethanHalf checkNumberMorethanHalf = new CheckNumberMorethanHalf();
       System.out.println(checkNumberMorethanHalf.moreThanHalfNumb(numbers, numbers.length)) ;
    }

    public int moreThanHalfNumb(int[] numbers,int length){
        if(checkInvalidArray(numbers,length)){
           return 0;
        }
        int middle = length>>1;  // length/2
        int start = 0;
        int end = length - 1;
        int index = partition(numbers,numbers.length,start,end);
        while(index != middle){
            if(index > middle){
                end = index -1;
                index = partition(numbers,length,start,end);
            }else{
                start = index +1;
                index =partition(numbers,numbers.length,start,end);
            }
        }
        int result = numbers[middle];

        for(int i : numbers){
            System.out.printf("%d ",i);

        }
        System.out.println("result="+result);

        if(!checkMoreThankHalf(numbers,numbers.length,result))
            result = 0;
        return result;
    }

    public boolean checkMoreThankHalf(int[] numbers,int length,int number){
        int times = 0;
        for(int i=0;i < numbers.length;++i){
            if(numbers[i] == number)
                times++;
        }
        boolean isMoreThanHalf = true;
        if(times*2 <= numbers.length){
            isMoreThanHalf = false;
        }
        return isMoreThanHalf;
    }


    public boolean checkInvalidArray(int[] numbers, int length){
        if(numbers == null || length <= 0){
            return true;
        }
        return false;
    }

    public int partition(int[] data,int length, int start, int end){
        if(data == null || data.length <= 0 || start < 0 || end >= data.length){
            System.out.println("Invalid properties");
            throw new IllegalArgumentException();
        }
        Random r = new Random();
        int index = r.ints(1, start, end).findFirst().getAsInt();
        swap(data,index,end);

        int small = start - 1;
        for(index = start; index<end;++index){
            if(data[index] < data[end]){
                ++ small;

                if(small != index)
                    swap(data,index,small);

            }
        }
        ++ small;
        swap(data,small,end);
        return small;

    }

    public void swap(int[] data, int num1, int num2){
        int tmp = data[num1];
        data[num1] = data[num2];
        data[num2] = tmp;
    }


}
