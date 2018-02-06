package _02_recommendation.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import _00_utility.LocationUtil;
import _00_utility.RandomUtil;
import _00_utility.model.Food;
import _00_utility.model.FoodWithLatLng;
import _00_utility.model.Place;
import _00_utility.model.Store;

public class FindFoodDao {
	DataSource dataSource;
	LocationUtil locationUtil = new LocationUtil();
	RandomUtil randomUtil = new RandomUtil();
	Place firstSpot;
	Place secondSpot;
	private List<Food> foodPortfolio = new ArrayList<>();
	private List<FoodWithLatLng> foodWithLatLngPortfolio = new ArrayList<>();
	private List<Store> storePortfolio = new ArrayList<>();
	private final int OBJECT_TYPE_FOOD = 1;
	private final int OBJECT_TYPE_FOOD_WITH_LATLNG = 2;
	private final int OBJECT_TYPE_STORE = 3;

	public FindFoodDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private String findFoodByTypeSql = "SELECT * FROM `FOODS` WHERE food_type = ?;";

	/**
	 * 依照餐點類型尋找餐點 回傳值為key是餐點編號、value是該Food物件的ArrayList
	 */
	public List<Food> findFoodBy(String food_type) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(findFoodByTypeSql);) {
			ps.setString(1, food_type);
			extractInfoFromQueryResult(ps.executeQuery(), OBJECT_TYPE_FOOD);
			return foodPortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private String findStoreByIdSql = "SELECT * FROM `STORES` WHERE store_id = ?;";

	/**
	 * 依照餐點類型尋找餐點，並篩選5公里以內的店家 回傳值為key是餐點編號、value是該Food物件的ArrayList
	 */
	public List<Food> findWithinDistanceFoodBy(String food_type, Double latitude, Double longitude) {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement psFood = conn.prepareStatement(findFoodByTypeSql);
				PreparedStatement psStore = conn.prepareStatement(findStoreByIdSql);) {
			firstSpot = new Place(latitude, longitude);
			psFood.setString(1, food_type);
			extractInfoFromQueryResult(psFood.executeQuery(), OBJECT_TYPE_FOOD);
			Iterator<Food> foodIt = foodPortfolio.iterator();
			while (foodIt.hasNext()) {
				Food dish = foodIt.next();
				psStore.setInt(1, dish.getFood_store());
				ResultSet result = psStore.executeQuery();
				result.next();
				secondSpot = new Place(result.getDouble(9), result.getDouble(10));
				double distance = locationUtil.distance(firstSpot, secondSpot);
				System.out.println(distance);
				if (distance > 5.0) {
					foodIt.remove();
				}
			}
			if (!foodPortfolio.isEmpty()) {
				return foodPortfolio;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
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
			return foodPortfolio;
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	private final String findFoodFromViewSql = "SELECT * FROM `VIEW_FOOD_LATLNG`;";

	/**
	 * 亂數選出三項餐點；回傳值為key是餐點編號、value是該Food物件的ArrayList
	 */
	public List<Food> findRandomFood() {
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
			extractInfoFromQueryResult(stmt.executeQuery(findFoodFromViewSql), OBJECT_TYPE_FOOD);
			if (!foodPortfolio.isEmpty()) {
				foodPortfolio = randomUtil.getThreeRandomPicksInList(foodPortfolio);
				return foodPortfolio;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 亂數選出三項餐點，並篩選5公里以內的店家 回傳值為key是餐點編號、value是該Food物件的ArrayList
	 */
	public List<Food> findRandomFoodByLatLng(Double latitude, Double longitude) {
		try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement();) {
			firstSpot = new Place(latitude, longitude);
			extractInfoFromQueryResult(stmt.executeQuery(findFoodFromViewSql), OBJECT_TYPE_FOOD_WITH_LATLNG);
			Iterator<FoodWithLatLng> foodIt = foodWithLatLngPortfolio.iterator();
			while (foodIt.hasNext()) {
				FoodWithLatLng dish = foodIt.next();
				secondSpot = new Place(dish.getStore_latitude(), dish.getStore_longitude());
				double distance = locationUtil.distance(firstSpot, secondSpot);
				if (distance > 5.0) {
					foodIt.remove();
				}
			}
			if (!foodWithLatLngPortfolio.isEmpty()) {
				convertFoodWithLatLngToFood(foodWithLatLngPortfolio);
				foodPortfolio = randomUtil.getThreeRandomPicksInList(foodPortfolio);
				return foodPortfolio;
			} else {
				return null;
			}
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 將List<FoodWithLatLng>物件轉換為List<Food>物件
	 */
	private void convertFoodWithLatLngToFood(List<FoodWithLatLng> foodWithLatLngPortfolio) {
		Iterator<FoodWithLatLng> foodIt = foodWithLatLngPortfolio.iterator();
		while (foodIt.hasNext()) {
			// 利用Food類別自訂的建構子，將FoodWithLatLng物件轉換為Food物件
			foodPortfolio.add(new Food(foodIt.next()));
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
					foodPortfolio.add(dish);
				}
				break;
			case OBJECT_TYPE_FOOD_WITH_LATLNG:
				while (result.next()) {
					Integer food_id = result.getInt(1);
					String food_name = result.getString(2);
					Integer food_price = result.getInt(3);
					String food_intro = result.getString(4);
					Integer food_limit = result.getInt(9);
					String food_type = result.getString(10);
					Integer food_store = result.getInt(11);
					String food_status = result.getString(12);
					Long food_review_count = result.getLong(13);
					Double store_latitude = result.getDouble(14);
					Double store_longitude = result.getDouble(15);
					FoodWithLatLng dish = new FoodWithLatLng(food_id, food_name, food_price, food_intro, food_limit,
							food_type, food_store, food_status, food_review_count, store_latitude, store_longitude);
					foodWithLatLngPortfolio.add(dish);
				}
				break;
			case OBJECT_TYPE_STORE:
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
				break;
			}
		} catch (SQLException e) {
			System.out.println("SQL查詢指令有問題");
			e.printStackTrace();
		}
	}
}