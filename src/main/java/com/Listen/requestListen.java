package com.Listen;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.control.cardControl;

public class requestListen implements ServletRequestListener {
	
	private  Logger log=LoggerFactory.getLogger(requestListen.class);

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		log.info("------request end-------");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		log.info("-------request init-----");
		System.out.println(arg0.getServletRequest().getServletContext());
	}

}
