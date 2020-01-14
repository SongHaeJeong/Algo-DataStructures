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
				System.out.println(dot[1][1]);
				X = nx;
				Y = ny;

			}else if (isBoolean(nx, ny) && map[nx][ny] != 0) {
				changeDot(dir);
				dot[3][1] = map[nx][ny];
				map[nx][ny] = 0;
				System.out.println(dot[1][1]);
				X = nx;
				Y = ny;
			}
		


		}

	}

	public static void changeDot(int dir) {
		if (dir == 1) { // 동쪽
			int dotNum = dot[1][2];
			for (int i = dot[0].length-1; i >0; i--) {
				dot[1][i] = dot[1][i-1];
			}
			dot[1][0] = dotNum;
			
			int temp = dot[1][0];
			dot[1][0] = dot[3][1];
			dot[3][1] = temp;
			
		} else if (dir == 2) { // 서쪽			
			int dotNum = dot[1][0];
			for (int i = 0; i < dot[0].length - 1; i++) {
				dot[1][i] = dot[1][i + 1];
			}
			dot[1][2] = dotNum;
			
			int temp = dot[1][2];
			dot[1][2] = dot[3][1];
			dot[3][1] = temp;
			

		} else if (dir == 3) { // 북쪽
			int dotNum = dot[3][1];
			for (int i = dot.length - 1; i > 0; i--) {
				dot[i][1] = dot[i - 1][1];
			}
			dot[0][1] = dotNum;
		} else if (dir == 4) { // 남쪽
			int dotNum = dot[0][1];
			for (int i = 0; i < dot.length - 1; i++) {
				dot[i][1] = dot[i + 1][1];
			}

			dot[3][1] = dotNum;

		}
	}

	public static boolean isBoolean(int x, int y) {

		if (x >= 0 && x < N && y >= 0 && y < M)
			return true;

		return false;
	}
}// end of class
