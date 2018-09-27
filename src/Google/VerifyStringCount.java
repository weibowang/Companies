package Google;

/**
 * 
 * 
 *http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=438935&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3087%255D%255Bvalue%255D%3D3%26searchoption%255B3087%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3048%255D%255Bvalue%255D%3D2%26searchoption%255B3048%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 *
 */

public class VerifyStringCount {
	public static void main(String[] args) {
		String s = "4gray6hunter";
		boolean res = verifyStringCount(s);
		System.out.println(res);
		
		String s2 = "11gray6hunter";
		boolean res2 = verifyStringCount(s2);
		System.out.println(res2);
		
		String s3 = "124gray6hunter";
		boolean res3 = verifyStringCount(s3);
		System.out.println(res3);
	}
	
	public static boolean verifyStringCount(String s) {
		boolean[] dp = new boolean[s.length() + 1];
		dp[s.length()] = true;
		for (int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if (c >= '0' && c <= '9') {
				for (int j = i; j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9'; j++) {
					String num = s.substring(i, j + 1);
					int count = Integer.parseInt(num);
					if (j + count + 1 <= s.length() && dp[j + count + 1]) {
						dp[i] = true;
						break;
					}
				}
			}
		}
		return dp[0];
	}
}
