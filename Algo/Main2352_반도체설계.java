package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2352_반도체설계 {
	private static int[] port;
	private static int result;
	private static int N;
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		port = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine() ," ");
		for (int i = 1; i < port.length; i++) {
			port[i] = Integer.parseInt(st.nextToken());
		}
		
		result = Integer.MIN_VALUE;
		sb = new StringBuilder();
		dfs(1, 0, port[0]); // idx, count;
		System.out.println(result);
		
		
	}
	
	public static void dfs(int idx, int cnt, int value) {
		if(idx > N) {
			result = result < cnt ? cnt : result;
			System.out.println(sb.toString());
			sb = new StringBuilder();
		}
		
		for (int i = idx; i < port.length; i++) {
			if(port[i] > value) {
				sb.append(idx).append(" ");
				dfs(idx +1, cnt +1 , port[i]);
			}else {
				dfs(idx+1, cnt , value);
			}
		}
	}
}
