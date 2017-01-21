package Basic;

import java.util.*;
import java.util.List;

public class Test {
	public static class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		    this.left = this.right = null;
		}
	}

	public static void main(String[] args) {
		int[][] A = new int[5][3];
		A[0][0] = 1;
		A[0][1] = 3;
		A[0][2] = 2;
		A[1][0] = 4;
		A[1][1] = 6;
		A[1][2] = 5;
		A[2][0] = 7;
		A[2][1] = 9;
		A[2][2] = 8;
		A[3][0] = 13;
		A[3][1] = 15;
		A[3][2] = 14;
		A[4][0] = 10;
		A[4][1] = 12;
		A[4][2] = 11;
		List<Integer> res = solution2(A);
		System.out.println(res.get(0));
		System.out.println(res.get(1));
	}
	
	
	public static List<Integer> solution2(int[][] A) {
        List<Integer> res = new ArrayList<Integer>();
        if (A == null || A.length == 0 || A[0].length == 0) {
            return res;
        }
        int xStart = 0;
        int yStart = 0;
        int xEnd = A.length - 1;
        int yEnd = A[0].length - 1;
        while (xStart + 1 < xEnd && yStart + 1 < yEnd) {
            System.out.println("bb " + xStart + " " + xEnd);
            int xMid = (xStart + xEnd) / 2;
            int yMid = (yStart + yEnd) / 2;
            //System.out.println(xStart + " " + xMid + " " +xEnd);
            if (check(A, xMid, yMid)) {
                res.add(xMid);
                res.add(yMid);
                return res;
            } else if (A[xMid + 1][yMid] > A[xMid][yMid]) {
                xStart = xMid + 1;
                System.out.println(xStart);
                continue;
            } else if (A[xMid - 1][yMid] > A[xMid][yMid]) {
                xEnd = xMid;
                continue;
            } else if (A[xMid][yMid + 1] > A[xMid][yMid]) {
                yStart = yMid + 1;
                continue;
            } else if (A[xMid][yMid - 1] > A[xMid][yMid]) {
                yEnd = yMid;
                continue;
            }
            System.out.println("aa " + xStart);
        }
        //System.out.println("?? ");
        if (check(A, xStart, xEnd)) {
            res.add(xStart);
            res.add(xEnd);
            return res;
        }
        res.add(yStart);
        res.add(yEnd);
        return res;
    }
    
    private static boolean check(int[][] A, int i, int j) {
        if (i <= 0 || i >= A.length - 1 || j <= 0 || j >= A[0].length - 1) {
            return false;
        }
        if (A[i][j] > A[i+1][j] && A[i][j] > A[i-1][j] && A[i][j] > A[i][j+1] && A[i][j] > A[i][j-1]) {
            return true;
        }
        return false;
    }
       
}
