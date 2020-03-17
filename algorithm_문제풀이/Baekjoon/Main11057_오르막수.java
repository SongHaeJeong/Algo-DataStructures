package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 	점화식 구하기
 *		0 1 2 3 4 5 6 7 8 9 
 *  N=1 1 1 1 1 1 1 1 1 1 1
 *  N=2 10 9 8 7 6 5 4 3 2 1
 *  N=3 55 45 36 --------- 1
 * 
 *  dp[3][0] = dp[2][0] + ---- + dp[2][9]
 */

public class Main11057_오르막수 {
	private static int N;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][10];

		// 점화식 N=1 일때 한가지씩이니깐 1로 채운다

		for (int i = 0; i < dp[0].length; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {

			for (int j = 0; j < dp[0].length; j++) {
				for (int k = j; k < dp[0].length; k++) {
					dp[i][j] += dp[i-1][k];
				}
				dp[i][j] %= 10007;

			}

		}
		
		int result = 0;
		for (int i = 0; i < dp[0].length; i++) {
			result += dp[N][i];
		}

		System.out.println(result %10007);
		
	}// end of main

}// end of class
