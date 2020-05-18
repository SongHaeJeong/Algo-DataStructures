## Solution3074_입국심사

>__문제 풀이__
>
>1. 이분법을 통해서 문제를 풀었다.
>2. 심사위원이 몇명의 참가자를 검사할 수 있는지에 대해서 체크하고 그 값이 실제 인원보다 작은지 큰지에 대해서 계속 비교 해봤다.



```java
package solve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3074_입국심사 {
	private static int[] people;
	private static int[] desk;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 심사대의 수
			int M = Integer.parseInt(st.nextToken()); // 사람 수
			desk = new int[N]; // 심사대의 시간 구하기
			for (int i = 0; i < N; i++) {
				int time = Integer.parseInt(br.readLine());
				desk[i] = time;
			}
			Arrays.sort(desk);
			long min = 1;// 최적의 경우 1초로 초기화
			long max = (long) desk[desk.length - 1] * M;// 최악의 경우로 초기화
			long mid = 0;
			long sum;
			long answer = max;
			while (min <= max) {
				sum = 0;
				mid = (min + max) / 2;
				for (int time : desk) {
					sum += mid / time;// 심사관 당 맡을 수 있는 입국자 수
				}
				if (sum >= M) {// 더 맡을 수 있으므로 시간 줄임
					if (mid < answer) {
						answer = mid;
					}
					max = mid - 1;
				} else {// 불가하므로 시간 늘림
					min = mid + 1;
				}
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());

	}// end of main

}// end of class

```

