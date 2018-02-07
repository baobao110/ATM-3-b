package com.inter;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.AccountFlow.Account;

public interface AccountDAO {
	public int add(Account account);//添加记录
	public ArrayList<Account> List(@Param("number")int number,@Param("currentNumber")int currentNumber,@Param("move")int move);
	/*
	 * 这个方法是返回分页中该页的所有记录，number是查询的卡号,currentNumber是指当前页的起始页首条记录,move指该页显示页面的条数
	 */
	public int totalNumber(int number);//获取所有的记录条数
	
	public ArrayList<Account> ten(String username);
}
