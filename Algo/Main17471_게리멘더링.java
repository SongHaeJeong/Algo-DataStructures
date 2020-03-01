package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
/*
 *  시험 당시 못풀었던 문제....
 *  처음에 팀을 조합으로 나눔
 *  무조건 2구역으로 나누어야되니깐 1개를 선택하는것부터 5까지만 설정함 
 *  
 *  구역을 나눈 후 연결되어있는지 확인작업 필요
 *  process() 검사하였고 flag 1,2로 나누어서 검사 처음에 나누어서 검사를 진행안했더니 
 *  한가지 구역에서 1이 되었을 때 모두 checked[idx] true로 만들어주기 때문에 나눠서 검사해야됨
 *  
 *  이후 구역을 두가지 나누었으면 Math.min 함수 사용해서 최소값 구함* 

 * 
 */
public class Main17471_게리멘더링 {
	private static int N, ans;
	private static int[][] area;
	private static int[] population;
	private static boolean[] visited;
	private static int[] set;
	private static int[] arr;
	private static ArrayList<Integer> aList;
	private static ArrayList<Integer> bList;
	private static boolean[] checked;

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

		// 구역 나눌떄쓰는거
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		/*
		 * for (int i = 0; i < area.length; i++) { for (int j = 0; j < area.length; j++)
		 * { System.out.print(area[i][j] + " "); } System.out.println(); }
		 */

		ans = Integer.MAX_VALUE;
		for (int i = 1; i <= N - 1; i++) {
			set = new int[i];
			combi(i, 0, 0);
		}

		if (ans == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(ans);

	}// end of main

	private static void combi(int count, int idx, int k) {
		if (idx == count) {
			check();

			return;
		}

		for (int i = k; i < arr.length; i++) {
			set[idx] = arr[i];
			visited[i] = true;
			combi(count, idx + 1, i + 1);
			visited[i] = false;
		}

	}

	// 두 구역으로 나누기
	private static void check() {
		// TODO Auto-generated method stub
		aList = new ArrayList<>();
		bList = new ArrayList<>();

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				bList.add(i);
			else
				aList.add(i);
		}

		checked = new boolean[N];
		//1구역 검사
		process(aList.get(0),0,aList.size(), 1);
		//2구역 검사
		process(bList.get(0),0,bList.size(), 2);
		
		for (int i = 0; i < checked.length; i++) {
			if(!checked[i]) return;
		}
		
		int aResult = 0;
		int bResult = 0;

		for (int i = 0; i < aList.size(); i++) {
			aResult += population[aList.get(i)];
		}
		for (int i = 0; i < bList.size(); i++) {
			bResult += population[bList.get(i)];
		}

		// 결과 구하기
		ans = Math.min(ans, Math.abs(aResult - bResult));

	}

	private static void process(int start, int idx, int size, int flag) {
		// TODO Auto-generated method stub
		checked[start] = true;
		if(size == 1) return;
		
		for (int i = 0; i < N; i++) {
			if(flag == 1) {
				if(area[start][i] == 1 && !checked[i] && visited[i]) {
					process(i, idx+1, size, 1);
				}		
				
			}else {
				if(area[start][i] == 1 && !checked[i] && !visited[i]) {
					process(i, idx+1, size, 2);
				}
			}
			
		}
			
	}

}// end of class
