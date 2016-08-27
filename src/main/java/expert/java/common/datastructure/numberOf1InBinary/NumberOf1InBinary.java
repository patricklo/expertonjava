package expert.java.common.datastructure.numberOf1InBinary;

/**
 * Created by patrick on 6/26/2015.
 */
public class NumberOf1InBinary {
    public static void main(String[] args){
        System.out.println(NumberOf1InBinary.count(10));
        System.out.println(NumberOf1InBinary.count1(10));
        System.out.println(NumberOf1InBinary.count2(10));
    }
    private static int count(int v){
        int num = 0;

        while(v!=0){
            int temp = v%2;
            if(temp==1){
                num++;
            }
            v = v/2;
        }
        return num;
    }

    //ʱ�临�Ӷ�Ϊ��O(log v)
    private static int count1(int v){
        int i = 0;
        while(v!=0){
            i += v&0x01;
            v >>= 1;
        }
        return i;
    }

    //ʱ�临�Ӷȸ��ͣ�O(n) nΪ����1�ĸ���
    private static int count2(int v){
        int num = 0;
        while(v!=0){
            v &= (v-1);
            num ++;
        }
        return num;
    }
}
