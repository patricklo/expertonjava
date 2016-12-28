package com.lo.serviceloader.sample2;

import java.util.ServiceLoader;

/**
 * Created by patricklo on 12/28/16.
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Command> serviceLoader= ServiceLoader.load(Command.class);
        for(Command command:serviceLoader){
            command.execute();
        }
    }

}
