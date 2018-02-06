package _00_utility;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import _00_utility.model.Food;
import _00_utility.model.FoodWithLatLng;
import _00_utility.model.Store;
import _00_utility.model.User;

/*
 * 此類別負責將物件轉換為JSON
 * 或將JSON轉換為物件
 */
public class JsonUtil {
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public User convertToUserFrom(String jsonString) {
		return gson.fromJson(jsonString, User.class);
	}
	
	public FoodWithLatLng convertToFoodWithLatLngFrom(String jsonString) {
		return gson.fromJson(jsonString, FoodWithLatLng.class);
	}
	
	public List<Food> convertToListOfFoodFrom(String jsonString) {
		Type collectionType = new TypeToken<List<Food>>() {}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	public List<Store> convertToListOfStoreFrom(String jsonString) {
		Type collectionType = new TypeToken<List<Store>>() {}.getType();
		return gson.fromJson(jsonString, collectionType);
	}
	
	public String listTojson(List<?> list) {
		return gson.toJson(list);
	}

	public String userToJson(User user) {
		return gson.toJson(user);
	}
	
}
