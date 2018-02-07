package com.Listen;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.control.cardControl;

public class sessionListen implements HttpSessionListener {
	
	private  Logger log=LoggerFactory.getLogger(sessionListen.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		log.info("------ sessionListen  init------");
		log.info(arg0.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		log.info("------ sessionListen-----init------");
	}

}
