package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_±∏ΩΩ≈ª√‚2 {
	private static char[][] map;
	private static int N, M, rx, ry, bx, by;
	private static Queue<Info> balls;
	private static boolean[][][][] visited;
	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		balls = new LinkedList<>();
		visited = new boolean[N][M][N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'B') {
					bx = i;
					by = j;

				}

				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				}

			}
		}
		balls.add(new Info(bx, by, rx, ry, 0));
		visited[bx][by][rx][ry] = true;
		BFS();

	}

	private static void BFS() {
		// TODO Auto-generated method stub
		while (!balls.isEmpty()) {
			Info in = balls.poll();
			if(in.count > 10) {
				System.out.println("-1");
				return;
			}
			
			if(map[in.rx][in.ry]== 'O' && map[in.bx][in.by] != 'O' ) {
				System.out.println(in.count);
				return;
			}
			
			for (int i = 0; i < dx.length; i++) {
				int nrx = in.rx + dx[i];
				int nry = in.ry + dy[i];				
				int nbx = in.bx + dx[i];
				int nby = in.by + dy[i];
				
				if(check(nrx,nry, nbx, nby) && )
			}
		}
	}
	
	static boolean inRange(int rx, int ry, int bx, int by) {
		if(rx>=0 && ry >=0 && rx < N && ry < M && bx >=0 && by>=0 && bx< N && by < M) {
			return true;
		}else {
			return false;
		}
	}

	static class Info {
		int rx;
		int ry;
		int bx;
		int by;
		int count;

		public Info(int rx, int ry, int bx, int by, int count) {
			super();
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Info [rx=" + rx + ", ry=" + ry + ", bx=" + bx + ", by=" + by + ", count=" + count + "]";
		}

	}
}
