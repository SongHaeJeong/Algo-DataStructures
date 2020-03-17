package Algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main17837_새로운게임2 {
	private static int N, K, result;
	private static int[] dx = { 0, 0, 0, -1, 1 };
	private static int[] dy = { 0, 1, -1, 0, 0 };
	private static ArrayList<Pair> list;
	private static int[][] map;
	private static ArrayList<Integer>[][] horseMap;
	private static Pair[] horse;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;

		map = new int[N][N];
		horseMap = new ArrayList[N][N];
		horse = new Pair[K + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				horseMap[i][j] = new ArrayList<Integer>();
			}
		}

		int iy, ix, idir;
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			iy = Integer.parseInt(st.nextToken()) - 1;
			ix = Integer.parseInt(st.nextToken()) - 1;
			idir = Integer.parseInt(st.nextToken());
			horse[i] = new Pair(iy, ix, idir);
			horseMap[iy][ix].add(i);
		}
		
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		
		while(result <= 1000) {
			result++;
			
			for (int i = 1; i <= K; i++) {
				Pair h = horse[i];
			
				
				int nx = h.row + dx[h.dir];
				int ny = h.column + dy[h.dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 2) {
					if(h.dir == 1) h.dir = 2;
					else if(h.dir == 2) h.dir = 1;
					else if(h.dir == 3) h.dir = 4;
					else if(h.dir == 4) h.dir = 3;
					
					nx = h.row + dx[h.dir];
					ny = h.column + dy[h.dir];					
				}
				
				if(nx >=0 && ny>=0 && nx < N && ny < N && map[nx][ny] != 2) {
					if(map[nx][ny] == 0) {
						int rm = horseMap[h.row][h.column].remove(horseMap[h.row][h.column].size()-1);
						while(rm != i) {
							tempList.add(rm);
							rm = horseMap[h.row][h.column].remove(horseMap[h.row][h.column].size()-1);
						}
						tempList.add(rm);
						
						while(!tempList.isEmpty()) {
							rm = tempList.remove(tempList.size()-1);
							horse[rm].row = nx;
							horse[rm].column = ny;
							horseMap[nx][ny].add(rm);
						}
						
					}else {
						
						int rm = horseMap[h.row][h.column].remove(horseMap[h.row][h.column].size()-1);
						while(rm != i) {
							horse[rm].row = nx;
							horse[rm].column = ny;
							horseMap[nx][ny].add(rm);
							rm=horseMap[h.row][h.column].remove(horseMap[h.row][h.column].size()-1);
						}
						horse[rm].row = nx;
						horse[rm].column = ny;
						horseMap[nx][ny].add(rm);						
					}
					
					if(horseMap[nx][ny].size() >=4) {
						System.out.println(result);
						return;
					}
					
					
				}
				
				
				
			}
			
			
			
		}
		System.out.println("-1");
		return;
		
	}// end of main
		// 맵 정보 : 0 흰색 , 1 빨간색, 2 파란색

	static class Pair {
		int row;
		int column;
		int dir;

		public Pair(int row, int column, int dir) {
			super();
			this.row = row;
			this.column = column;
			this.dir = dir;
		}

		@Override
		public String toString() {
			return "Pair [row=" + row + ", column=" + column + ", dir=" + dir + "]";
		}

	}
}// end of class
