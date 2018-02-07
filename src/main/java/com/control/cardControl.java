package com.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.AccountFlow.Account;
import com.BankCard.Card;
import com.ajax.ajaxDAO;
import com.fenye.Fenye;
import com.service.CardService;
import com.service.Service;
import com.test.logTest;
import com.user.User;


@Controller	//这里的注入写法固定,SpringMVC会自动根据注入找到Control类
@RequestMapping("/card")//这里就比较有意思,SpringMVC中这个注解是获取前端的url地址片,根据url地址找到相应的类和方法进行相应的操作
public class cardControl extends control{
	
	@Autowired	//这个注解程序会自动找到相关的类,进行自动的初始化,这样就不需要用set方法进行初始化,这里可以结合Service层的@Component
	private Service service;
	
	@Autowired
	private CardService cardservice;
	

	private  Logger log=LoggerFactory.getLogger(cardControl.class);
	
	@RequestMapping("/down")
	public void  down(HttpServletResponse resp,HttpServletRequest req,HttpSession  session) throws IOException {
		User user=(User) session.getAttribute("user");
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		Card cad=service.Get(number,password);
		String filename = number + ".csv";
		resp.setContentType("application/octet-stream");  
		resp.setHeader("Content-Disposition", "attachment;filename="+ filename);  
		
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()))) {
			int currentPage = 1;
			String header = "卡号,金额,备注,时间";
			bw.write(header);
			bw.newLine();
			bw.flush();
			while (true) {
				Fenye list = service.List(number, password, currentPage);
				 ArrayList<Account>account=(ArrayList<Account>) list.getObject();
				if(null==account) {
					break;
				}
				if(account.isEmpty()) {//这里特别注意不然不能下载完成
					break;
				}
				for(Account i:account) {
					StringBuilder text = new StringBuilder();
					text.append(i.getNumber()).append(",")
					.append(i.getMoney()).append(",")
					.append(i.getDescription()).append(",")
					.append(i.getCreatetime()).append(",");
					bw.write(text.toString());
					bw.newLine();
					bw.flush();
					}
				currentPage++;
			}
		}
	}

	
	@RequestMapping("/todelete")
	public String toDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "delete";
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public ajaxDAO delete(String password,String number) throws ServletException, IOException {
		int num=Integer.parseInt(number);
		try {
			Card cad=service.Get(num,password);
			service.delete(num);
			return ajaxDAO.success();
		}
		catch(Exception e) {
			return ajaxDAO.failure(e.getMessage());
		}
	}
	//注意这里的异常的捕获可以和底层联系
	
	@RequestMapping("/openAccount")
	@ResponseBody
	public ajaxDAO openAccount(String password, String confirmPwd,HttpSession session,HttpServletRequest req) throws ServletException, IOException {
		log.info("开户");
		User user=(User) session.getAttribute("user");
		String username=user.getUsername();
		if(!password.equals(confirmPwd)) {
			return ajaxDAO.failure("用户或密码不正确");
		}
		else {
			try {
				Card card=service.open(username,password);
				req.setAttribute("user", user);
				return ajaxDAO.success(card);
			}catch(Exception e) {
				return ajaxDAO.failure(e.getMessage());
			}
		}
	}
	/*
	 * 注意这里的参数session,以前都是在方法中用request获取但是现在用Spring-MVC就简化了,可以在参数栏直接传入
	 */
	
	@RequestMapping("/toOpenAccount")
	public String toOpenAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		javax.servlet.http.HttpSession session = req.getSession();
		return "OpenAccount";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ajaxDAO save(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws ServletException, IOException {
		log.info("存钱");
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		try {
			Card cad=service.Get(number,password);
			User user=(User)session.getAttribute("user");
			service.save(number, password, money,user.getUsername());
			Card card=service.Get(number,password);
			return ajaxDAO.success(card);
		}catch(Exception e) {
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/toSave")
	public String  toSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "save";
	}
	
	@RequestMapping("/transfer")
	@ResponseBody
	public  ajaxDAO transfer(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws ServletException, IOException {
		log.info("汇款");
		String a=req.getParameter("number");
		log.info("number"+a);
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		log.info("password"+password);
		String b=req.getParameter("money");
		log.info("money"+b);
		double money=Double.parseDouble(b);
		try {
			Card cad=service.Get(number,password);
			String c=req.getParameter("InNumber");
			log.info("InNumber"+c);
			int InNumber=Integer.parseInt(c);
			User user=(User)session.getAttribute("user");
			service.transfer(number, password, money, InNumber,user.getUsername());
			Card card1=service.GetCard(number);
			Card card2=service.GetCard(InNumber);
			Card card=service.Get(number,password);
			return ajaxDAO.success(card);
		}
		catch(Exception e) {
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/toTransfer")
	public String toTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "transfer";
	}
	
	@RequestMapping("/check")
	@ResponseBody
	public ajaxDAO  check(HttpServletRequest req, HttpServletResponse resp,HttpSession session) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		try {
			Card cad=service.Get(number,password);
			User user=(User)session.getAttribute("user");
			service.draw(number, password, money,user.getUsername());
			Card card=service.GetCard(number);
			return ajaxDAO.success(card);
		}
		catch(Exception e) {
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/toCheck")
	public String toCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "check";
	}
	
	@RequestMapping("/List")
	@ResponseBody
	public ajaxDAO list(@RequestParam("number")String number,@RequestParam("password")String password,@RequestParam("currentPage")String currentPage) throws ServletException, IOException {
				int num=Integer.parseInt(number);
				log.info("number"+number);
				log.info("password"+password);
				log.info("currentPage"+currentPage);
				currentPage=currentPage ==null ? "1" : currentPage;
				try {
					Fenye list = service.List(num, password, Integer.parseInt(currentPage));//获取记录
					log.info("totalPage"+list.getTotalPage());
					return ajaxDAO.success(list);
				}
				catch(Exception e) {
					return ajaxDAO.failure(e.getMessage());
				}
		}
	
	@RequestMapping("/toList")
	public String toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "list";
	
	}
	
	@RequestMapping("/toChangePassword")
	public String toChangePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "password";
	}
	
	@RequestMapping("/password")
	@ResponseBody
	public ajaxDAO  password(String oldPassword, String newPassword,HttpServletRequest req) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		try {
			Card cad=service.Get(number,oldPassword);
			Card card=service.ChangePassword(number, oldPassword, newPassword);
			return ajaxDAO.success(card);
		}
		catch(Exception e) {
			return ajaxDAO.failure(e.getMessage());
		}
	}
	
	@RequestMapping("/toUsercenter")
	public String toUsercenter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("--------");
		javax.servlet.http.HttpSession session = req.getSession();
		String username= (String) session.getAttribute("username");
		log.info("username"+username);
		String currentPage = req.getParameter("currentPage");
		currentPage=currentPage ==null ? "1" : currentPage;//注意这里的分页后台实现
		Fenye list=cardservice.list(username, Integer.parseInt(currentPage));
		req.setAttribute("fenye", list);
		req.setAttribute("currentPage", Integer.parseInt(currentPage));
		req.setAttribute("username", username);
		return "usercenter";
	}
	
	@RequestMapping("/toInformation")
	public String toInformation	(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		req.setAttribute("number", number);
		return "information";
	}
	
	@RequestMapping("/information")
	@ResponseBody
	public ajaxDAO information(HttpServletRequest req,String number,String password)throws ServletException, IOException {
		Card card=service.Get(Integer.parseInt(number),password);
		if(card==null) {
			return ajaxDAO.failure("失败");
		}
		else {
			return ajaxDAO.success(card);
		}
		/*return "success3";*/
	}

	/*
	 * 注意这里的方法参数,其中的number和password必须和前端的name一样,
	 * 必须和前端的要取的数据名相同,但是因为都是从前端获取的所以必须都定义为String类型
	 * 这里可以结合information。jsp页面看就懂了,这也是Spring-MVC的用法特色,获取前端的数据
	 * 不需要用request获取,可以直接获取但是参数必须和前端的name相同
	 */
	
	@RequestMapping("/flow")
	@ResponseBody
	public ajaxDAO flow(HttpSession session) throws ServletException, IOException {
		User user=(User)session.getAttribute("user");
		String username=user.getUsername();
		if(null!=username) {
			ArrayList<Card>list=cardservice.flow(username);
			return ajaxDAO.success(list);
		}
		else {
			return ajaxDAO.failure("操作有误");
		}
		
	}
}
