package expert.java.common.multithreading.memoryleak;

/**
 * Created by Patrick on 15/11/16.
 */
public class MemLeak2 {
    public static void main(String[] args) {
        while (true) {
            Element first = new Element();
            first.next = new Element();
            first.next.next = first;
        }
    }
}
