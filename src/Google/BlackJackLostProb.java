package Google;

/*
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=438935&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%255B3086%255D%255Bvalue%255D%3D8%26searchoption%255B3086%255D%255Btype%255D%3Dradio%26searchoption%255B3087%255D%255Bvalue%255D%3D3%26searchoption%255B3087%255D%255Btype%255D%3Dradio%26searchoption%255B3089%255D%255Bvalue%255D%255B3%255D%3D3%26searchoption%255B3089%255D%255Btype%255D%3Dcheckbox%26searchoption%255B3048%255D%255Bvalue%255D%3D2%26searchoption%255B3048%255D%255Btype%255D%3Dradio%26searchoption%255B3046%255D%255Bvalue%255D%3D1%26searchoption%255B3046%255D%255Btype%255D%3Dradio%26searchoption%255B3109%255D%255Bvalue%255D%3D2%26searchoption%255B3109%255D%255Btype%255D%3Dradio&page=1
 * 
 * http://www.1point3acres.com/bbs/thread-424050-1-1.html
 * 
 */

public class BlackJackLostProb {
	public static void main(String[] args) {
		int t1 = 0;
		int t2 = 1;
		int t3 = 10;
		int t4 = 13;
		int t5 = 16;
		int t6 = 17;
		double r1 = lostProb(t1);
		double r2 = lostProb(t2);
		double r3 = lostProb(t3);
		double r4 = lostProb(t4);
		double r5 = lostProb(t5);
		double r6 = lostProb(t6);
		System.out.println("t1 " + r1);
		System.out.println("t2 " + r2);
		System.out.println("t3 " + r3);
		System.out.println("t4 " + r4);
		System.out.println("t5 " + r5);
		System.out.println("t6 " + r6);
		
		double a1 = lostProb2(t1);
		double a2 = lostProb2(t2);
		double a3 = lostProb2(t3);
		double a4 = lostProb2(t4);
		double a5 = lostProb2(t5);
		double a6 = lostProb2(t6);
		System.out.println("a1 " + a1);
		System.out.println("a2 " + a2);
		System.out.println("a3 " + a3);
		System.out.println("a4 " + a4);
		System.out.println("a5 " + a5);
		System.out.println("a6 " + a6);
	}
	
	public static double lostProb(int total) {
		double[] dp = new double[26 + 1];
		dp[26] = 1;
		dp[25] = 1;
		dp[24] = 1;
		dp[23] = 1;
		dp[22] = 1;
		dp[21] = 0;
		dp[20] = 0;
		dp[19] = 0;
		dp[18] = 0;
		dp[17] = 0;
		for (int i = 16; i >= 0; i--) {
			double prob = 0;
			for (int j = 1; j <= 10; j++) {
				prob += 0.1 * dp[i + j];
			}
			dp[i] = prob;
		}
		return dp[total];
	}
	
	 public static double lostProb2(int sum) {
        if (sum >=17 && sum <= 21) return 0;
        if (sum > 21) return 1;
        double T = 0;
        for (int i = 1; i <= 10; ++ i) {
            T += lostProb2(sum + i) * 0.1;
        }
        return T;
    }
}
