package com.fiberhome.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Description:loginout Servlet即删除session
 * @author sjZhang
 * @date 2018年2月7日下午4:16:04
 */
@WebServlet("/user/loginout")
public class LoginOutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if(session != null){
			Cookie c = new Cookie("JSESSIONID",session.getId());
			c.setMaxAge(0);
			c.setPath("/");
			resp.addCookie(c);
			session.invalidate();//将session置为无效状态，即删除session
			resp.sendRedirect("http://localhost:9090/user/act");
		}
		
	}

}
