package Google;

import java.util.*;

/*
 * 第一题, 给一个array比如[4,2,1,3,5],根据这个array现在我们能有了一个新的array => 每个数是在原array里, 在它左边的所有比它大的number的个数,就是[0,1,2,1,0].
 *  题目是现在给了这个[0,1,2,1,0]要求原array, 原来array的range是1~n
 */
public class RecoverArray {
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 1, 0};
		array = recoverArray(array, 5);
		System.out.println(Arrays.toString(array));
	}
	public static int[] recoverArray(int[] array, int n) {
		if (array == null || array.length == 0) {
			return null;
		}
		int[] nums = new int[n];
		for (int i = array.length - 1; i >= 0; i--) {
			int count = array[i] + 1;
			int full = nums.length;
			
			while (count > 0) {
				full--;
				if (nums[full] == 0) {
					count--;
				}
			}
			nums[full] = 1;
			array[i] = full + 1;
		}
		return array;
	}
}
