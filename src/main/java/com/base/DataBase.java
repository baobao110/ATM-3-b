package com.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataBase {
	/*private static SqlSessionFactory ssf;
	static {
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream("config.xml");
			ssf = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static SqlSession open(boolean flag) {
		return ssf.openSession(flag);
		
		 * 这里需要设置将SqlSession设置为true,就是自动提交如果不设置为true，它的默认为false,
		 * 如果不设置需要自己手动的提交在最后要添加session.commit(),所以最好在开始就设置为true
		 * 这和JDBC中的不一样，在JDBC中默认为自动
		 
	}*/
	//这里已经不需要再使用的mybaties的 SqlSessionFactory
	
	public static int CreateNumber() {
		String a="";
		Random b=new Random();
		a+=(b.nextInt(9)+1);
		for(int i=0;i<5;i++) {
			Random c=new Random();
			a+=c.nextInt(10);
		}
		return Integer.parseInt(a);
	}
}
