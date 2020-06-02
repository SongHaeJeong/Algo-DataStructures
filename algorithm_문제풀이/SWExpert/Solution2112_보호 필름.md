## Solution2112_보호 필름

>풀이 방법
>
>- 처음에 색칠을 하지 않고 검사 (idx >= D) 종료 조건
>- 이 후 0으로 색칠
>- 이 후 1로 색칠
>
>모든 경우의 수를 다 따져보고 가장 작은 값을 답으로 출력



>__재풀이 시간__
>
>30분
>
>- 가지치기를 안해줬더니 시간초과 발생했음.



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2112_보호필름 {
	private static int D, W, K, ans;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트케이스
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken()); // 세로 길이
			W = Integer.parseInt(st.nextToken()); // 가로 길이
			K = Integer.parseInt(st.nextToken()); // 크기
			
			map = new int[D][W]; // 맵에 대한 정보
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) { 
					map[i][j] = Integer.parseInt(st.nextToken());					
				}
			}
			ans = Integer.MAX_VALUE;
			draw(map, 0, 0); 
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}//end of testCase
		System.out.println(sb.toString());
	}//end of main

	
	// 맵을 들고다니면서 색칠하고 return 되면 이전의 맵을 사용, cnt 색칠한 갯수, idx 배열의 높이를 결정하는 변수
	private static void draw(int[][] prev, int cnt, int idx) {
		if(ans <= cnt) return; // 가지치기
		
		if(idx >= D) {
			if(check(prev))ans = ans > cnt ? cnt : ans;
			return;
		}
		
	    int[][] temp = new int[D][W];
        for (int i = 0; i < D; i++) {
        	temp[i] = Arrays.copyOf(prev[i], prev[i].length); // 이전의 값을 복사하여 계산        	
        }
        
		draw(temp, cnt, idx +1); // 아무것도 칠하지 않았을 때
		
		Arrays.fill(temp[idx], 1); // 1의 값을 모두 칠했을때
		draw(temp, cnt +1, idx +1); 
		
		Arrays.fill(temp[idx], 0); // 0의 값을 모두 칠했을 때
		draw(temp,cnt +1, idx +1);
		
		
	}

	private static int[][] copyMap(int[][] map2) { // 맵을 복사
		// TODO Auto-generated method stub
		int[][] temp = new int[D][W];
		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, map[i].length);
			
		}
		
		return temp;
	}

	private static boolean check(int[][] map) {
		for (int i = 0; i < W; i++) {
			boolean flag = false; // 각 열마다 성능 검사를 위한 변수
			for (int j = 0; j <= D-K; j++) {
				flag = true; // 성능 검사가 가능하다는 조건으로 초기값 설정
				for (int d = j; d < j+K; d++) {
					
					if(map[j][i] != map[d][i]) {
						flag = false; // 성능 검사가 불가능하면 flag = false;
						break; 
					}				
					
				}
				
				if(flag) break; // 성능 검사가 가능하면 다음 열의 상태를 확인					
				
			}
			
			if(!flag) return false; //끝까지 검사해봤는데 false 이면 그 열을 성능 검사 불가능
		}		
		
		return true; // 모두 검사가 완료되었으면 성능검사 True
	}
}//end of class

```



```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution2112_보호필름 {
	private static int ans;
	private static int D, W, K;
	private static int[][] map;
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			ans = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0,0,map);
			
			
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
	private static void dfs(int idx, int count, int[][] prev) {
		if(ans <= count) return;
		// TODO Auto-generated method stub
		if(idx == D ) {
			if(check(prev)) {
				ans = ans > count ? count : ans;
			}
			return;			
		}
		
	    int[][] temp = new int[D][W];
        for (int i = 0; i < D; i++) {
        	temp[i] = Arrays.copyOf(prev[i], prev[i].length); // 이전의 값을 복사하여 계산        	
        }
		
		dfs(idx +1 , count, temp);
		
		Arrays.fill(temp[idx], 0);
		dfs(idx+1, count+1, temp);
		Arrays.fill(temp[idx], 1);
		dfs(idx+1, count+1, temp);
	}
	private static boolean check(int[][] prev) {
		boolean flag = true;
		for (int i = 0; i < W; i++) {
			
			int count = 1;
			for (int j = 0; j < D-1; j++) {
				if(prev[j][i] == prev[j+1][i]) {
					count++;
					if(count >= K) break;
				}else {
					count = 1;
				}
				
			}
			
			if(count < K) {
				flag = false;
				break;
			}
		}
		return flag;
	}
}//end of class
	
```

