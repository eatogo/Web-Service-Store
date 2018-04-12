package _02_recommendation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import _00_global.JsonUtilImpl;
import _00_global.model.Food;
import _00_global.model.FoodWithLatLng;
import _02_recommendation.model.FoodDao;

/**
 * 這支Servlet負責接收亂數查詢餐點的請求
 * 
 * 請求必須以JSON格式寫進來回應也一律以JSON格式回傳
 * 
 * 搜尋功能可以依定位經緯度縮小搜尋範圍
 * 若要使用定位功能，請附上經緯度參數：{"store_latitude"="xx.xxxxx","store_longitude"="xxx.xxxxx"}
 */
@WebServlet("/RequestRandomFood.do")
public class RequestRandomFoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CONTENT_TYPE = "text/html; charset=utf-8";
	Map<String, String> errorMessage = new HashMap<String, String>();
	List<Food> foods;
	JsonUtilImpl jsonUtil;
	StringBuffer jsonInString, jsonOutString;
	FoodWithLatLng foodWithLatLng;
	Double latitude, longitude;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		FoodDao dao = (FoodDao) ctx.getBean("findFoodJDBCDS");
		foods = (List<Food>) ctx.getBean("foods");
		foodWithLatLng = (FoodWithLatLng) ctx.getBean("foodWithLatLng");
		jsonUtil = (JsonUtilImpl) ctx.getBean("jsonUtil");
		request.setCharacterEncoding("UTF8");
		
		// 開始讀取clien端傳來json字串動作
		BufferedReader br = request.getReader();
		jsonInString = new StringBuffer();
		String line = null;
		// 讀取client端的json字串
		while ((line = br.readLine()) != null) {
			jsonInString.append(line);
		}
		br.close();
		
		try { // 有定位經緯度，亂數取出五公里內三道餐點
			// 將json字串轉為FoodWithLatLng物件
			foodWithLatLng = jsonUtil.convertToFoodWithLatLngFrom(jsonInString.toString());
			latitude = foodWithLatLng.getStore_latitude();
			longitude = foodWithLatLng.getStore_longitude();
			// 依定位縮小搜尋範圍，再亂數選出三道餐點
			foods = dao.findRandomFoodByLatLng(latitude, longitude);
			sendJsonWithWriter(response);
		} catch (NullPointerException e) { // 無定位經緯度，從全部餐點亂數取出三道
			System.out.println(e.getMessage());
			foods = dao.findRandomFood();
			sendJsonWithWriter(response);
		}
	}
	
	private void sendJsonWithWriter(HttpServletResponse response) throws IOException {
		response.setContentType(CONTENT_TYPE);
		if (!foods.isEmpty()) {
			jsonOutString = new StringBuffer();
			jsonOutString.append(jsonUtil.convertToJsonFrom(foods));
			PrintWriter out = response.getWriter();
			out.println(jsonOutString);
			out.close();
			return;
		} else {
			PrintWriter out = response.getWriter();
			out.println("No-data nearby");
			out.close();
			return;
		}
	}

}
