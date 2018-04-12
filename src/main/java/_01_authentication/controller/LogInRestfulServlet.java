package _01_authentication.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_global.JsonUtilImpl;
import _00_global.model.User;
import _01_authentication.model.AuthenticateDao;

@WebServlet("/LogInRestful.do")
public class LogInRestfulServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		AuthenticateDao authenticateDao = (AuthenticateDao) ctx.getBean("autenticateJDBCDS");
		JsonUtilImpl jsonUtil = (JsonUtilImpl) ctx.getBean("jsonUtil");
		StringBuffer jsonOutString = new StringBuffer();
		
		request.setCharacterEncoding("UTF8");
		String cellphone = request.getParameter("cellphone");
		String password = request.getParameter("password");
		User user = authenticateDao.authenticate(cellphone, password);
		jsonOutString.append(jsonUtil.convertToJsonFrom(user));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
