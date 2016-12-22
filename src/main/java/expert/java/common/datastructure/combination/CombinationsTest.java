package expert.java.common.datastructure.combination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Patrick on 1/17/2015.
 * <p>
 * 给定一个数组,得出其字符的所有组合
 * 如:abc
 *
 * 所有组合数应为:27个
 */
public class CombinationsTest {

    static List<String> getCombination(int length, char[] chars, List<String> strList) {
        if (strList.size() == 0) {
            strList.add("");
        }
        if (strList.get(0).length() >= length) {
            return strList;
        }
        List<String> newList = new ArrayList<String>(chars.length * strList.size());
        for (String str : strList) {
            for (char c : chars) {
                newList.add(str + c);
            }
        }
        return getCombination(length, chars, newList);
    }


    public static void main(String[] args) {
        List<String> result = getCombination(3, "abc".toCharArray(), new ArrayList<String>());
        for (String str : result) {
            System.out.println(str);
        }
    }

}
