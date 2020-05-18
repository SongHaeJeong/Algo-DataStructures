## Solution1486_장훈이의높은선반

>__문제 풀이__
>
>1. 입력받은 사람들의 키에 따라 조합을 찾음
>2. 만약 그 조합이 B 보다 높다면 최소값을 찾아주는 식으로 문제를 해결함



```java
package solve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1486_장훈이의높은선반 {
	private static int N, B,minNum;
	private static int[] people;
	private static int[] set ;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			people = new int[N];
			minNum = Integer.MAX_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				people[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 1; i <= N; i++) {
				set = new int[i];
				combination(0,0, i);
			}
			
			sb.append("#").append(testCase).append(" ").append(minNum).append("\n");
		}
		System.out.println(sb.toString());

	}// end of main
	
	private static void combination(int idx, int k , int num) {
		if(idx == num) {
			
			int result = 0;
			for (int i = 0; i < set.length; i++) {
				result += set[i];
			}
			
			if(result >= B) {
				minNum = minNum > (result - B) ? result - B : minNum;
			}
			
			return;			
		}
		
		for (int i = k; i < people.length; i++) {
			set[idx] = people[i];
			combination(idx+1, i+1, num);
		}
		
		
		
	}
}// end of class

```

