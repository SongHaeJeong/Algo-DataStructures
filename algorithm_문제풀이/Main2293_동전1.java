package Algo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;



/*
 *  처음에 dfs로 k를 가지고 다니면서 현재 인덱스값을 빼줘서 count == 0 이면 result를 하나씩 추가해주고
 *  0보다 작으면 return; 해줘서 이전의 값과 다음 value[idx+1]값을 빼줘서 result를 최종적으로 출력했는데 
 *  12%에서 시간초과
 * 	그래서 다른 방법으로 풀었다 
 *  dp[0] = 1한 이유는 확률에서 아무것도 선택하지 않았을 때 1로 설정하는 것과 같은 의미
 *  
 *  예시에서 value = [1, 2, 5] , k = 10;
 *  1원만 사용하는 경우   
 *  1원 2원 3원 4원 5원 6원 ----- 10원 ==> 모두 1가지씩
 *    
 *  1,2원만 사용하는 경우 표를 그려서 만들어보면 dp[j] += dp[j-value[i]] 라는 점화식이 나옵니다. *  
 * 
 */


public class Main2293_동전1 {
	private static int n,k, result;
	private static int[] value;
	private static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n =Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		value = new int[n];
		
		for (int i = 0; i < value.length; i++) {
			value[i] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[k+1];
		dp[0] = 1;
		result = 0;
		
		for (int i = 0; i < value.length; i++) {
			for (int j = value[i] ; j <=k ; j++) {
				dp[j] += dp[j-value[i]];
			}			
		}
		
//		dfs(0, k);
		
		System.out.println(dp[k]);
		
	}//end of main
	private static void dfs(int idx, int count) {
		// TODO Auto-generated method stub
		if(idx >= n ) return;
		if(count < 0) return;
		if(count == 0) {
			result++;
			return;
		}
		
		dfs(idx, count - value[idx]);
		dfs(idx+1, count);
	}
}//end of class
