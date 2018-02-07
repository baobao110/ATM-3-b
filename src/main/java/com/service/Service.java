package com.service;

import java.util.ArrayList;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.AccountFlow.Account;
import com.BankCard.Card;
import com.base.DataBase;
import com.exception.newException;
import com.fenye.Fenye;
import com.flow.flow;
import com.inter.AccountDAO;
import com.inter.CardDAO;
import com.util.Dateformate;



@Component	//这里是Spring的注入运行时Spring会自动扫描该注入的作用域,这里是配合Control层使用的
@Transactional	//这里的注入是用于spring和mybaties结合时的事务管理,它等同于mybaties中的sqlSession的connection,这里不需要手动的开启和关闭事务
public class Service {
	
	@Autowired
	private CardDAO cardDao;
	
	@Autowired
	private AccountDAO accountDao;

	private Logger log=LoggerFactory.getLogger(Service.class);
	
	@Transactional(rollbackFor=Exception.class)
	public Card open(String username,String password) {
		Card card=new Card();
		card.setNumber(DataBase.CreateNumber());
		card.setPassword(DigestUtils.md5Hex(password));
		card.setMoney(0);
		card.setUsername(username);
		System.out.println(card);
		if(cardDao.open(card)==1) {//这里调用Card.xml类中的SQL语句
			return card;
			}
		else {
			throw new newException("开户失败");
		}
	}
	//注意这里的异常处理如果是Exception就回滚,如果是自定义的RuntimeException异常就抛到Control层,通过try catch捕获最后将错误信息以ajax方式在前端以页面显示
	
	@Transactional(rollbackFor=Exception.class)
	public void save(int number,String password,double money,String username)  {
			Card card=cardDao.GetCad(number);//这里可以和之前的使用mybaties的获取事务进行比较就可以发现用spring+mybaties的优点,这里自动创建对象
			if(null==card) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(!DigestUtils.md5Hex(password).equals(card.getPassword())) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(money<0) {
				log.info("金额小于零");
				throw new newException("金额小于零");
			}
			double x=card.getMoney();
			x+=money;
			if(cardDao.modifyMoney(number, x)!=0) {
				log.info("存钱成功");	
			}
			Account account=new Account();
			account.setNumber(number);
			account.setMoney(money);
			account.setType(1);
			account.setDescription("存钱");
			account.setUsername(username);
			accountDao.add(account);
			log.info("流水账产生");
		}
		
