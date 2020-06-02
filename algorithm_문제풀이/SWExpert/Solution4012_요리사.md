## Solution4012_요리사

> 두번의 조합을 이용해서 문제 풀음 
>
> 첫번째 : 음식재료 / 2에 대해서 A,B가 나누어 가질 때 한번 사용
>
> 두번째 : 나누어가진 요리에 대해서 2개씩 나누어짐 예를 들어, A 재료가 0 , 1 , 2 가지고 있으면 이거에 대해서 (0,1), (1,0), (1,2) (2,1) (0,2) (2,0) 계산하기 위해서 조합 사용

재풀이 시간 : 20분

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4012_요리사 {
	private static int N;
	private static int[][] food;
	private static int[] cooker;
	private static int[] aSet;
	private static int[] bSet;
	private static int[] temp;
	private static int ans, aResult, bResult;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine()); // 음식의 개수
			food = new int[N][N];

			StringTokenizer st;
			for (int i = 0; i < food.length; i++) {
				st = new StringTokenizer(br.readLine(), " "); // 음식 시너지에 대한 입력 받기
				for (int j = 0; j < food.length; j++) {
					food[i][j] = Integer.parseInt(st.nextToken()); // 음식 시너지 입력
				}
			}

			cooker = new int[N]; // 요리사의 팀을 나누기위해서 배열 생성
			aSet = new int[N / 2]; // A,B팀에 대한 음식 재료 나누기 위해 배열 생성
			for (int i = 0; i < cooker.length; i++) {
				cooker[i] = i;
			}

			ans = Integer.MAX_VALUE;
			teamSplit(0, 0);
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		} // end of testCase
		System.out.println(sb.toString());

	}// end of main

	// A,B팀에서 음식재료를 반으로 나누는 작업
	private static void teamSplit(int idx, int k) {
		// TODO Auto-generated method stub
		if (idx == N / 2) {
			process();
			return;
		}

		for (int i = k; i < cooker.length; i++) {
			aSet[idx] = cooker[i];
			teamSplit(idx + 1, i + 1);
		}
	}

	private static void process() {
		// TODO Auto-generated method stub
		// A팀에 대한 정보를 통해 B팀을 나눠야됨
		bSet = new int[N / 2];
		int bIdx = 0;

		boolean[] flag = new boolean[N];
		for (int i = 0; i < aSet.length; i++) {
			flag[aSet[i]] = true;
		}

		temp = new int[2];
		for (int i = 0; i < flag.length; i++) {
			if (!flag[i]) {
				bSet[bIdx++] = i;
			}
		}

		// A 재료가 0,1,2 면, 이거에 대해서도 (0,1), (1,0) ,(1,2),(2,1), (0,2),(2,0) 계산해줘야됨
		aResult = 0;
		bResult = 0;
		recipe(0, 0, aSet,0);
		recipe(0, 0, bSet,1);
		ans = ans > Math.abs(aResult - bResult) ? Math.abs(aResult - bResult) : ans;
		

	}
	
	private static void recipe(int idx, int k, int[] set,int type) {
		if(idx == 2) {
			if(type == 0) {
				
				aResult += food[temp[0]][temp[1]];
				aResult += food[temp[1]][temp[0]];
			}else {
				
				bResult += food[temp[0]][temp[1]];
				bResult += food[temp[1]][temp[0]];
			}
			
			
			return ;
		}
		
		for (int i = k; i < set.length; i++) {
			temp[idx] = set[i];
			recipe(idx +1, i+1, set,type);
			
			
		}
	}
}// end of class

```

```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4012_요리사 {
	private static int N, ans;
	private static int[][] food;
	private static int[] A;
	private static int[] B;
	private static int[] set = new int[2];
	private static int aTotal;
	private static int bTotal;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			food = new int[N][N];
			for (int i = 0; i < food.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < food.length; j++) {
					food[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			A = new int[N / 2];
			B = new int[N / 2];
			ans = Integer.MAX_VALUE;
			split(0, 0);
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}// end of main

	private static void split(int idx, int k) {
		// TODO Auto-generated method stub
		if (idx == N / 2) {
			process();
			return;
		}

		for (int i = k; i < N; i++) {
			A[idx] = i;
			split(idx + 1, i + 1);
		}
		
		
	}

	private static void process() {
		// TODO Auto-generated method stub
		boolean[] visited = new boolean[N];
		for (int i = 0; i < A.length; i++) {
			visited[A[i]] = true;
		}
		int bIdx = 0;
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				B[bIdx++] = i;
		}
		aTotal = 0;
		bTotal = 0;
		makeRecipe(A, 0, 0, 0);
		makeRecipe(B, 0, 0, 1);
		
		ans = Math.min(ans, Math.abs(aTotal - bTotal));

	}

	private static void makeRecipe(int[] a2, int idx, int k, int type) {
		if(idx == 2) {
			if(type == 0) {
				aTotal += food[set[0]][set[1]];
				aTotal += food[set[1]][set[0]];
			}else {
				bTotal += food[set[0]][set[1]];
				bTotal += food[set[1]][set[0]];
			}
			
			return;
		}
		
		for (int i = k; i < a2.length; i++) {
			set[idx] = a2[i];
			makeRecipe(a2, idx+1, i+1, type);
		}
	}
}// end of class
	
```

