package Google;

import java.util.*;


/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * https://www.youtube.com/watch?v=RORuwHiblPc
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=194262&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 * lc68 is not the same problem
 */

public class TextJustification {
	
	public static void main(String[] args) {
		String[] s = {"tushar", "roy", "likes", "to", "code"};
		List<String> res = fullJustify(s, 10);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
	}
	
	public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0) {
            return res;
        }
        int[][] dp = new int[words.length][words.length];
        for (int i = 0; i < dp.length; i++) {
            int sum = 0;
            for (int j = i; j < dp.length; j++) {
                if (j != i) {
                    sum += 1;
                    sum += words[j].length();
                    if (sum > maxWidth) {
                        dp[i][j] = Integer.MAX_VALUE;
                    } else {
                        dp[i][j] = (maxWidth - sum) * (maxWidth - sum);
                    }
                } else {
                    sum += words[j].length();
                    dp[i][j] = (maxWidth - sum) * (maxWidth - sum);
                }
            }
        }
        
        int[] minCount = new int[words.length];
        int[] path = new int[words.length];
        minCount[0] = dp[0][0];
        path[0] = 0;
        for (int i = 1; i < minCount.length; i++) {
            int min = Integer.MAX_VALUE;
            if (dp[0][i] != Integer.MAX_VALUE) {
                minCount[i] = dp[0][i];
                path[i] = 0;
                //System.out.println("aa");
                continue;
            }
            for (int j = 0; j <= i; j++) {
                int first = Integer.MAX_VALUE;
                int second = Integer.MAX_VALUE;
                if (j + 1 <= i) {
                    second = dp[j + 1][i];
                }
                first = minCount[j];
                if (second != Integer.MAX_VALUE) {
                    if (first + second < min) {
                        min = first + second;
                        path[i] = j + 1;
                    }
                } else {
                    continue;
                }
            }
            minCount[i] = min;
        }
        System.out.println(minCount[minCount.length - 1]);
        System.out.println(Arrays.toString(path));
        for (int i = path.length - 1; i >= 0; i--) {
            int val = path[i];
            String s = "";
            for (int j = val; j <= i; j++) {
                s += words[j];
                if (j != i) {
                    s += " ";
                }
            }
            i = val;
            res.add(s);
        }
        Collections.reverse(res);
        return res;
    }

}
