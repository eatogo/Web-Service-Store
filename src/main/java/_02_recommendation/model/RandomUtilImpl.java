package _02_recommendation.model;

import java.util.Collections;
import java.util.List;

import _00_global.model.Food;
import _00_global.utility.RandomUtil;

public class RandomUtilImpl implements RandomUtil {
	
	public RandomUtilImpl() {
	}

	/**
	 * Array亂數功能
	 */
	@Override
	public Integer[] getThreeRandomPicksInArray(Integer[] integerArray) {
		Integer[] randomSortedArray = randomizeArray(integerArray);
		return new Integer[] { randomSortedArray[0], randomSortedArray[1], randomSortedArray[2] };
	}

	@Override
	public Integer[] randomizeArray(Integer[] array) {
		for (int index = 0; index < 3; index++) {
			int randomIndex = (int) (Math.random() * array.length);
			int temp = array[randomIndex];
			array[randomIndex] = array[index];
			array[index] = temp;
		}
		return array;
	}
	
	private List<Food> threePicksList;

	public List<Food> getThreePicksList() {
		return threePicksList;
	}

	public void setThreePicksList(List<Food> threePicksList) {
		this.threePicksList = threePicksList;
	}
	
	/**
	 * ArrayList亂數功能
	 */
	@Override
	public List<Food> getThreeRandomPicksInList(List<Food> list) {
		Collections.shuffle(list);
		// 存取亂數排序List後的前三選項
		threePicksList.add(list.get(0));
		threePicksList.add(list.get(1));
		threePicksList.add(list.get(2));
		return threePicksList;
	}
}
