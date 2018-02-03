package _00_utility;

public class RandomUtil {
	/*
	 * 建構子傳入要亂數排序的陣列Integer[] integerArray
	 * randomSortedArray陣列則用來保存亂數排序後的結果
	 */
	private Integer[] integerArray = null;
	private Integer[] randomSortedArray = null;
	
	public RandomUtil(Integer[] integerArray) {
		this.integerArray = integerArray;
	}
	
	public Integer[] getThreeRandomPicks() {
		randomSortedArray = randomizeArray(integerArray);
		return new Integer[]{randomSortedArray[0], randomSortedArray[1], randomSortedArray[2]};
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
}
