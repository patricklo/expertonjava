package com.patric.hsf.test;

import com.taobao.hsf.standalone.HSFEasyStarter;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by patricklo on 11/28/16.
 */
public class TestProvider {

    protected static final String HSF_PATH = "/Users/patricklo/Documents/devel/hsf/taobao-hsf-bak.sar";

    static {
        HSFEasyStarter.start(HSF_PATH, "");
        String springResourcePath = "classpath:spring_hsf_consumer_for_test_only.xml";
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(springResourcePath);
        ctx.start();
    }

    public static void main(String[] args) {
        System.out.println("start");
    }
}