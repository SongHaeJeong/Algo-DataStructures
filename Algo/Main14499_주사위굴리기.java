package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14499_주사위굴리기 {
	private static int N;
	private static int M;
	private static int X;
	private static int Y;
	private static int K;
	private static int[][] map;
	private static int[] command;
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	private static int[][] dot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		command = new int[K];
		dot = new int[4][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			command[i] = Integer.parseInt(st.nextToken());
		}

		process();

	}// end of main

	public static void process() {

		for (int i = 0; i < command.length; i++) {
			int dir = command[i];
			// 주사위 굴림

			int nx = X + dx[dir];
			int ny = Y + dy[dir];
			if (isBoolean(nx, ny) && map[nx][ny] == 0) {
				changeDot(dir);
				map[nx][ny] = dot[3][1];

			}

			if (isBoolean(nx, ny) && map[nx][ny] != 0) {
				changeDot(dir);
				dot[3][1] = map[nx][ny];
				map[nx][ny] = 0;
			}
			X = nx;
			Y = ny;

			System.out.println(dot[1][1]);

		}

	}

	public static void changeDot(int dir) {
		if (dir == 1) { // 동쪽
			swap(1, 0, 1, 1);
			swap(1, 1, 1, 2);
			swap(1, 2, 3, 1);
			swap(3, 1, 1, 0);
		} else if (dir == 2) { // 서쪽
			swap(1, 0, 3, 1);
			swap(1, 1, 1, 0);
			swap(1, 2, 1, 1);
			swap(3, 1, 1, 2);
		} else if (dir == 3) { // 북쪽
			swap(0, 1, 1, 1);
			swap(1, 1, 2, 1);
			swap(2, 1, 3, 1);
			swap(3, 1, 0, 1);
		} else if (dir == 4) { // 남쪽
			swap(3, 1, 2, 1);
			swap(2, 1, 1, 1);
			swap(1, 1, 0, 1);
			swap(0, 1, 3, 1);
		}
	}

	public static void swap(int x, int y, int q, int p) {
		int temp = dot[q][p];
		dot[q][p] = dot[x][y];
		dot[x][y] = temp;
	}

	public static boolean isBoolean(int x, int y) {

		if (x >= 0 && x < N && y >= 0 && y < M)
			return true;

		return false;
	}
}// end of class
