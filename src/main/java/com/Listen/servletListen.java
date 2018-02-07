package com.Listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.control.cardControl;
import com.handler.Hand;

public class servletListen implements ServletContextListener {

	private  Logger log=LoggerFactory.getLogger(servletListen.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("-------servletListen end");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		log.info("-------servletListen init");
		Hand.init();
	}

}
