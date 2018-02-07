package com.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.control.cardControl;

public class Hand {

	private static ApplicationContext context;

	private static  Logger log=LoggerFactory.getLogger(Hand.class);
	
	public static void init() {
		context = new ClassPathXmlApplicationContext(new String[] {"/spring-config.xml"});//这里配置Spring唯一需要注意的就是spring。xml文件的路径
	}
	
	public static Object getController(String value) {
		log.info(value);
		return context.getBean(value);
	}

}
