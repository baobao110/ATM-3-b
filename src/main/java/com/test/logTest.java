package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class logTest {
	
	private static Logger log=LoggerFactory.getLogger(logTest.class);
	
	public  static void main(String []args) {
		// TODO Auto-generated method stub
		log.trace("111---");
		log.debug("2222");
		log.info("333");
		log.warn("444");
		log.error("555");
	}
}
