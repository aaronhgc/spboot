package com.mugen;

import java.lang.reflect.Constructor;

/**
 * Created by aaron on 2016/12/27.
 */
public class Test {

    @org.junit.Test
    public void contextLoads() {
        Class ifaceClass = null;
        try {
            ifaceClass = Class.forName("com.mugen.admin.thriftint.UserService" + "$Iface");
            Class processorClass = Class.forName("com.mugen.admin.thriftint.UserService" + "$Processor");
            Constructor<?> con = processorClass.getConstructors()[0];
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }
}
