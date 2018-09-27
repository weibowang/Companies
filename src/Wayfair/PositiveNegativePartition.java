package Wayfair;

import java.util.Arrays;

public class PositiveNegativePartition {
	public static void main(String[] args) {
		int[] nums = {1, -2, 3, -4, 5, -6, 7, 0, 0};
		int[] array1 = solution1(nums);
		int[] array2 = solution2(nums);
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
	}
	
	//O(2n) time, O(n) space
	public static int[] solution1(int[] nums) {
		int[] array = new int[nums.length];
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0) {
				array[index] = nums[i];
				index++;
			}
		}
		index = nums.length - 1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] > 0) {
				array[index] = nums[i];
				index--;
			}
		}
		return array;
	}
	
	//O(n ^ 2) time  O(1) space
	public static int[] solution2(int[] nums) {
		int negative = 0;
		for (int num : nums) {
			if (num < 0) {
				negative++;
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (negative <= 0) {
				break;
			}
			if (nums[i] >= 0) {
				int last = nums[i];
				int tmp = nums[i];
				int j = i + 1;
				while (j < nums.length && nums[j] > 0) {
					tmp = nums[j];
					nums[j] = last;
					last = tmp;
					j++;
				}
				last = nums[j];
				nums[j] = tmp;
				nums[i] = last;
				negative--;
			}
		}
		return nums;
	}

}
