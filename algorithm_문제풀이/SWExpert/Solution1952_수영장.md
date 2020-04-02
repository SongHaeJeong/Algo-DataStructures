## Solution1952_수영장

>__문제 풀이__
>
>1. DFS를 통해 idx가 12를 넘어가면 최소값을 계산하고 종료함
>2. if(year[day] >0) 조건을 통해 이용한 횟수가 0 이상일 때 계산하게 함



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1952_수영장 {
	private static int[] year;
	private static int[] prices;
	private static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			ans = Integer.MAX_VALUE;
			prices = new int[4]; // 1일 , 1달 , 3달, 1년
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < prices.length; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}

			year = new int[12];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < year.length; i++) {
				year[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0, 0, 1);
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main

	private static void dfs(int day, int money, int term) {
		// TODO Auto-generated method stub
		if (day >= year.length) {
			ans = ans > money ? money : ans;
			return;
		}
		if(year[day] > 0) {
			
			dfs(day + 1, money + year[day] * prices[0], 1); // 하루 이용권
			dfs(day + 1, money + prices[1], 1); // 한달 이용권
			dfs(day + 3, money + prices[2], 3); // 세달 이용권
			dfs(day + 12, money + prices[3], 12); // 일년 이용권
		}else {
			dfs(day + term, money, term); // 그 달의 이용횟수가 0 이면 계산 할 필요 없음
		}
	}
}// end of class

```

