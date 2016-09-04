package com.expertonjava.osgi.sample;

import com.expertonjava.osgi.sample.service.Hello;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * Created by Patrick on 4/9/16.
 */
public class Activator implements BundleActivator {
    public void start(BundleContext ctx) throws Exception {
        System.out.println("----------------hello client start---------------------");
        ServiceReference ref = ctx.getServiceReference(Hello.class.getName());
        if (ref != null) {
            Hello hello = null;
            try {
                hello = (Hello) ctx.getService(ref);
                if (hello != null)
                    hello.sayHello("Patrick");
                else
                    System.out.println("Service:Hello---object null");
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                ctx.ungetService(ref);
                hello = null;
            }
        } else {
            System.out.println("Service:Hello---not exists");
        }
        System.out.println("----------------hello client start---------------------");

    }

    public void stop(BundleContext bundleContext) throws Exception {

    }
}
