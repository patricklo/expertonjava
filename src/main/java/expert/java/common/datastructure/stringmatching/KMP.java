package expert.java.common.datastructure.stringmatching;

/**
 * Created by patricklo on 11/11/16.
 * Brute-Force被称为简单模式匹配算法。
 * <p>
 * <p>
 * KMP算法是它的改进算法。
 * 一种改进的字符串匹配算法，由D.E.Knuth与V.R.Pratt和J.H.Morris同时发现，
 * 因此称之为KMP算法。
 * 此算法可以在O(n+m)的时间数量级上完成串的模式匹配操作，
 * 其基本思想是：每当匹配过程中出现字符串比较不等时，不需回溯指针，
 * 而是利用已经得到的“部分匹配”结果将模式向右“滑动”尽可能远的一段距离，继续进行比较。
 */
public class KMP {
    public static void main(String[] args) {
            kmp("aaaaaaaaaab","aaab");
    }

    //根据给定主串和子串，采用KMP算法
    public static int kmp(String src, String sub) {
        //先生成模式串sub的next[j]
        int[] next = getNext(sub);

        //i:主串的游标
        //j:子串的游标
        int i = 0, j = 0, index = -1;
        while (i < src.length() && j < sub.length()) {
            if (src.charAt(i) == sub.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j]; //向右滑动
            }

        }
        if (j == sub.length()) {
            index = i - sub.length();
        }
        return index;
    }

    //根据给定的模式串，求next[j]的算法
    public static int[] getNext(String sub) {
        int j = 1, k = 0;
        int[] next = new int[sub.length()];
        next[0] = -1;
        next[1] = 0;

        while (j < sub.length() - 1) {
            if (sub.charAt(j) == sub.charAt(k)) {
                next[j + 1] = k + 1;
                j++;
                k++;
            } else if (k == 0) {
                next[j + 1] = 0;
                j++;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
