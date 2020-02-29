package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17471_게리멘더링 {
	private static int N, ans;
	private static int[][] area;
	private static int[] population;
	private static boolean[] visited;
	private static int[] set;
	private static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		population = new int[N];
		visited = new boolean[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < population.length; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}

		area = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			for (int j = 0; j < t; j++) {
				int connect = Integer.parseInt(st.nextToken());
				area[i][connect - 1] = 1;
				area[connect - 1][i] = 1;

			}
		}
		
		//구역 나눌떄쓰는거
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		

		for (int i = 1; i <= N-1; i++) {
			set = new int[i];
			combi(i, 0 , 0);
		}

	}// end of main
	private static void combi(int count, int idx , int k) {
		if(idx == count) {
			boolean flag = check();
			if(flag) process();
			return;
		}
		
		for (int i = k; i < arr.length; i++) {
			set[idx] = arr[i];
			visited[i] = true;
			combi(count , idx +1, i+1);
			visited[i] = false;
		}
		
		
	}
	//두개로 나누어졌다면 인구차이 최소 구하기
	private static void process() {
	
		
	}
	
	//구역이 두개로 나누워졌는지 안했는지 확인
	private static boolean check() {
		// TODO Auto-generated method stub
		//A 구역
		boolean aCheck = false;
		int startIdx = set[0];
		int setIdx = 0;
		while(setIdx != set.length) {
			
			if(set.length == 1) {
				aCheck = true;
				break;
			}
			
			
			for (int i = 0; i < N; i++) {
				if(area[startIdx][i] == 1) {
					if(visited[i]) {
						startIdx = i;
						aCheck = true;
						break;
					}
				}
			}			
			setIdx++;
		}
		
		if(!aCheck) return false;
		
		
			
		//B 구역
		
		return false;
	}

}// end of class
