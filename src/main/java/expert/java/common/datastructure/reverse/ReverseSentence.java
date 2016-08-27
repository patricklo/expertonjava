package expert.java.common.datastructure.reverse;

/**
 * Created by patrick on 4/29/15.
 */
public class ReverseSentence {

    public static void main(String [] args){

    }

    public static char[] reversetSentence(String str){
        if(str == null){
            return null;
        }
        char[] strChar = str.toCharArray();
        reverseStringOnly(strChar);

        //上面方法单纯把一个句子翻转如：
        // I am a student -> .tneduts a ma I

        //下面调用同一方法，针对每个单词再逆转
        //实现类似

        return strChar;


    }

    public static void reverseStringOnly(char[] str){
        //单纯把一个句子翻转如：
        // I am a student -> .tneduts a ma I
        if(str == null){
            return;
        }
        int stringBegin =0;
        int stringEnd = str.length-1;

        while(stringBegin < stringEnd){
            char temp = str[stringBegin];
            str[stringBegin] = str[stringEnd];
            str[stringEnd] = temp;

            stringBegin++;
            stringEnd--;
        }

    }
}
