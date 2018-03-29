package validate;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import Utils.TokenUserData;

/**
 * Servlet implementation class TokenValidateServlet
 */
@WebServlet("/validate")
public class TokenValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");
		User user = TokenUserData.validateToken(token);
		if(user == null){
			response.getWriter().write("");
		}
		else{
			response.getWriter().write(
			   "id="+user.getId()+";name="+user.getName()
			   +";account="+ user.getAccount());
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
