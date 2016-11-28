package com.expertonjava.osgi.sample.service.impl;

import com.expertonjava.osgi.sample.service.Hello;

/**
 * Created by Patrick on 4/9/16.
 */
public class HelloImpl implements Hello {

    private String name = "default-name";

    public HelloImpl(String name){
        this.name = name;
    }
    public void sayHello(String text) {
        System.out.println(text+name);
    }
}
