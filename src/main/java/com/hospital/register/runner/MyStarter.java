package com.hospital.register.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.hospital.register.bean.User;
import com.hospital.register.context.Context;
import com.hospital.register.service.UserService;

@Component
@Order(value = 1)
public class MyStarter implements CommandLineRunner {
	
	@Autowired
    private UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(MyStarter.class);
	@Override
	public void run(String... args) throws Exception {
		logger.info(this.getClass().getName() + "启动加载数据" + args);
		List<User> users = userService.findAllUser(0, 2);
		
		for(User item : users) {
			Context.USER_MAP.put(item.getUserId(),item);
			System.out.println(item.toString());
		}
		
	}

}
