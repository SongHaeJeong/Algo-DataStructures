package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main3190_¹ì {
	private static int[][] map;
	private static int N;
	private static Info[] info;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	private static int result;
	private static int L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2];
		int K = Integer.parseInt(br.readLine());
		map[1][1] = 1;
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 4;
		}
		L = Integer.parseInt(br.readLine());
		info = new Info[L];
		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			info[i] = new Info(Integer.parseInt(st.nextToken()), st.nextToken());
		}

		for (int i = 0; i < N + 2; i++) {
			map[i][0] = -1;
			map[0][i] = -1;
			map[N + 1][i] = -1;
			map[i][N + 1] = -1;
		}
		result = 0;
		solve();
		System.out.println(result);

	}// end of main

	public static void solve() {
		Deque<Info> deque = new LinkedList<Info>();
		deque.addFirst(new Info(1, 1));
		int dir = 0;
		int timeIdx = 0;

		while (true) {

			result++;

			int nx = deque.getFirst().x + dx[dir];
			int ny = deque.getFirst().y + dy[dir];
			if (map[nx][ny] == 4) {
				deque.addFirst(new Info(nx, ny));
				map[nx][ny] = 1;
			} else if (map[nx][ny] == 0) {
				deque.addFirst(new Info(nx, ny));
				Info de = deque.peekLast();
				map[nx][ny] = 1;
				map[de.x][de.y] = 0;
				deque.removeLast();
			} else {
				break;
			}
			if (timeIdx < L && result == info[timeIdx].time) {
				dir = info[timeIdx].dir.equals("L") ? (dir + 3) % 4 : (dir + 1) % 4;
				timeIdx++;
			}

		}
	}

	public static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Info {
		int x;
		int y;
		int time;
		String dir;

		public Info(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Info(int time, String dir) {
			super();
			this.time = time;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Info [x=" + x + ", y=" + y + "]";
		}

	}
}// end of class