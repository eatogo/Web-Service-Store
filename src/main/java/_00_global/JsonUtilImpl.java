package _00_global;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import _00_global.model.Food;
import _00_global.model.FoodWithLatLng;
import _00_global.model.Store;
import _00_global.model.User;
import _00_global.utility.JsonUtil;

/*
 * 此類別負責將物件轉換為JSON
 * 或將JSON轉換為物件
 */
public class JsonUtilImpl implements JsonUtil {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Override
	public User convertToUserFrom(String jsonString) {
		return gson.fromJson(jsonString, User.class);
	}
	
	@Override
	public String convertToJsonFrom(User user) {
		return gson.toJson(user);
	}
	
	@Override
	public FoodWithLatLng convertToFoodWithLatLngFrom(String jsonString) {
		return gson.fromJson(jsonString, FoodWithLatLng.class);
	}
	
	@Override
	public String convertToJsonFrom(FoodWithLatLng foodWithLatLng) {
		return gson.toJson(foodWithLatLng);
	}
	
	@Override
	public List<Food> convertToListOfFoodFrom(String jsonString) {
		Type collectionType = new TypeToken<List<Food>>() {}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	@Override
	public List<Store> convertToListOfStoreFrom(String jsonString) {
		Type collectionType = new TypeToken<List<Store>>() {}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	@Override
	public String convertToJsonFrom(List<?> list) {
		return gson.toJson(list);
	}
	
}
