package expert.java.common.aop.pojoaspect.impl;

import expert.java.common.aop.pojoaspect.SleepablePOJO;

/**
 * Created by patrick on 2016/2/23.
 */
public class HumanPOJO implements SleepablePOJO {

    @Override
    public void sleep() {
        System.out.println("Sleeping");
    }
}
