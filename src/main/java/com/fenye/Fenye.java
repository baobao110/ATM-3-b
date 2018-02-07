package com.fenye;

public class Fenye {

	public static final int move=3;//规定每页的显示条数
	private int totalNumber=0;//记录的总数
	private int totalPage=1;//显示的总页数
	private int currentNumber=0;//当前记录的号码
	private int currentPage=1;//当前页
	private Object object;//当前页面的信息
	
	public Fenye(int totalNumber, int currentPage) {
		this.totalNumber = totalNumber;
		this.currentPage = currentPage;
	}
	
	public int getcurrentNumber() {
		return (currentPage-1)*move;
	}
	
	public int getTotalPage() {
		return totalNumber% move==0 ? totalNumber/ move :(totalNumber/move +1);
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	
	
	
	
	
	

}
