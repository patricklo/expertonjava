package practice.java.aop.proxyaspect.impl;

import practice.java.aop.proxyaspect.Sleepable;

/**
 * Created by patrick on 2016/2/23.
 */
public class Human implements Sleepable {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
