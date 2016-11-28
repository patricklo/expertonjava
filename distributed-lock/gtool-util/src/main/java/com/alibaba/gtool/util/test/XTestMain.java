package com.alibaba.gtool.util.test;

import java.util.concurrent.TimeUnit;

/**
 * 测试
 *
 * @author gumao
 * @since 16/10/28
 */
public class XTestMain {
    public  static void main(String args[]){
       long aa= TimeUnit.SECONDS.toMillis(1);
        long bb= TimeUnit.MINUTES.toMillis(1);
        System.out.println("  "+aa+"  "+bb );
    }
}
