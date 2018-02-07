package com.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.control.cardControl;
import com.user.User;


public class check implements Filter {
	
	private  Logger log=LoggerFactory.getLogger(check.class);

	public check() {
		// TODO Auto-generated constructor stub
		log.info("-----Filter init------ ");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		log.info("-----Filter end------ ");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;//这里注意转型,需要转为HttpServlet
		HttpServletResponse response=(HttpServletResponse)resp;
		String uri=request.getRequestURI();//这里首先获取前端的URL地址
		log.info("<<<<<<<"+uri);
		Boolean flag=white(uri,request);//通过white()方法判断是不是在白名单之内
		if(flag) {
			chain.doFilter(req, resp);//如果是在白名单之内就直接通过交给后面,白名单里面保存的是注册和跳转登录页面的链接
		}
		else {
			HttpSession session=request.getSession();
			/*
			 * 如果不在链接不是登录页面跳转和注册的请求,那么这是就是已经试图进入其它页面如用户页面,这是就需要判断它是否已经登录过
			 * 如何判断已经登录,这时就需要一个全局变量,这里在之前后台每次登录后都会保存一个"user"全局变量,这里就派上用场,如果之前登录后Session
			 * 中一定保存了一个"user"全局变量,这里可以判断"user"是否为null间接判断是否已经登录
			 */
			User user=(User)session.getAttribute("user");
			log.info("-------"+user);
			if(null==user) {
				response.sendRedirect("/user/toLogin.do");//null说明没有登录需要跳转到登录页面,这里注意它的链接地址
				/*
				 * response.sendRedirect("/WEB-INF/pag/login.jsp") 
				 * 为什么前一种方法可以到达,但是后一种方法无法到达,这里可以从web.xml的文件执行顺序判断
				 * 这里web.xml文件先执行filter后执行servlet,这里可以这样理解filter类出来之后
				 * 进入servlet,因为Servlet的判断依据是以.do结尾的,所以在web.xml文件的Servlet中无法通过
				 * 所以无法到达登录页面,但是这里又出现了另一个问题,为什么后台的页面跳转,通过文件的路径就可以直接跳转
				 * 但是不使用.do的链接形式,这是因为从后台向前端进行页面跳转时不需要进入web.xml文件,所以它可以直接通过路径
				 * 进行页面跳转,但是如果后台进行页面跳转时通过.do的链接进行跳转同样不可以,其实可以这样理解前端的页面跳转请求
				 * 其实也是通过Servlet过滤交给后台完成实际意义的页面跳转,这里可以理解为在不不进入Servlet之前的链接
				 * 方式都是自定义的方式,最后都进入Servlet进行过滤,交给后台处理完成页面跳转,在后台进行实际的页面跳转时
				 * 则是用路径的方式完成,所以可以理解为在Servlet之前链接都是以自定义的方式,Servlet之后链接都是以路径的方式
				 * 所以这里就可以理解为什么在Filter类是以自定义的方式,这里不要忘了在web。xml的执行顺序Filter在Servlet
				 * 之前执行,Filter是完成前端和后台之间的数据过滤
				 */
			}
			else {
				chain.doFilter(req, resp);
			}
		}
	}
	
	private Boolean white(String uri,HttpServletRequest req) {
		Boolean flag=false;
		InputStream input=req.getServletContext().getResourceAsStream("/WEB-INF/classes/white.properties");
		/*
		 * 这里的getServletContext()方法是获取默认的根路径,获取输入流
		 */
		Properties pro=new Properties();
		try {
			pro.load(input);
			String msg=pro.getProperty("white_uri");//到这里其实就是获取properties信息,这里是固定的套路
			String[] message=msg.split(",");
			for(String i:message) {
				log.info(i);
				if(i.equals(uri)) {
					flag=true;
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		return flag;
	}
	
	private  void docheck(HttpServletRequest req) {
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	/*
	 * 拦截器Filter的作用是为了将多个页面串连起来,不会出现只要知道url链接就可以访问任何的页面,即使没有登录的情况下,
	 * 这里用拦截器只要没有登录就不可以进入用户的页面,还有就是服务器关闭后刷新它会自动跳转到登录页面,让用户再次登录,保护用户信息
	 */
	
}
