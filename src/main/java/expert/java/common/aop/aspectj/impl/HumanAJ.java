package expert.java.common.aop.aspectj.impl;

import expert.java.common.aop.aspectj.SleepableAJ;

/**
 * Created by patrick on 2016/2/23.
 */
public class HumanAJ implements SleepableAJ {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
