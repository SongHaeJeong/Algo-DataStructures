## Solution4008_숫자만들기

>cal(int calNumber, int idx) : idx는 연산자의 인덱스를 관리하기 위해 선언, calNumber는 계산되는 숫자를 가지고 다니면서 idx == N-1 종료 조건 때, minNum, maxNum 계산해준다.
>
>



```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4008_숫자만들기 {
	private static int N;
	private static int[] command;
	private static int[] arr;
	private static int minNum, maxNum;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			command = new int[4]; // 수식을 받을 배열
			StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 수식 입력 받기 
			
			for (int i = 0; i < 4; i++) {
				command[i] = Integer.parseInt(st.nextToken()); // 수식 입력
			}
			
			arr = new int[N]; //수식에 사용되는 숫자
			
			st = new StringTokenizer(br.readLine() , " "); //숫자 입력 받기 
			
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken()); // 숫자 입력
			}
			minNum = Integer.MAX_VALUE; // 최소값을 구하기 위한 변수
			maxNum = Integer.MIN_VALUE; // 최대값을 구하기 위한 변수
			cal(arr[0], 0); // DFS을 이용해서 계산
			
			sb.append("#").append(testCase).append(" ").append(maxNum - minNum).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
	private static void cal(int calNumber, int idx) {
		// TODO Auto-generated method stub
		if(idx == N-1) {
			minNum = minNum > calNumber ? calNumber : minNum;
			maxNum = maxNum < calNumber ? calNumber : maxNum;
			
			return;
			
		}
		
		
		for (int i = 0; i < command.length; i++) {
			if(command[i] > 0) {
				command[i]--;
				if(i == 0) cal(calNumber + arr[idx+1], idx +1);
				else if(i == 1) cal(calNumber - arr[idx+1], idx+1);
				else if(i== 2)cal(calNumber * arr[idx+1], idx+1);
				else cal(calNumber / arr[idx +1], idx +1);
				command[i]++;
			}
		}
		
	}
}//end of class
		
```

```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4008_숫자만들기 {
	private static int N, minNum, maxNum;
	private static int ans;
	private static int[] command;
	private static int[] num;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			command = new int[4];
			num = new int[N];
			minNum = Integer.MAX_VALUE;
			maxNum = Integer.MIN_VALUE;
			for (int i = 0; i < 4; i++) {
				command[i] = Integer.parseInt(st.nextToken());				
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < num.length; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, num[0]);
			sb.append("#").append(testCase).append(" ").append(maxNum - minNum).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}//end of main
	private static void dfs(int idx, int value) {
		// TODO Auto-generated method stub
		if(idx == N-1) {
			minNum = minNum > value ? value : minNum;
			maxNum = maxNum < value ? value : maxNum;
			return;
		}
		
		for (int i = 0; i < command.length; i++) {
			if(command[i] > 0) {
				command[i]--;
				
				if(i == 0 ) {
					dfs(idx+1, value + num[idx+1]);
				}else if(i == 1) {
					dfs(idx+1, value - num[idx+1]);
				}else if(i == 2) {
					dfs(idx+1, value * num[idx+1]);
				}else if(i == 3) {
					dfs(idx+1, value / num[idx+1]);
				}
				
				command[i]++;			
				
			}
		}
	}
}//end of class

```

