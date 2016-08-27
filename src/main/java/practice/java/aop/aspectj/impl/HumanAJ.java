package practice.java.aop.aspectj.impl;

import practice.java.aop.aspectj.SleepableAJ;

/**
 * Created by patrick on 2016/2/23.
 */
public class HumanAJ implements SleepableAJ {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
