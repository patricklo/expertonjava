package practice.java.datastructure.binarytree.counting;

/**
 * Created by patrick on 2/2/15.
 */
public class CountBinaryNo {

    //计算二进制中1的个数
    public static void main(String[] args){
        System.out.println(20/5);
    }

    private static int count(int v){
        int num = 0;

        while(v!=0){
            int temp = v%2;
            if(temp==1){
                System.out.println("temp="+temp);
                num++;
            }
            v = v/2;
            System.out.println("v="+v);
        }
        return num;

    }


    //时间复杂度为：O(log v)
    private static int count1(int v){

        int i = 0;
        while(v!=0){
            i += v&0x01;
            v >>= 1;
            //i++;
        }

        return i;
    }
    //时间复杂度更低：O(n) n为具体1的个数
    private static int count2(int v){
        int num = 0;
        while(v!=0){
        /*

        */

            v &= (v-1);
            num ++;
        }
        return num;
    }



}
