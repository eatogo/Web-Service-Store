package _00_global.utility;

import java.util.List;

import _00_global.model.Food;

public interface RandomUtil {

	public Integer[] getThreeRandomPicksInArray(Integer[] integerArray);
	
	Integer[] randomizeArray(Integer[] array);
	
	public List<Food> getThreeRandomPicksInList(List<Food> list);
}
