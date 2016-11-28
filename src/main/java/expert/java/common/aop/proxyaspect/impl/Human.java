package expert.java.common.aop.proxyaspect.impl;

import expert.java.common.aop.proxyaspect.Sleepable;

/**
 * Created by patrick on 2016/2/23.
 */
public class Human implements Sleepable {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
