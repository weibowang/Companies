package Facebook;

import java.util.*;
/*
 * http://www.geeksforgeeks.org/length-of-the-longest-arithmatic-progression-in-a-sorted-array/
 */

public class LongestArithmeticProgression {
	public static void main(String[] args) {
		int[] nums1 = {1, 7, 10, 13, 14, 19};
		int[] nums2 = {2, 4, 6, 8, 10};
		int[] nums3 = {2, 3, 5, 7, 8, 11};
		int[] nums4 = {1, 3, 4, 5, 7, 8, 9};
		int[] nums5 = {0, 2, 4, 6, 10, 14, 18, 22};
		System.out.println(LongestArithmeticProgression(nums5));
		//System.out.println(Arrays.toString(nums1));
		System.out.println(LongestArithmeticProgression2(nums5));
		System.out.println(longestArithmeticProgression3(nums5));
	}
	public static int LongestArithmeticProgression(int[] nums) {
		if (nums.length <= 2) {
			return nums.length;
		}
		int[][] dp = new int[nums.length][nums.length];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j] = 2;
			}
		}
		int max = 2;
		for (int j = dp.length - 2; j > 0; j--) {
			int i = j - 1;
			int k = j + 1;
			while (i >= 0 && k < dp.length) {
				if (nums[j] * 2 == nums[i] + nums[k]) {
					dp[i][j] = dp[j][k] + 1;
					max = Math.max(max, dp[i][j]);
					i--;
					k++;
				} else if (nums[j] * 2 > nums[i] + nums[k]) {
					k++;
				} else if (nums[j] * 2 < nums[i] + nums[k]) {
					i--;
				}
			}
		}
		return max;
	}
	
	public static int LongestArithmeticProgression2(int[] nums) {
		if (nums.length <= 2) {
			return nums.length;
		}
		int[][] dp = new int[nums.length][nums.length];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp.length; j++) {
				dp[i][j] = 2;
			}
		}
		int max = 2;
		for (int j = 1; j < dp.length - 1; j++) {
			int i = j - 1;
			int k = j + 1;
			while (i >= 0 && k < dp.length) {
				//System.out.println("?? " + dp[i][j] + " " + j);
				if (nums[j] * 2 > nums[i] + nums[k]) {
					k++;
				} else if (nums[j] * 2 < nums[i] + nums[k]) {
					i--;
				} else {
					dp[j][k] = dp[i][j] + 1;
					//System.out.println(i + " " + j + " " + dp[i][j]);
					max = Math.max(max, dp[j][k]);
					i--;
					k++;
				}
			}
		}
		return max;
	}
	
	
	static int longestArithmeticProgression3(int a[]){
	    int i,j,k;
	    int n = a.length;
	    int[][] Table = new int[n][n];
	    int longestAP = 2;
	  
	    for(i=0;i<n; i++) {
	    	Table[i][n-1] =2;
	    }
	  
	    for(j= n-2; j>=1; j-- ){
	        i = j-1;
	        k = j+1;
	  
	        while(i>=0 && k<n){
	        	//System.out.println("?? " + Table[i][j] + " " + j);
	            if(2* a[j] > a[i] + a[k]){
	                k++; // Table[j][k]is already filled 
	            }
	            else if (2* a[j] < a[i] + a[k]){
	             /*Table[i][j] needs to be filled before we move up */
	                Table[i][j] =2; 
	                i--;
	            }
	            else{
	                Table[i][j] = Table[j][k] +1;
	                //System.out.println("** " + i + " " + j + " " + Table[i][j]);
	                longestAP = Math.max(longestAP, Table[i][j]);
	                i--;
	                k++;
	            }
	        }
	        while(i>=0){
	            Table[i][j] =2; 
	            i--;
	        }
	    }
	    return longestAP;
	}
}
