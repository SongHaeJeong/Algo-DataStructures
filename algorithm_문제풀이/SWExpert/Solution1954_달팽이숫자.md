## Solution1954_달팽이숫자

>__문제 풀이__
>
>1. 네개의 Index 변수를 조작하여 Map 배열에 Value 값을 입력함
>2. While()문을 통해서 계속 반복하며 , 종료 조건은 입력받은 배열의 크기가 홀수, 짝수 인지에 따라 다르게 조건 설정함

```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1954_달팽이숫자 {
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			solution(N);
			sb.append("#").append(testCase).append("\n");
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			
			
		}
		System.out.println(sb.toString());
	}//end of main

	private static void solution(int n) {
		// TODO Auto-generated method stub
		map = new int[n][n];
		int value = 1;
		int sR = 0;
		int sC = 0;
		int eR = n-1;
		int eC = n-1;
		
		while(true) {
			
			if(sR == eR && sC == eC || (sR-1 == eR && sC-1 == eC)) break;
			
			
			for (int i = sC; i <= eC; i++) {
				map[sR][i] = value++;
			}
			
			for (int i = sR+1; i <= eR; i++) {
				map[i][eC] = value++;
			}
			
			for (int i = eC-1; i >= sC; i--) {
				map[eR][i] = value++;
			}
			
			for (int i = eR-1; i > sR ; i--) {
				map[i][sC] = value++;
			}
			
			sR++;
			sC++;
			eR--;
			eC--;
		}
		
		if(n % 2 == 1) map[sR][sC] = value;
		
	}
}//end of class

```

