package com.patric.hsf.test;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by patricklo on 12/22/16.
 */
public class TestClassVar {


    private final AtomicBoolean isProviderStarted = new AtomicBoolean(false);

    public void testSetValue(){
       boolean isSuccess =  isProviderStarted.compareAndSet(false,true);
        System.out.println(isSuccess);
    }
}
