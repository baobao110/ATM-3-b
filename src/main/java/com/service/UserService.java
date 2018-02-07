package com.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.DataBase;
import com.exception.newException;
import com.inter.UserDAO;
import com.user.User;

@Component
@Transactional
public class UserService {
	
	@Autowired
	private UserDAO userDao;
	
	private Logger log=LoggerFactory.getLogger(UserService.class);
	
	@Transactional(rollbackFor=Exception.class)
	public User register(String username,String password) {
		User user=new User();
		user.setUsername(username);
		user.setPassword(DigestUtils.md5Hex(password));//DigestUtils.md5Hex(password) MD5¼ÓÃÜ
		if(userDao.getUser(username)!=null) {
			throw new newException("×¢²áÊ§°Ü");
		}
		int i=userDao.addUser(user);
		if(i!=1) {
			
			throw new newException("×¢²áÊ§°Ü");
		}
		return user;
	}
	

	@Transactional(rollbackFor=Exception.class)
	public User login(String username,String password) {
		User user=userDao.getUser(username);
		if(null==user) {
			log.warn("ÓÃ»§µÇÂ¼Ê§°Ü");
			throw new newException("ÓÃ»§µÇÂ¼Ê§°Ü");
		}
		if(!DigestUtils.md5Hex(password).equals(user.getPassword()))
		{
			log.warn("ÓÃ»§µÇÂ¼Ê§°Ü");
			throw new newException("ÓÃ»§µÇÂ¼Ê§°Ü");
		}
		return user;
	}
	
	public User get(String username) {
		if(null==userDao.getUser(username))
		{
			return null;
		}
		else {
			return userDao.getUser(username);
		}
	}

}
