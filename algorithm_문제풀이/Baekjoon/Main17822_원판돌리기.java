package Algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main17822_원판돌리기 {
	private static int N, M, T;
	private static int[][] arr;
	private static Info[] command;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		command = new Info[T];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			command[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		process();

	}// end of main

	// 명령어 실행
	private static void process() {
		for (int i = 0; i < command.length; i++) {
			Info in = command[i];
			int number = in.number;
			int dir = in.dir;
			int count = in.count;

			// 회전

			for (int j = number; j <= N; j++) {
				// 배수만 회전
				if (j % number == 0) {
					// 시계 방향
					for (int l = 0; l < count; l++) {

						if (dir == 0) {
							int temp = arr[j][M - 1];
							for (int k = M - 1; k > 0; k--) {
								arr[j][k] = arr[j][k - 1];
							}

							arr[j][0] = temp;

						} else { // 반시계
							int temp = arr[j][0];
							for (int k = 0; k < M - 1; k++) {
								arr[j][k] = arr[j][k + 1];
							}

							arr[j][M - 1] = temp;
						}
					}

				}

			}
			// 인접한 거 찾기
			find();

		}

		int result = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] > 0) {
					result += arr[i][j];
				}
			}
		}

		System.out.println(result);
	}

	private static void find() {
		boolean flag = false;
		ArrayList<Pair> list = new ArrayList<Pair>();
		boolean[][] visited = new boolean[N + 1][M];

		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				// 좌우 먼저 인접 검사

				if (arr[i][j] > 0) {

					int left = j - 1;
					int right = j + 1;
					if (left < 0)
						left = M - 1;
					if (right >= M)
						right = 0;

					if (arr[i][j] == arr[i][left] || arr[i][j] == arr[i][right]) {
						flag = true;
						list.add(new Pair(i, j));
					}

					// 상하 검사
					int up = i - 1;
					int down = i + 1;
					if (up > 0 && arr[i][j] == arr[up][j]) {
						flag = true;
						list.add(new Pair(i, j));
					}

					if (down <= N && arr[i][j] == arr[down][j]) {
						flag = true;
						list.add(new Pair(i, j));
					}
				}
			}
		}

		int value = 0;
		int count = 0;
		if (!flag) {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] > 0) {
						value += arr[i][j];
						count++;

					}
				}
			}

			double a = (double)value / count;

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[i][j] > 0) {

						if (arr[i][j] > a)
							arr[i][j] = arr[i][j] - 1;
						else if (arr[i][j] < a)
							arr[i][j] = arr[i][j] + 1;
					}

				}
			}

		} else {
			for (Pair pair : list) {
				arr[pair.row][pair.count] = -1;
			}
		}
	}

	static class Pair {
		int row;
		int count;

		public Pair(int row, int count) {
			super();
			this.row = row;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Pair [row=" + row + ", count=" + count + "]";
		}

	}

	static class Info {
		int number;
		int dir;
		int count;

		public Info(int number, int dir, int count) {
			super();
			this.number = number;
			this.dir = dir;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Info [number=" + number + ", dir=" + dir + ", count=" + count + "]";
		}

	}
}// end of class
