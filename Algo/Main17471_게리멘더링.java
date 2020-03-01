package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

		// 구역 나눌떄쓰는거
		arr = new int[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}

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

	// 구역이 두개로 나누워졌는지 안했는지 확인
	private static void check() {
		// TODO Auto-generated method stub
		ArrayList<Integer> aList = new ArrayList<>();
		ArrayList<Integer> bList = new ArrayList<>();

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				bList.add(i);
			else
				aList.add(i);
		}

		// A 구역
		int setIdx = 0;
		int startIdx = aList.get(0);

		for (int i = 1; i < aList.size(); i++) {

			if (!visited[aList.get(i)] || area[startIdx][aList.get(i)] != 1) {
				return;
			} else
				startIdx = aList.get(i);
		}

		boolean[] flag = new boolean[N];
		for (int i = 0; i < bList.size(); i++) {
			flag[bList.get(i)] = true;
		}

		setIdx = 0;
		startIdx = bList.get(0);

		for (int i = 1; i < bList.size(); i++) {
			int b = area[startIdx][i];
			if (!flag[bList.get(i)] || area[startIdx][bList.get(i)] != 1) {
				return;
			} else
				startIdx = bList.get(i);
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

}// end of class
