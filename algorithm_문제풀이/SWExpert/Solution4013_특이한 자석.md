## Solution4013_특이한 자석

>단순 시뮬레이션 문제이다.
>
>명령에 따른 회전과 연쇄되어지는 회전에 대해서 check() 함수로 flag 변수에 방향을 표시해줬다
>
>40분 정도 걸린것같음



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4013_특이한자석 {
	private static int K, ans;
	private static int[][] magnet;
	private static int[][] command;
	private static int[] flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			K = Integer.parseInt(br.readLine()); // 자석을 회전시키는 횟수
			StringTokenizer st;
			magnet = new int[4][8]; // 자석의 크기 설정 - 4개의 자석과 8개의 정보

			for (int i = 0; i < magnet.length; i++) { // 자석의 정보 입력 받음
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < magnet[i].length; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			command = new int[K][2]; // 자석 회전에 대한 명령어 배열 설정
			for (int i = 0; i < command.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				command[i][0] = Integer.parseInt(st.nextToken()); // 어떤 자석인지 입력
				command[i][1] = Integer.parseInt(st.nextToken()); // 시계방향인지 반시계인지 입력
			}

			ans = 0;
			RotateMagnet();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase
		System.out.println(sb.toString());
	}// end of main

	// 회전 시켜서 값 계산
	public static void RotateMagnet() {

		// N극 0 S극 1
		// 시계 1 반시계 -1

		for (int i = 0; i < command.length; i++) {
			flag = new int[4]; // 1,2,3,4의 자석중에 회전 시킬 자석 찾기
			int magNum = command[i][0]-1;
			int dir = command[i][1];
			check(magNum, dir); // 1번~4번까지 돌수있는지 확인

			// 방향 돌리기
			rotate(magNum, dir);
			for (int j = magNum+1; j < flag.length; j++) {
				if(flag[j] == 0) break;
				else rotate(j, flag[j]);
				
			}
			
			for (int j = magNum-1; j >=0; j--) {
				if(flag[j] == 0) break;
				else rotate(j,flag[j]);
			}
		

		}
		calValue(); //점수 계산

	}// end of RotateMagnet

	private static void calValue() {
		// TODO Auto-generated method stub
			for (int i = 0; i < magnet.length; i++) {
			if(magnet[i][0] == 1) {
				ans += 1 << i;
			}
		}
 	}

	public static void rotate(int magNum, int dir) {
		// 시계
		if (dir == 1) {
			int temp = magnet[magNum][7]; // 마지막꺼 저장하기 위한 임시 변수
			for (int j = 7; j > 0; j--) {
				magnet[magNum][j] = magnet[magNum][j - 1];
			}
			magnet[magNum][0] = temp; // 회전시킴

			// 반시계
		} else if (dir == -1) {
			int temp = magnet[magNum][0];
			for (int i = 0; i < 7; i++) {
				magnet[magNum][i] = magnet[magNum][i + 1];
			}
			magnet[magNum][7] = temp;
		}
	}

	public static void check(int magNum, int dir) {
		flag[magNum] = dir;

		for (int i = magNum; i <magnet.length-1; i++) {
			if(magnet[i][2] != magnet[i+1][6]) {
				if(flag[i] == 1) flag[i+1] = -1;
				else flag[i+1] = 1;
			}else {
				break;
			}
		}
		
		for (int i = magNum; i >0; i--) {
			if(magnet[i][6] != magnet[i-1][2]) {
				if(flag[i] == 1) flag[i-1] = -1;
				else flag[i-1] = 1;
			}else {
				break;
			}
		}

	}

}// end of class

```

