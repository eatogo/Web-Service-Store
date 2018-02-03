package _02_recommendation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class FindFoodDao {
	DataSource dataSource;
	private List<Food> foodPortfolio = new ArrayList<>();
	private List<Store> storePortfolio = new ArrayList<>();
	
	public FindFoodDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private String findFoodByTypeSql = "SELECT * FROM `FOODS` WHERE food_type = ?;";
    /*
     * 依照餐點類型尋找餐點
     * 回傳值為key是餐點編號、value是該Food物件的Map
     */
	public List<Food> findFoodByType(String food_type) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findFoodByTypeSql);)
		{
			ps.setString(1, food_type);
			extractInfoFromQueryResult(ps.executeQuery(), new Food());
			return foodPortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private String findStoreByStoreSql = "SELECT * FROM `STORES` WHERE (store_latitude BETWEEN ? AND ?) AND (store_longitude BETWEEN ? AND ?);";
	/*
	 * 搜尋在給定經緯度矩形內的店家
	 * 回傳值為key是店家編號、value是該Store物件的Map
	 */
	public List<Store> findStoreByLonLat(Double north_latitude, Double south_latitude, Double east_longitude,
			Double west_longitude) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findStoreByStoreSql);)
		{
			ps.setDouble(1, north_latitude);
			ps.setDouble(2, south_latitude);
			ps.setDouble(3, east_longitude);
			ps.setDouble(4, west_longitude);
			extractInfoFromQueryResult(ps.executeQuery(), new Store());
			return storePortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private String findFoodByStoreSql = "SELECT * FROM `FOODS` WHERE food_store = ?;";
	/*
	 * 依照店家編號搜尋餐點
	 * 回傳值為key是餐點編號、value是該Food物件的Map
	 */
	public List<Food> findFoodByStore(Integer store_id) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findFoodByStoreSql);)
		{
			ps.setInt(1, store_id);
			extractInfoFromQueryResult(ps.executeQuery(), new Food());
			return foodPortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}
	
	private void extractInfoFromQueryResult(ResultSet result, Object object) {
		if (object instanceof Food) {
			try {
				while (result.next()) {
					Integer food_id = result.getInt(1);
					String food_name = result.getString(2);
					Integer food_price = result.getInt(3);
					String food_intro = result.getString(4);
					String food_pic_hdpi = result.getString(5);
					String food_pic_ldpi = result.getString(6);
					String food_pic_mdpi = result.getString(7);
					String food_pic = result.getString(8);
					Integer food_limit = result.getInt(9);
					String food_type = result.getString(10);
					Integer food_store = result.getInt(11);
					String food_status = result.getString(12);
					Long food_review_count = result.getLong(13);
					Food dish = new Food(food_id, food_name, food_price, food_intro, food_pic_hdpi, food_pic_ldpi,
							food_pic_mdpi, food_pic, food_limit, food_type, food_store, food_status, food_review_count);
					foodPortfolio.add(dish);
				} 
			} catch (SQLException e) {
				System.out.println("SQL查詢指令有問題");
				e.printStackTrace();
			}
		} else if (object instanceof Store) {
			try {
				while (result.next()) {
					Integer store_id = result.getInt(1);
					String store_name = result.getString(2);
					String store_address = result.getString(3);
					String store_phone = result.getString(4);
					String store_email = result.getString(5);
					String store_logo = result.getString(6);
					String store_open_hour = result.getString(7);
					String store_intro = result.getString(8);
					Double store_latitude = result.getDouble(9);
					Double store_longitude = result.getDouble(10);
					String store_city = result.getString(11);
					String store_region = result.getString(12);
					String store_operate_type = result.getString(13);
					String store_status = result.getString(14);
					Store store = new Store(store_id, store_name, store_address, store_phone, store_email, store_logo,
							store_open_hour, store_intro, store_latitude, store_longitude, store_city, store_region,
							store_operate_type, store_status);
					storePortfolio.add(store);
				}
			} catch (SQLException e) {
				System.out.println("SQL查詢指令有問題");
				e.printStackTrace();
			}
		}
	}
}