package com.BankCard;
import java.util.Date;//这里为什么用util不是sql因为util在 流水界面前端时可以显示时分秒但是sql默认0

import com.alibaba.fastjson.annotation.JSONField;

public class Card {
	private int id;
	private int number;
	private String password;
	private double money;
	private Date createtime;
	private Date modifytime;
	private String username;
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public Date getModifytime() {
		return modifytime;
	}
	
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", password=" + password + ", money=" + money + ", createtime="
				+ createtime + ", modifytime=" + modifytime + ", username=" + username + "]";
	}
	
	
	
//这里是根据数据库表建立的类和JDBC一样
	
}
