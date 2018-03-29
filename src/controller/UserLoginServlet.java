package controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import Utils.KeyGenerator;
import Utils.TokenUserData;
import service.UserAccountService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserAccountService userAccountService = new UserAccountService();
	/**
	 * 进入登录页
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得原始请求的URL并保存传递，当登陆成功，让浏览器再跳转到这个URL
		String origUrl = request.getParameter("origUrl");
		request.setAttribute("origUrl", origUrl);
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("登录");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String origUrl = request.getParameter("origUrl");
		System.out.println("密码 "+password);
		System.out.println("原始地址"+origUrl);
		User user = null;
		try {
			user = userAccountService.findUserByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("查到用户"+user);
		if(user != null)
		{
			if(user.getPassword().equals(password))
			{
				//生成token
				String token = KeyGenerator.generator();
				//将token user存到全局唯一数据结构中
				TokenUserData.addToken(token,user);
				//写cookie
				Cookie tokenCookie = new Cookie("token",token);
				tokenCookie.setPath("/");
				//tokenCookie.setDomain(DOMAIN);
				tokenCookie.setHttpOnly(true);
				response.addCookie(tokenCookie);
				//跳转到原请求
				if(origUrl==null && origUrl.equals(""))
					origUrl = "login_success";
				else
					origUrl = URLDecoder.decode(origUrl,"utf-8");
				response.sendRedirect(origUrl);
				
			}else{
				backToLoginPage(request, response, account, origUrl, "密码不正确");
			}
		}else{
			backToLoginPage(request, response, account, origUrl, "用户不存在");
		}
	}
	
	private void backToLoginPage(HttpServletRequest request, HttpServletResponse response,
			String account,String origUrl,String errInfo) throws ServletException, IOException
	{
		request.setAttribute("account", account);
		request.setAttribute("origUrl", origUrl);
		request.setAttribute("errInfo", errInfo);
		request.getRequestDispatcher("WEB-INF/view/login.jsp").forward(request, response);
	}

}
