package com.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fenye.Fenye;
import com.service.CardService;

public abstract class control {

	public abstract String toUsercenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException ;

}
