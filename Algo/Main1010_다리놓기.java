package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 		1 2 3 4 5
 * N=1  1 2 3 4 5
 * N=2    1, 1+2, 1+2+3, 1+2+3+4, 1+2+3+4+5
 * N=3 		1, 1+ 1+2, 1+1+2+1+2+3 ------
 * 
 * 규칙을 봐서 점화식으로 품
 * 
 * 
 */
public class Main1010_다리놓기 {
	private static int N;
	private static int M;
	private static int result;
	private static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for (int testCase = 1; testCase <=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			result = 0;
			process();
			System.out.println(dp[N][M]);
			
			
		}	
		
	}//end of main

	private static void process() {
		dp = new int[N+1][M+1];
		
		// N = 1 : M만큼 1가지 가능
		for (int i = 1; i < dp[0].length; i++) {
			dp[1][i] = i;
		}
		
		
		for (int i = 2; i <=N; i++) {
			
			for (int j = i; j < dp[0].length; j++) {
				
				for (int k = j-1; k >=1; k--) {
					dp[i][j] += dp[i-1][k];
				}
				
				
			}
			
			
		}
		
		
		
		
		
	}
}//end of class