	@Transactional(rollbackFor=Exception.class)
	public void draw(int number,String password,double money,String username) {
			Card card=cardDao.GetCad(number);
			if(null==card) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(!DigestUtils.md5Hex(password).equals(card.getPassword())) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(money<0) {
				log.info("金额小于零");
				throw new newException("金额小于零");
			}
			double x=card.getMoney();
			if(money>x) {
				log.info("金额不足");
				throw new newException("金额不足");
			}
			x-=money;
			if(cardDao.modifyMoney(number, x)!=0) {
				log.info("取钱成功");
			}
			Account account=new Account();
			account.setNumber(number);
			account.setMoney(money);
			account.setType(1);
			account.setDescription("取钱");
			account.setUsername(username);
			accountDao.add(account);
			log.info("流水账产生");
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void transfer(int OutNumber,String password,double money,int InNumber,String username) {
			Card card1=cardDao.GetCad(OutNumber);
			if(null==card1) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
				
			}
			if(!DigestUtils.md5Hex(password).equals(card1.getPassword())) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(money<0) {
				log.info("金额小于零");
				throw new newException("金额小于零");
			}
			double x=card1.getMoney();
			if(money>x) {
				log.info("金额不足");
				throw new newException("金额不足");
			}
			x-=money;
			if(cardDao.modifyMoney(OutNumber, x)!=0) {
				log.info("取钱成功");
			}
			Account account1=new Account();
			account1.setNumber(OutNumber);
			account1.setMoney(money);
			account1.setType(1);
			account1.setDescription("取钱");
			account1.setUsername(username);
			accountDao.add(account1);
			log.info("流水账产生");
			Card card2=cardDao.GetCad(InNumber);
			if(null==card2) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
				
			}
			double y=card2.getMoney();
			y+=money;
			if(cardDao.modifyMoney(InNumber, y)!=0) {
				log.info("转入成功");	
			}
			Account account2=new Account();
			account2.setNumber(InNumber);
			account2.setMoney(money);
			account2.setType(1);
			account2.setDescription("存钱");
			account2.setUsername(username);
			accountDao.add(account2);
			log.info("流水账产生");
		}//这里注意cardDao的使用
	
	@Transactional(rollbackFor=Exception.class)
	public Card ChangePassword(int number,String oldPassword,String newPassword) {
			Card a=cardDao.GetCad(number);
			if(null==a) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(!DigestUtils.md5Hex(oldPassword).equals(a.getPassword())) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			if(cardDao.modifyPassword(number, DigestUtils.md5Hex(newPassword))!=0) {
				a=cardDao.GetCad(number);
				return a;
			}
			else {
				 throw new newException("账号或者密码不存在");
			}
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Fenye List(int number, String password, int currentPage) {
		Card card=cardDao.GetCad(number);
		if(null==card) {
			log.info("账号或者密码不存在");
			throw new newException("账号或者密码不存在");
		}
		if(!DigestUtils.md5Hex(password).equals(card.getPassword())) {
			log.info("账号或者密码不存在");
			throw new newException("账号或者密码不存在");
		}
		int totalNumber=accountDao.totalNumber(number);//调用totalNumber方法获取总纪录数目
		Fenye fenye=new Fenye(totalNumber, currentPage);//初始化，参数为总记录和当前页数
		 ArrayList<Account>list=accountDao.List(number, fenye.getcurrentNumber(), fenye.move);//调用List()方法获取该number的该页内容
		 ArrayList<flow> account=new ArrayList<>(list.size());
			for(Account i:list) {
				flow a=new flow();
				a.setId(i.getId());
				a.setNumber(i.getNumber());
				a.setMoney(i.getMoney());
				a.setType(i.getType());
				a.setCreatetime(Dateformate.formate(i.getCreatetime()));
				a.setDescription(i.getDescription());
				account.add(a);
			}
		 fenye.setObject(account);//将获取的记录保存
		 if(null==fenye) {
			 throw new newException("数据为空");
		 }
		 else {
			 return fenye;
		 }
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int total(int number) {
		return accountDao.totalNumber(number);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public Card GetCard(int number) {
			Card a=cardDao.GetCad(number);
			if(null==a) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			else {
				return a;
			}
		}
	
	@Transactional(rollbackFor=Exception.class)
	public Card Get(int number,String password) {
			Card a=cardDao.GetCard(number,DigestUtils.md5Hex(password));
			if(null==a) {
				log.info("账号或者密码不存在");
				throw new newException("账号或者密码不存在");
			}
			else {
				return a;
			}
		}
	
	@Transactional(rollbackFor=Exception.class)//作用处理检查型异常
	public void delete(int number) {
			int i=cardDao.delete(number);
			System.out.println(i);
			if(i==0) {
				log.info("账号或者密码不存在");
			}
	}
	
	public ArrayList<flow> ten(String username){
		System.out.println("server username"+username);
		ArrayList<Account> account=accountDao.ten(username);
		ArrayList<flow> list=new ArrayList<>(account.size());
		for(Account i:account) {
			flow a=new flow();
			a.setId(i.getId());
			a.setNumber(i.getNumber());
			a.setMoney(i.getMoney());
			a.setType(i.getType());
			a.setCreatetime(Dateformate.formate(i.getCreatetime()));
			a.setDescription(i.getDescription());
			list.add(a);
		}
		return list;
	}
}
/*
 * 什么是检查型异常,什么是非检查型异常,这里的区分方法是:
 * 1 检查型异常继承于Exception 非检查型异常继承于RuntimException或者Error
 * 2 检查型异常必须捕获,但是非检查型异常可以不捕获
 * /
 */