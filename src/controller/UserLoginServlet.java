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
	 * �����¼ҳ
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ԭʼ�����URL�����洫�ݣ�����½�ɹ��������������ת�����URL
		String origUrl = request.getParameter("origUrl");
		request.setAttribute("origUrl", origUrl);
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("��¼");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String origUrl = request.getParameter("origUrl");
		System.out.println("���� "+password);
		System.out.println("ԭʼ��ַ"+origUrl);
		User user = null;
		try {
			user = userAccountService.findUserByAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("�鵽�û�"+user);
		if(user != null)
		{
			if(user.getPassword().equals(password))
			{
				//����token
				String token = KeyGenerator.generator();
				//��token user�浽ȫ��Ψһ���ݽṹ��
				TokenUserData.addToken(token,user);
				//дcookie
				Cookie tokenCookie = new Cookie("token",token);
				tokenCookie.setPath("/");
				//tokenCookie.setDomain(DOMAIN);
				tokenCookie.setHttpOnly(true);
				response.addCookie(tokenCookie);
				//��ת��ԭ����
				if(origUrl==null && origUrl.equals(""))
					origUrl = "login_success";
				else
					origUrl = URLDecoder.decode(origUrl,"utf-8");
				response.sendRedirect(origUrl);
				
			}else{
				backToLoginPage(request, response, account, origUrl, "���벻��ȷ");
			}
		}else{
			backToLoginPage(request, response, account, origUrl, "�û�������");
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
