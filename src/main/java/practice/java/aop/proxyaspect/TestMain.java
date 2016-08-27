package practice.java.aop.proxyaspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by patrick on 2016/2/23.
 */
public class TestMain {
    public static void main(String[] args){
        //方法1：
//        ApplicationContext appCtx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
//        Sleepable sleeper = (Sleepable)appCtx.getBean("humanProxy");
//        sleeper.sleep();

        //方法2：
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        Sleepable sleeper = (Sleepable)appCtx.getBean("human");
        sleeper.sleep();


    }
}
