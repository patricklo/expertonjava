package practice.java.multithreading.memoryleak;

import java.util.Map;

/**
 * Created by patrick on 7/23/2015.
 */
public class MemLeak {
    public final String key;
    public MemLeak(String key) {
        this.key = key;
    }

    public static void main(String args[]) {
        try {
            Map map = System.getProperties();
            for(;;) {
                map.put(new MemLeak("Key"),"value");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
