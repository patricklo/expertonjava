package practice.java.aop.pojoaspect.impl;

import practice.java.aop.pojoaspect.SleepablePOJO;

/**
 * Created by patrick on 2016/2/23.
 */
public class HumanPOJO implements SleepablePOJO {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
