package practice.java.other;

import org.mockito.internal.matchers.Null;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by patrick on 12/18/14.
 */
public class Test {
    private ConcurrentHashMap concurrentHashMap;
    private HashMap hashMap;
    private Thread thread;
    public static int staticInt = 10;


    public int nonStaticInt;
    public static void main(String [] args){

        Object a = new Object();
        Object b = new Object();
        System.out.println(a.equals(b));

        String x = new String("xyz");
        String y = new String("xyz");
        x.substring(1);
        System.out.println(x.equals(y));
    }

    protected void testOverride() throws NullPointerException{
        System.out.println("Test");
    }
}
