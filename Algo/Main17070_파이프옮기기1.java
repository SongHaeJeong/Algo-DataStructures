package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * BFS로 처음에 풀었는데 시간 초과가 떠서 DFS로 풀었다.
 * BFS로 풀 때, 중복처리를 잘 못해줘서 그런것 같다....
 * 
 * 
 */
public class Main17070_파이프옮기기1 {
	private static int N, result;
	private static int[][] map;
	private static int[] dx = {0,1,1};
	private static int[] dy = {1,0,1};
	
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

		dfs(1,2,0);
		System.out.println(result);

	}// end of main

	private static void dfs(int row, int column, int dir) {
		// TODO Auto-generated method stub
		if(row == N && column == N) {
			result++;
			return;
		}
		
		int[] Dir = getDir(dir);
		
		for (int i = 0; i < Dir.length; i++) {
			
			int nx = row + dx[Dir[i]];
			int ny = column + dy[Dir[i]];
			
			if(nx > N || ny > N || map[nx][ny] != 0) continue;
			if(Dir[i] == 2 && (map[row+1][column] != 0 || map[row][column+1] != 0)) continue;
			dfs(nx, ny, Dir[i]);
			
		}
		
		
	}

	private static int[] getDir(int dir) {
		// TODO Auto-generated method stub
		if(dir == 0) {
			int[] temp = {0,2};
			return temp;
		}else if(dir == 1) {
			int[] temp = {1,2};
			return temp;
		}else if(dir ==2) {
			int[] temp = {0,1,2};
			return temp;
		}
		return null;
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
