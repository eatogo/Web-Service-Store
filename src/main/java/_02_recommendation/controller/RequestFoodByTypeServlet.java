package _02_recommendation.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_utility.JsonGsonUtil;
import _02_recommendation.model.FindFoodDao;
import _02_recommendation.model.Food;

@WebServlet("/RequestFoodByType.do")
public class RequestFoodByTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMessage = null;
		JsonGsonUtil jsonUtil = new JsonGsonUtil();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		FindFoodDao dao = (FindFoodDao) ctx.getBean("findFoodJDBCDS");
		request.setCharacterEncoding("UTF8");
		request.setAttribute("errorMessage", errorMessage);
		
		// 先從傳來的訊息中取出food_type
		String food_type = request.getParameter("food_type");
		
//		String latitude = request.getParameter("latitude");
//		String longitude = request.getParameter("longitude");
//		if (latitude != null || longitude != null || latitude.trim() != "" || longitude.trim() != "") {
//			// 做範圍搜尋動作
//		}
		
		List<Food> foods = dao.findFoodByType(food_type);

		if (!foods.isEmpty()) {
			request.setAttribute("result", jsonUtil.listTojson(foods));
			RequestDispatcher rd = request.getRequestDispatcher("/findFoodResultPage.jsp");
			rd.forward(request, response);
			return;
		} else {
			errorMessage.put("zeroFoodResult", "搜尋" + food_type + "餐點無結果");
			RequestDispatcher rd = request.getRequestDispatcher("/findFoodResultPage.jsp");
			rd.forward(request, response);
			return;
		}
	}

}
