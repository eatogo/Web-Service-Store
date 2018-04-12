package _07_storeInterface.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import _00_global.model.Food;
import _00_global.model.User;

public class StoreDao {
	DataSource dataSource;
	private List<User> coworkers;
	private List<Food> menu;
	private final int OBJECT_TYPE_FOOD = 1;

	public StoreDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 * 依照店家編號及身份別搜尋使用者 回傳值為key是使用者編號、value是該User物件的ArrayList
	 */
	public List<User> findUserByStoreAndIdentity(Integer user_store, String user_identity) {

		return coworkers;
	}

	private String findFoodByStoreSql = "SELECT * FROM `FOODS` WHERE food_store = ?;";

	/**
	 * 依照店家編號搜尋餐點 回傳值為key是餐點編號、value是該Food物件的ArrayList
	 */
	public List<Food> findFoodByStore(Integer store_id) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findFoodByStoreSql);) {
			ps.setInt(1, store_id);
			extractInfoFromQueryResult(ps.executeQuery(), OBJECT_TYPE_FOOD);
			return menu;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private void extractInfoFromQueryResult(ResultSet result, int OBJECT_TYPE) {
		try {
			switch (OBJECT_TYPE) {
			case OBJECT_TYPE_FOOD:
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
					menu.add(dish);
				}
				break;
			}
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
		}
	}
}
