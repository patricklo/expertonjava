package practice.java.aop.aspectj.utils;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * Created by patrick on 2016/2/23.
 */
@Aspect
public class SleepHelperAJ {
    public SleepHelperAJ(){

    }

    @Pointcut("execution(* *.sleep())")
    public void sleepPointCut(){}

    @AfterReturning("sleepPointCut()")
    public void afterSleep() throws Throwable {
        System.out.println("起床后要先穿衣服！");
    }


    @Before("sleepPointCut()")
    public void beforeSleep() throws Throwable {
        System.out.println("睡觉之前要洗澡换衣服！");
    }
}
