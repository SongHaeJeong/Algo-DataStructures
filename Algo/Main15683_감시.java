package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main15683_감시 {
	private static int N, L, R;
	private static int[][] map;
	private static boolean flag;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };
	private static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int result = 0;
		while (result <= 2000) {
			flag = false;
			visited = new boolean[N][N];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(!visited[i][j]) {
						
						open(i, j);
					}
				}
			}

			if (!flag)
				break;

			result++;
		}

		System.out.println(result);

	}// end of main

	public static void open(int row, int column) {
		ArrayList<Pair> list = new ArrayList<Pair>(); // 문에 추가
		Queue<Pair> q = new LinkedList<>();
		list.add(new Pair(row, column, map[row][column]));
		q.add(new Pair(row, column, map[row][column]));
		visited[row][column] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N 
						|| visited[nx][ny])
					continue;
				else {
					if (L <= Math.abs(p.value - map[nx][ny]) && Math.abs(p.value - map[nx][ny]) <= R) {
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny, map[nx][ny]));
						list.add(new Pair(nx, ny, map[nx][ny]));
						flag = true;
					}

				}
			}

		}
		
		int number = 0;
		for (Pair li : list) {
			number += li.value;
		}
		
		for (Pair li : list) {
			map[li.x][li.y]= number/list.size(); 
		}
		
		
	}

	static class Pair {
		int x;
		int y;
		int value;

		public Pair(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", value=" + value + "]";
		}

	}
}// end of class
