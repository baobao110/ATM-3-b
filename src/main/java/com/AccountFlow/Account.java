package com.AccountFlow;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Account {
	private int id;
	private int number;
	private double money;
	private int type;
	private Date createtime;
	private String description;
	private String username;
	
	
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", number=" + number + ", money=" + money + ", type=" + type + ", createtime="
				+ createtime + ", description=" + description + ", username=" + username + "]";
	}
	
	
	
}
