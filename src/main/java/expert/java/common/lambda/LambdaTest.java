package expert.java.common.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by patrick on 6/21/2015.
 */
public class LambdaTest {

    public static void main(String [] args)throws InterruptedException{

        /*
        1.
         */
        Thread thread01 = new Thread(new Runnable() {
            public void run() {
                System.out.println("Before Java 8");
            }
        });
        thread01.start();
        thread01.join();

        Thread thread02 = new Thread(() -> System.out.println("Java 8"));
        thread02.start();
        thread02.join();


        /*
        2.
         */
        //Before java 8
        List features = Arrays.asList("Lambdas", "Default Method",
                "Stream API", "Date and Time API");

        for (Object feature : features) {
            System.out.println(feature);
        }

        //After Java8
        features.forEach((n) -> System.out.println(n));

        //moreover
        System.out.println("MoreOver....");
        features.forEach(System.out::println);




        /*
        3
         */

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.toString().startsWith("J"));
    }

    public static void filter(List<String> names, Predicate condition){
        for(String name:names){
            if(condition.test(name)){
                System.out.println(name);
            }
        }
    }
}
