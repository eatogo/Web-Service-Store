package _00_utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import _00_utility.model.Food;

public class RandomUtil {

	public RandomUtil() {
	}

	// 待亂數Integer[]陣列
	private Integer[] integerArray = null;
	// randomSortedArray陣列則用來保存亂數排序後的結果
	private Integer[] randomSortedArray = null;

	// 此建構子傳入要亂數排序的陣列Integer[] integerArray
	public RandomUtil(Integer[] integerArray) {
		this.integerArray = integerArray;
	}

	public Integer[] getThreeRandomPicksInArray() {
		randomSortedArray = randomizeArray(integerArray);
		return new Integer[] { randomSortedArray[0], randomSortedArray[1], randomSortedArray[2] };
	}

	private Integer[] randomizeArray(Integer[] array) {
		for (int index = 0; index < 3; index++) {
			int randomIndex = (int) (Math.random() * array.length);
			int temp = array[randomIndex];
			array[randomIndex] = array[index];
			array[index] = temp;
		}
		return array;
	}

	// 存取亂數排序List後的前三選項
	private List<Food> threePicksList = new ArrayList<>();

	public List<Food> getThreeRandomPicksInList(List<Food> list) {
		Collections.shuffle(list);
		threePicksList.add(list.get(0));
		threePicksList.add(list.get(1));
		threePicksList.add(list.get(2));
		return threePicksList;
	}
}
