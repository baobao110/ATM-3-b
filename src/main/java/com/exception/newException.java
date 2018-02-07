package com.exception;

public class newException extends RuntimeException {
	
	public newException(String msg) {
		super(msg);
	}
}
//注意这里的异常继承于RuntimeException的可以和Service层的异常处理