package com.mugen.admin.thriftint.impl;


import com.mugen.admin.thriftint.UserDto;
import com.mugen.admin.thriftint.UserService;
import com.mugen.admin.thriftint.UserService1;
import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserServiceImpl1 implements UserService1.Iface{

	@Override
	public UserDto getUser() throws TException
	  {		
		UserDto user = new UserDto(1000,"aaron");
		return user;
	  }

}
