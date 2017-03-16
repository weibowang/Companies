package Facebook;

import java.util.Arrays;
/*
 * We're given a sorted array of integers: [-3, -1, 0, 1, 2]. 
 * We want to generate a sorted array of their squares: [0, 1, 1, 4, 9]
 */

public class SortArrayInSquare {
	public static void main(String[] args) {
		int[] nums = {-3, -1, 0, 1, 2};
		int[] array = SortArrayInSquare(nums);
		System.out.println(Arrays.toString(array));
	}
	public static int[] SortArrayInSquare(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new int[0];
		}
		int[] array = new int[nums.length];
		int left = 0;
		int right = nums.length - 1;
		for (int i = array.length - 1; i >= 0; i--) {
			int a = nums[left] * nums[left];
			int b = nums[right] * nums[right];
			if (a > b) {
				array[i] = a;
				left++;
			} else {
				array[i] = b;
				right--;
			}
		}
		return array;
	}
}
