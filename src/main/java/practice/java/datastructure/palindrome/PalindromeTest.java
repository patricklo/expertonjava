package practice.java.datastructure.palindrome;

/**
 * Created by patrick on 6/15/2015.
 */
public class PalindromeTest {
    public static void main(String[] args){
        String testStr = "asdfasdf";
        String subStr = testStr.substring(1);
        System.out.println(subStr);
        String reverseStr = palindromTest(testStr);
        System.out.println("Ori String : "+ testStr);
        System.out.println("Reverse String : "+ reverseStr);

        int testInt = 120011;
        int reverseInt = palindromTest( testInt);
        System.out.println("Ori Int : "+ testInt);
        System.out.println("Reverse Int : "+ reverseInt);
    }


    // Use Recursion
    public static String palindromTest(String testStr){
        if(testStr == null){
            System.out.println("the string is empty");
            return null;
        }
        if(testStr.length()== 1 ){
            return testStr;
        }
        return palindromTest(testStr.substring(1)) +testStr.charAt(0);
    }

    //Use Iteration
    public static String palindromTestByIteration(String testStr){
        StringBuilder sb = new StringBuilder();
        char[] testStrArray = testStr.toCharArray();
        for(int i= testStrArray.length-1;i >= 0 ; i--){
            sb.append(testStrArray[i]);
        }
        return sb.toString();
    }


    public static int palindromTest(int testInt){
        if(testInt == 0) {
            System.out.println("empty");
            return 0;
        }

        int reverseInt = 0;
        while(testInt != 0){
            reverseInt = reverseInt * 10 + testInt%10;
            testInt =testInt/10;
        }
        return reverseInt;
    }

}
