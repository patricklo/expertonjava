package expert.java.common.memory;

import java.util.Map;

/**
 * Created by patricklo on 11/14/16.
 */
public class MemoryLeakTest {
    public final String key;

    public MemoryLeakTest(String key) {
        this.key = key;
    }

    public static void main(String args[]) {
        try {
            Map map = System.getProperties();
            for (; ; ) {
                map.put(new MemoryLeakTest("key"), "value");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
