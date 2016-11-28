package expert.java.common.aop.pojoaspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by patrick on 2016/2/23.
 */
public class TestMainPOJO {
    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("springmvc-servlet.xml");
        SleepablePOJO human = (SleepablePOJO)appCtx.getBean("human");
        human.sleep();
    }
}
