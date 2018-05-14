package Lintcode;
import java.util.*;

public class SubarraySumII {
	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4};
		int start = 1;
		int end = 3;
		int res = subarraySumII(nums, start, end);
		System.out.println(res);
		

	}
    
    public static int subarraySumII(int[] A, int start, int end) {
        // Write your code here
        
        int[] sum = new int[A.length+1];
        sum[0] = 0;
        for(int i = 0; i < A.length; i++){
            sum[i+1] += sum[i] + A[i];
        }
        
        int res = 0;
        
        for(int i = 0; i < A.length; i++){
            for(int j = i + 1; j <= A.length; j++){
                int diff = sum[j] - sum[i];
                if(start <= diff && end >= diff){
                    res++;
                }
            }
        }
        return res;
        
    }
}
