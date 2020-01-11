package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main12100_2048 {
	private static int[][] map;
	private static int N;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				result = result < map[i][j] ? map[i][j] : result;
			}
		}
		dfs(0);
		System.out.println(result);

	}

	static void dfs(int depth) {
		if (depth == 5) {
			findMaxNumber();
			return;
		}

		int[][] copyMap = new int[N][N];
		for (int i = 0; i < copyMap.length; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, N);

		}
		for (int i = 0; i < dx.length; i++) {
			move(i);
			dfs(depth + 1);
			for (int j = 0; j < copyMap.length; j++) {
				System.arraycopy(copyMap[j], 0, map[j], 0, N);

			}

		}
	}

	static void findMaxNumber() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				result = result < map[i][j] ? map[i][j] : result;
			}
		}
	}

	static void move(int dir) {
		// ºÏµ¿³²¼­
		Queue<Integer> queue = new LinkedList<Integer>();
		switch (dir) {
		case 0:
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if (map[j][i] != 0) {
						queue.add(map[j][i]);
						map[j][i] = 0;
					}

				}

				int idx = 0;
				while (!queue.isEmpty()) {
					int data = queue.peek();
					queue.poll();
					if (map[idx][i] == 0)
						map[idx][i] = data;
					else if (map[idx][i] == data) {
						map[idx][i] *= 2;
						idx++;
					} else {
						idx++;
						map[idx][i] = data;
					}

				}
			}

			break;
		case 1:
			for (int i = 0; i < map.length; i++) {
				for (int j = map.length - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						queue.add(map[i][j]);
						map[i][j] = 0;
					}
				}

				int idx = map.length - 1;
				while (!queue.isEmpty()) {
					int data = queue.peek();
					queue.poll();
					if (map[i][idx] == 0)
						map[i][idx] = data;
					else if (map[i][idx] == data) {
						map[i][idx] *= 2;
						idx--;
					} else {
						idx--;
						map[i][idx] = data;
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < map.length; i++) {
				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0) {
						queue.add(map[j][i]);
						map[j][i] = 0;
					}
				}

				int idx = N - 1;
				while (!queue.isEmpty()) {
					int data = queue.peek();
					queue.poll();
					if (map[idx][i] == 0)
						map[idx][i] = data;
					else if (map[idx][i] == data) {
						map[idx][i] *= 2;
						idx--;
					} else {
						idx--;
						map[idx][i] = data;
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < map.length; i++) {
				for (int j = 0 ; j < map.length; j++) {
					if(map[i][j] != 0) {
						queue.add(map[i][j]);
						map[i][j] = 0;
					}
				}
				
				int idx = 0;
				while(!queue.isEmpty()) {
					int data = queue.peek();
					queue.poll();
					if(map[i][idx] == 0) {
						map[i][idx] = data;
					}else if (map[i][idx] == data) {
						map[i][idx] *= 2;
						idx++;
					}else {
						idx++;
						map[i][idx] = data; 
					}
				}
			}

		default:
			break;
		}
	}

	static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Pair {
		int x;
		int y;
		int num;

		public Pair(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", num=" + num + "]";
		}

	}
}