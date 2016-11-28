package expert.java.common.datastructure.stringmatching;

/**
 * Created by patricklo on 11/11/16.

 该方法的优点是：算法简单明朗，便于实现记忆。

 缺点是：进行了回溯，效率不高，而这些都是没有必要的.
 */
public class BruteForce {
    public int bruteForceAlgo(String source,String sub){
        int j = 0, i = 0,index=-1;
        while (i < source.length() && j < sub.length()) {
            if (source.charAt(i) == sub.charAt(j))
            {
                i++; j++;
            } else {
                // 使i回退到下一个字符，应为子串的前面j向可能匹配成功，而第j+1项失败，所以 i=i-j+1
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == sub.length()) {
            index = i - sub.length();
        } else {
            index = -1;
        }

        return index;

    }

    public static void main(String[] args){
        String source = "sourcesubsource";
        String sub = "sub";
        BruteForce bruteForce = new BruteForce();
        int index = bruteForce.bruteForceAlgo(source,sub);
        System.out.println(index);
    }
}
