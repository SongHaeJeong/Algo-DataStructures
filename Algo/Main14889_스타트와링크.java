package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14889_스타트와링크 {
	private static int N, startNum, linkNum;
	private static int[][] map;
	private static int[] linkTeam;
	private static int[] startTeam;
	private static int[] teams;
	static int[] setLink;
	static int[] setStart;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		teams = new int[N];
		linkTeam = new int[N/2];		
		startNum = 0;
		linkNum = 0;
		for (int i = 0; i < teams.length; i++) {
			teams[i] = i;
		}
		result = Integer.MAX_VALUE;
		combination(0, 0);
		System.out.println(result);

	}// end of main

	public static void combination(int k, int idx) {
		if (idx == N / 2) {
			boolean[] visited = new boolean[N];
			for (int i = 0; i < linkTeam.length; i++) {
				visited[linkTeam[i]] = true;
			}
			startTeam = new int[N/2];
			int startIdx = 0;
			for (int i = 0; i < visited.length; i++) {
				if(!visited[i]) {
					startTeam[startIdx] = i;
					startIdx++;
				}
			}
			
			
			setLink = new int[2];
			setStart = new int[2];
			teamCombination(0, 0); // 같은 팀에서 2명씩 뽑아내는거
			result = result > Math.abs(startNum - linkNum) ? Math.abs(startNum - linkNum) : result;
			startNum = 0;
			linkNum = 0;
			return;

		}

		for (int i = k; i < teams.length; i++) {
			linkTeam[idx] = teams[i];
			combination(i + 1, idx + 1);
		}
	}

	
	
	private static void teamCombination(int k, int idx) {
		if(idx == 2) {
			reslove();
			return;
		}
		
		for (int i = k; i < linkTeam.length; i++) {
			setLink[idx] = linkTeam[i];
			setStart[idx] = startTeam[i];
			teamCombination(i+1, idx +1);
		}
	}
	
	public static void reslove() {
		startNum += map[setStart[0]][setStart[1]] + map[setStart[1]][setStart[0]];
		linkNum += map[setLink[0]][setLink[1]] + map[setLink[1]][setLink[0]];
	}

}// end of class
