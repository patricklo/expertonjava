package com.lo.serviceloader.sample2;

/**
 * Created by patricklo on 12/28/16.
 */
public class ShutdownCommand implements Command{
    public void execute() {
        System.out.println("shutdown....");
    }
}
