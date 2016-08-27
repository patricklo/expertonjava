package expert.java.common.aop.pojoaspect.utils;

/**
 * Created by patrick on 2016/2/23.
 */
public class SleepHelperPOJO {
    public SleepHelperPOJO(){

    }


    public void afterSleep() throws Throwable {
        System.out.println("起床后要先穿衣服！");
    }


    public void beforeSleep() throws Throwable {
        System.out.println("睡觉之前要洗澡换衣服！");
    }
}
