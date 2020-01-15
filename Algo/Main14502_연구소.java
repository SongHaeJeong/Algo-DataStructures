package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14502_¿¬±¸¼Ò {
	private static int N;
	private static int M;
	private static int[][] map;
	private static Queue<Pair> queue;
	private static ArrayList<Pair> virus;
	static Pair[] walls;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];
		walls = new Pair[3];
		virus = new ArrayList<Pair>();
		
		result = Integer.MIN_VALUE;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) virus.add(new Pair(i,j));
			}
		}
		for (int i = 0; i < N + 2; i++) {
			map[i][0] = -1;
			map[i][M + 1] = -1;
			
		}

		for (int i = 0; i < M + 2; i++) {
			map[0][i] = -1;
			map[N + 1][i] = -1;
		}

		
		combination(0, 0);
		System.out.println(result);

	}// end of main
	static void combination(int idx, int k) {
		if(idx == 3) {
			process();
//			for (int i = 0; i < walls.length; i++) {
//				System.out.print(walls[i]+ " ");
//			}
//			System.out.println();
			return;
		}
		for (int i = k; i < virus.size(); i++) {
			walls[idx] = new Pair(virus.get(i).x , virus.get(i).y);
			combination(idx+1, i+1);
		}
	}

	private static void process() {
		queue = new LinkedList<Pair>();
		int[][] copyMap = new int[N+2][M+2];
		for (int i = 0; i < N+2; i++) {
			System.arraycopy(map[i], 0, copyMap[i], 0, M+2);
		}
		
		for (int i = 0; i < walls.length; i++) {
			copyMap[walls[i].x][walls[i].y] = 1; 
			
		}
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				if(copyMap[i][j] == 2) {
					queue.add(new Pair(i,j));
				}
				
			}
		}
		
		
		while(!queue.isEmpty()) {
			Pair p = queue.poll();
			
			for (int i = 0; i < dx.length; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(copyMap[nx][ny] == -1 || copyMap[nx][ny] == 1 || copyMap[nx][ny] == 2) continue;
				
				if(copyMap[nx][ny] == 0) {
					copyMap[nx][ny] = 2;
					queue.add(new Pair(nx, ny));
				}
			}
		}
		
		int count = 0;
		for (int i = 1; i < N+1; i++) {
			for (int j = 1; j < M+1; j++) {
				if(copyMap[i][j] == 0) {
					count++;
				}
			}
		}
		
		result = result < count ? count : result; 
		
		
		
		
	}
	static void print() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + "]";
		}
		
		
	}
}// end of class
