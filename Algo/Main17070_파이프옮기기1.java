package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17070_파이프옮기기1 {
	private static int N, result;
	private static int[][] map;
	private static int[] dx = { 0, 1, 1 }; // 가로 세로 대각선
	private static int[] dy = { 1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		result = 0;

		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < map.length; i++) {
			map[i][0] = -1;
			map[0][i] = -1;
		}

		BFS();
		System.out.println(result);

	}// end of main

	public static void BFS() {
		Queue<Info> queue = new LinkedList<>();
		queue.add(new Info(1, 2, 0));

		while (!queue.isEmpty()) {
			Info in = queue.poll();
			int row = in.row;
			int column = in.column;
			int type = in.type;

			if (row == N && column == N) {
				result++;
				continue;
			}

			// 가로
			if (type == 0) {
				// 가로 , 대각선 가능

				for (int i = 0; i < dx.length; i++) {
					if (i != 1) { // 세로는 안됨
						boolean flag = false;
						int nRow = row + dx[i];
						int nColumn = column + dy[i];

						if (nRow > N || nColumn > N || map[nRow][nColumn] == 1)
							continue;
						if (i == 2) {

							for (int j = 0; j < 2; j++) {
								int tempRow = row + dx[j];
								int tempColumn = column + dy[j];

								if (tempRow > N || tempColumn > N || map[tempRow][tempColumn] == 1) {
									flag = true;
									break;
								}

							}

						}
						if (!flag)
							queue.add(new Info(nRow, nColumn, i));

					}
				}

				// 세로
			} else if (type == 1) {
				boolean flag = false;
				for (int i = 0; i < dx.length; i++) {
					if (i != 0) { // 세로는 안됨
						int nRow = row + dx[i];
						int nColumn = column + dy[i];

						if (nRow > N || nColumn > N || map[nRow][nColumn] == 1)
							continue;

						if (i == 2) {

							for (int j = 0; j < 2; j++) {
								int tempRow = row + dx[j];
								int tempColumn = column + dy[j];

								if (tempRow > N || tempColumn > N || map[tempRow][tempColumn] == 1) {
									flag = true;
									break;
								}

							}

						}
						if (!flag)
							queue.add(new Info(nRow, nColumn, i));
					}
				}
				// 대각선
			} else if (type == 2) {
				boolean flag = false;
				for (int i = 0; i < dx.length; i++) {
					int nRow = row + dx[i];
					int nColumn = column + dy[i];

					if (nRow > N || nColumn > N || map[nRow][nColumn] == 1)
						continue;

					if(i == 2) {
						
						for (int j = 0; j < 2; j++) {
							int tempRow = row +dx[j];
							int tempColumn = column + dy[j];
							
							if(tempRow > N || tempColumn > N || map[tempRow][tempColumn] == 1) {
								flag = true;
								break;
							}
							
						}
						
						
					}
					if(!flag) queue.add(new Info(nRow, nColumn, i));

				}
			}
		}

	}

	static class Info {
		int row;
		int column;
		int type;

		public Info(int row, int column, int type) {
			super();
			this.row = row;
			this.column = column;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Info [row=" + row + ", column=" + column + ", type=" + type + "]";
		}

	}
}// end of class
