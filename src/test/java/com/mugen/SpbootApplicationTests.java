package com.mugen;

import com.mugen.admin.thrift.ThriftServicesConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Constructor;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpbootApplicationTests {
	@Autowired
	private ThriftServicesConfig propsConfig;

	@Test
	public void contextLoad2s() {
		Class ifaceClass = null;
		try {
			ifaceClass = Class.forName("com.mugen.admin.thriftint.UserService" + "$Iface");
			Class processorClass = Class.forName("com.mugen.admin.thriftint.UserService" + "$Processor");
			Constructor<?> con = processorClass.getConstructors()[0];
			System.out.println("333:"+con.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}




	}

}
