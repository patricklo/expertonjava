package practice.java.reflection;

/**
 * Created by Patrick on 21/2/16.
 */
public class ClassInfo {

    static {
        System.out.println("Static block");
    }

    public ClassInfo(){
        System.out.println("Constructor block");
    }
}
