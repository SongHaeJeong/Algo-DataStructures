package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1699_Á¦°ö¼öÀÇÇÕ {
	private static int N;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		dp[1] = 1;
		for(int i = 2; i < N+1; i++) {
			dp[i] = i;
			for(int j = 1; j*j <= i; j++)
				dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
		}
		
		System.out.println(dp[N]);
		
	}//end of main


		

}//end of class
