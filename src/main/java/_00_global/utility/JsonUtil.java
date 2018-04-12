package _00_global.utility;

import java.util.List;

import _00_global.model.Food;
import _00_global.model.FoodWithLatLng;
import _00_global.model.Store;
import _00_global.model.User;

public interface JsonUtil {

	public User convertToUserFrom(String jsonString);
	
	public String convertToJsonFrom(User user);
	
	public FoodWithLatLng convertToFoodWithLatLngFrom(String jsonString);
	
	public String convertToJsonFrom(FoodWithLatLng foodWithLatLng);
	
	public List<Food> convertToListOfFoodFrom(String jsonString);
	
	public List<Store> convertToListOfStoreFrom(String jsonString);
	
	public String convertToJsonFrom(List<?> list);
}
