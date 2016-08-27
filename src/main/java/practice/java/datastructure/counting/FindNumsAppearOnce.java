package practice.java.datastructure.counting;

/**
 * Created by patrick on 6/26/2015.
 */
public class FindNumsAppearOnce {
    public static void main(String[] args){
        int data[] = {2, 4, 3, 6, 3, 2, 5, 5};
       // Test("Test1", data, sizeof(data) / sizeof(int), 4, 6);
        FindNumsAppearOnce findNumsAppearOnce = new FindNumsAppearOnce();
        findNumsAppearOnce.findNumsAppearOnce(data,4,6);
    }

    public void findNumsAppearOnce(int[] data,int num1,int num2){
        if(data == null || data.length < 2){
            return;
        }

        int resultOfExclusiveOR = 0;
        for(int i : data){
            resultOfExclusiveOR ^= i;
        }

        int indexof1 = findFirstBitIs1(resultOfExclusiveOR);

        num1=0;
        num2=0;

        for(int j = 0; j < data.length; ++j){
            if(isBit1(data[j],indexof1))
                num1 ^= data[j];
            else
                num2 ^= data[j];
        }
    }

    public boolean isBit1(int num1,int indexBit1){
        num1 = num1>>indexBit1;

        return (num1 & 1)==1;
    }

    public int findFirstBitIs1(int num){
        int indexBit1=0;

        while(((num&1) == 0) && (indexBit1 < 8 * new Integer(num).toString().length())){
            num = num>>1;
            ++indexBit1;
        }
        return indexBit1;
    }


}
