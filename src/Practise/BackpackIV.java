package Practise;

public class BackpackIV {
	public static void main(String[] args) {
		int[] nums = {2, 3, 5, 7};
		int target = 7;
		System.out.println(solution1(nums, target));
		System.out.println(solution2(nums, target));
		System.out.println(solution3(nums, target));
	}
	
	public static int solution1(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 0; i < nums.length; ++i) {
        	for (int  j = 0; j <= target; ++j) {
            	if (j >= nums[i]) {
            		f[j] += f[j - nums[i]];
            	}
            }
        }
        return f[target];
    }
	
	public static int solution2(int[] nums, int target) {
        int m = target;
        int []A = nums;
        int f[][] = new int[A.length + 1][m + 1];
        
        f[0][0] = 1;
        for (int j = 0; j <= m; j++) {
            for (int i = 1; i <= A.length; i++) {
                int k = 0; 
                while(k * A[i-1] <= j) {
                    f[i][j] += f[i-1][j-A[i-1]*k];
                    k+=1;
                }
            } // for j
        } // for i    
        return f[A.length][target];
    }
	
	
	//Wrong solution
	public static int solution3(int[] nums, int target) {
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int  j = 0; j <= target; ++j) {
        	for (int i = 0; i < nums.length; ++i) {
        		if (j >= nums[i]) {
            		f[j] += f[j - nums[i]];
            	}
            }
        }


        return f[target];
    }
}
