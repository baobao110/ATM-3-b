package com.inter;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.BankCard.Card;

public interface CardDAO {
	public Card Get(@Param("number")int number,@Param("password")String password);
	/*
	 * 这里的DAO层和以前JDBC时不一样，这里作为接口，只需要定义方法就可以,同时需要注意这里的@Param()这里是给参数加注解
	 */
	public int modifyPassword(@Param("number")int number,@Param("newPassword")String newPassword);//多个参数要起别名
	public int modifyMoney(@Param("number")int number,@Param("money")double money);
	public int open(Card card);
	
	public ArrayList<Card> List(@Param("number")int number,@Param("currentNumber")int currentNumber,@Param("move")int move);
	public ArrayList<Card> list(@Param("username")String usernamer,@Param("currentNumber")int currentNumber,@Param("move")int move);
	public int totalNumber(int number);
	public int total(String username);
	public Card GetCad(int number);
	public Card GetCard(@Param("number")int number,@Param("password")String password);
	public int  delete(int number);
	public ArrayList<Card> flow(String username);
}
