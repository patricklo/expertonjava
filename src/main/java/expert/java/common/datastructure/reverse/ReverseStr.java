package expert.java.common.datastructure.reverse;

/**
 * Created by patrick on 4/29/15.
 */
public class ReverseStr {

    public static void main(String [] args){
        String reverseStr=reverseRecursively("abcd");
        System.out.println(reverseStr);

        System.out.println(reverseStr.substring(1));
    }
    public static String reverseRecursively(String str) {

        //base case to handle one char string and empty string
        if (str.length() < 2) {
            return str;
        }

        return reverseRecursively(str.substring(1)) + str.charAt(0);

    }

}
