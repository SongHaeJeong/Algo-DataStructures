package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14501_Επ»η {
	private static int N;
	private static int[][] arr;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][2];

		for (int i = 1; i < arr.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MIN_VALUE;
		dfs(1,0);

		System.out.println(result);

	}

	public static void dfs(int days, int pay) {
		if (days > N) {
			result = result < pay ? pay : result;
			return;
		}

		for (int j = days; j < arr.length; j++) {
			if (j + arr[j][0] <= N+1) {
				dfs(j + arr[j][0], pay+arr[j][1]);
			}else {
				dfs(j+arr[j][0] , pay);
			}
		}
	}
}
