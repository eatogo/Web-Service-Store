package _01_authentication.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_global.JsonUtilImpl;
import _00_global.ValidateUtil;
import _00_global.model.User;
import _01_authentication.model.UserDao;

@WebServlet("/SignUpJson.do")
public class SignUpJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StringBuffer jsonInString, jsonOutString;
	String errorMessage;
	User userUnverified, user;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		UserDao userDao = (UserDao) ctx.getBean("userJDBCDS");
		JsonUtilImpl jsonUtil = (JsonUtilImpl) ctx.getBean("jsonUtil");
		ValidateUtil validateUtil = (ValidateUtil) ctx.getBean("validateUtil");
		
		request.setCharacterEncoding("UTF8");

		// 開始讀取clien端傳來json字串動作
		BufferedReader br = request.getReader();
		jsonInString = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			jsonInString.append(line);
		}
		br.close();
		
		userUnverified = jsonUtil.convertToUserFrom(jsonInString.toString());
		if(validateUtil.validate(userUnverified)) { // 輸入資料格式正確
			user = userDao.findUserByStringData("user_cellphone", userUnverified.getUser_cellphone());
			if (user.getUser_id() == null) { // 找不到註冊過的資料，才能註冊
				if (userDao.insertIntoUser(user)) {
					send("Succeeded", response);
				} else {
					send("Unseccessful", response);
				}
			} else { // 找到註冊過的資料
				send("Already registered", response);
			}
		} else { // 輸入資料格式不正確
			send("Data not valid", response);
		}
	}
	
	private void send(String stringOut, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.println(stringOut);
		out.close();
		return;
	}

}
