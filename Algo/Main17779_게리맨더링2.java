package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17779_게리맨더링2 {
	private static int N, maxNum, minNum, result;
	private static int[][] A, map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N][N]; // 입력 인구수
		for (int i = 0; i < A.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < A.length; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		result = Integer.MAX_VALUE;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (i >= i + d1 + d2 || i + d1 + d2 >= N)
							continue;
						if (j - d1 >= j || j >= j + d2 || j + d2 >= N || j - d1 < 0)
							continue;
						else {
							split5(i, j, d1, d2);
							splitElse(i, j, d1, d2);
							check();

						}

					}
				}
			}
		}
		System.out.println(result);
	}// end of main

	private static void check() {
		// TODO Auto-generated method stub
		maxNum = Integer.MIN_VALUE;
		minNum = Integer.MAX_VALUE;
		int[] cal = new int[5];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {

				if (map[i][j] == 1)	cal[0] += A[i][j];
				else if(map[i][j] == 2) cal[1] +=A[i][j];
				else if(map[i][j] == 3) cal[2] +=A[i][j];
				else if(map[i][j] == 4) cal[3] +=A[i][j];
				else if(map[i][j] == 5) cal[4] +=A[i][j];
			}
		}
		
		for (int i = 0; i < cal.length; i++) {
			maxNum = maxNum < cal[i] ? cal[i] : maxNum ;
			minNum = minNum > cal[i] ? cal[i] : minNum;
		}
		
		result = result > maxNum - minNum ? maxNum - minNum : result;
		
		
	}

	// (x,y)와 경계의 길이 d1,d2를 정한다.(d1,d2 >= 1, 1<= x < x+d1+d2 <=N , 1 <=y-d1 < y
	// <y+d2 <=N)
	private static void splitElse(int x, int y, int d1, int d2) {
		// 제 1구역
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (map[i][j] != 5)
					map[i][j] = 1;
			}
		}

		// 제 2구역
		for (int i = 0; i <= x + d2; i++) {
			for (int j = y + 1; j < N; j++) {
				if (map[i][j] != 5)
					map[i][j] = 2;
			}
		}

		// 제 3구역

		for (int i = x + d1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (map[i][j] != 5)
					map[i][j] = 3;
			}
		}

		// 제 4구역
		for (int i = x + d2 + 1; i < N; i++) {
			for (int j = y - d1 + d2; j < N; j++) {
				if (map[i][j] != 5)
					map[i][j] = 4;
			}
		}
	}

	private static void split5(int x, int y, int d1, int d2) {
		map = new int[N][N];

		for (int i = 0; i <= d1; i++) {
			map[x + i][y - i] = 5;
		}

		for (int i = 0; i <= d2; i++) {
			map[x + i][y + i] = 5;
		}

		for (int i = 0; i <= d2; i++) {
			map[x + d1 + i][y - d1 + i] = 5;
		}
		for (int i = 0; i <= d1; i++) {
			map[x + d2 + i][y + d2 - i] = 5;
		}

		for (int i = 0; i < map.length; i++) {
			int left = 0;
			int right = map.length - 1;

			while (left < N && map[i][left] == 0) {
				left++;
			}

			if (left == N)
				continue;

			while (right >= 0 && map[i][right] == 0) {
				right--;
			}

			if (left == right)
				continue;
			else {
				for (int j = left; j < right; j++) {
					map[i][j] = 5;
				}
			}

		}
	}

}// end of class
