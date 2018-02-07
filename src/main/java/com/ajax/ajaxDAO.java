package com.ajax;

import com.alibaba.fastjson.JSON;

public class ajaxDAO {

	private boolean success;
	private String message;
	private Object data;
	private int code = 200;
	
	public ajaxDAO(boolean success, Object data) {
		super();
		this.success = success;
		this.data = data;
	}
	
	
	public ajaxDAO(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}


	public static ajaxDAO success() {
		return new ajaxDAO(true," ");
	}
	
	public static ajaxDAO failure() {
		return new ajaxDAO(false," ");
	}
	
	public static ajaxDAO success(Object data) {
		return new ajaxDAO(true, data);
	}
	
	public static ajaxDAO failure(String message) {
		return new ajaxDAO(false, message);
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}
//	这里唯一需要注意的是ajax需要有get和set方法
	
}
