package practice.java.designpatterns.singleton;

/**
 * Created by patrick on 12/18/14.
 */
public class Singleton {
    private static volatile Singleton instance;

    private Singleton(){}

    public static Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                //double checked locking - because second check of Singleton instance with lock
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
