package Algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main15686_치킨배달 {
	private static int N, M, result;
	private static int[][] map;
	private static Pair[] set;
	private static ArrayList<Pair> chicken;
	private static ArrayList<Pair> house;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		set = new Pair[M];
		result = Integer.MAX_VALUE;
		chicken = new ArrayList<Pair>();
		house = new ArrayList<Pair>();
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2)
					chicken.add(new Pair(i, j));
				if(map[i][j] == 1) house.add(new Pair(i,j));
			}
		}

		selectChicken(0, 0);
		System.out.println(result);

	}// end of main

	private static void selectChicken(int idx, int k) {
		if(idx == M) {
			int value = process();
			result = result > value ? value : result; 
			return;
		}
		
		for (int i = k; i < chicken.size(); i++) {
			set[idx] = new Pair(chicken.get(i).row, chicken.get(i).column);
			selectChicken(idx+1, i+1);
		}
	}
	

	private static int process() {
		int sum = 0 ;
	
		for (int i = 0; i < house.size(); i++) {
			int value = Integer.MAX_VALUE;	
			Pair p = house.get(i);
			for (int j = 0; j < set.length; j++) {
				int chickenLen = Math.abs(p.row - set[j].row) + Math.abs(p.column - set[j].column);
				value = value > chickenLen ? chickenLen : value;
				
				
			}
			sum += value;
			
			
		}
		
		return sum;
	}


	static class Pair {
		int row, column;

		public Pair(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		@Override
		public String toString() {
			return "Pair [row=" + row + ", column=" + column + "]";
		}

	}
}// end of class
