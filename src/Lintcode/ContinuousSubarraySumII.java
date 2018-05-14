package Lintcode;
import java.util.*;
/*
 * https://zhengyang2015.gitbooks.io/lintcode/continuous_subarray_sum_ii_403.html
 */

public class ContinuousSubarraySumII {
	public static void main(String[] args) {
		int[] nums = {3, 1, -100, -3, 4};
		ArrayList<Integer> res = solution1(nums);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
	
	public static ArrayList<Integer> solution1(int[] nums) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (nums.length <= 0) {
			return res;
		}
		res.add(0);
		res.add(0);
		int sum = 0;
		int max = Integer.MIN_VALUE;
		int start = 0;
		int end = 0;
		for (int i = 0; i < nums.length; i++) {
			if (sum < 0) {
				sum = nums[i];
				start = i;
				end = i;
			} else {
				sum += nums[i];
				if (nums[i] > 0) {
					end = i;
				}
			}
			if (sum > max) {
				max = sum;
				res.set(0, start);
				res.set(1, end);
			}
		}
		sum = 0;
		int min = Integer.MAX_VALUE;
		start = 0;
		end = -1;
		for (int i = 0; i < nums.length; i++) {
			if (sum > 0) {
				sum = nums[i];
				start = i;
				end = i;
			} else {
				sum += nums[i];
				if (nums[i] < 0) {
					end =i;
				}
			}
			if (sum < min) {
				min = sum;
				if (start == 0 && end == nums.length - 1) {
					continue;
				}
				res.set(0, (end + 1) % nums.length);
				res.set(1, (start - 1 + nums.length) % nums.length);
			}
		}
		return res;
	}
	
	
	// O(n^2)
	public static ArrayList<Integer> solution2(int[] nums) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (nums.length <= 0) {
			return res;
		}
		res.add(0);
		res.add(0);
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length; i++) {
			int sum = 0;
			int start = 0;
			int end = 0;
			for (int j = i; j < i + nums.length; j++) {
				int index = j % nums.length;
				if (sum > 0) {
					sum += nums[index];
					if (nums[index] > 0) {
						end = index;
					}
				} else {
					sum = nums[index];
					start = index;
					end = index;
				}
				if (sum > max) {
					max = sum;
					res.set(0, start);
					res.set(1, end);
				}
			}
		}
		return res;
	}
}
