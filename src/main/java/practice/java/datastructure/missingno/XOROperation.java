package practice.java.datastructure.missingno;

/**
 * Created by Patrick on 1/27/2015.
 */
public class XOROperation {
    public static void main(String[] args){
        //eXclusive OR operation to exchange the value between two variables.
        int x=2,y=5;
        System.out.println("before:");
        System.out.println("x="+x+"; y="+y);

        x=x^y;
        y=y^x;
        x=x^y;

        System.out.println("after:");
        System.out.println("x="+x+"; y="+y);

    }
}
