package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 
 *  4번타자는 1번으로 고정 :
 *  temp[] 함수를 둬서 1번을 제외한 2~9번까지 순열 돌려서
 *  playerIdx[] 함수에 4번 idx에는 1번을 추가해서 배열 새로 생성
 *  --> 이 후 조건에 맞게 시뮬레이션 돌림 
 * 
 * 
 */

public class Main17281_야구 {
	private static int result, N;
	private static int[] playerIdx;
	private static int[][] play;
	private static boolean[] visited;
	private static int[] temp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		playerIdx = new int[9];
		play = new int[N][9];
		visited = new boolean[8];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				play[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//4번타자의 자리 제외하고 순열
		temp = new int[8];
		int tempIdx=  0;
		for (int i = 1; i < playerIdx.length; i++) {
			temp[tempIdx++] = i;
		}
		result = Integer.MIN_VALUE;
		per(0);// 플레이어 순서 정해주기
		System.out.println(result);
	}// end of main

	static int[] set = new int[8];

	private static void per(int idx) {
		if (idx == 8) {
			
			//4번타자 고정
			int tempIdx =0;
			for (int i = 0; i < playerIdx.length; i++) {
				if(i == 3) playerIdx[3] = 0;
				else {
					playerIdx[i] = set[tempIdx++]; 
				}
			}
			
			process();
			return;
		}

		for (int i = 0; i < temp.length; i++) {
			if (!visited[i]) {
				set[idx] = temp[i];
				visited[i] = true;
				per(idx + 1);
				visited[i] = false;
			}
		}
	}

	private static void process() {
	
		// 주자 플레이
		int playResult = 0;
		int ining = 0;
		int idx = 0;
		while (true) {
			int outCount = 0;
			int[] base = new int[5];
			for (int i = 0; i < base.length; i++) {
				base[i] = -1;
			}
			
			while (outCount != 3) {
				
				int playing = play[ining][playerIdx[idx]];
				base[0] = playerIdx[idx];
				if (playing == 0) {
					base[0] = -1;
					outCount++;
					
				} else if (playing == 1) {
					for (int i = base.length-1; i >0 ; i--) {
						base[i] = base[i-1];						
					}
					base[0] = -1;
					
					if(base[4] >= 0) {
						playResult++;
						base[4] = -1;
					}
						
					
				} else if (playing == 2) {
					
					for (int i = 0; i < 2; i++) {
						for (int j = base.length-1; j >0 ; j--) {
							base[j] = base[j-1];						
						}
						base[0] = -1;
						
						if(base[4] >= 0) {
							playResult++;
							base[4] = -1;
						}
					}
					
					
					
				} else if (playing == 3) {
					
					for (int i = 0; i < 3; i++) {
						for (int j = base.length-1; j >0 ; j--) {
							base[j] = base[j-1];			
						}
						base[0] = -1;
						
						if(base[4] >= 0) {
							playResult++;
							base[4] = -1;
						}
					}
				} else {
					
					int memberCount =0;
					for (int i = 0; i < base.length; i++) {
						if(base[i] >=0) {
							memberCount++;
							base[i] = -1;
						}
						
					}
					
					playResult+= memberCount;
					
				}
				
				idx = (idx+1) % playerIdx.length;
				
			}
			
			ining++;
			if(ining == N) break;

		}
		
		result = result < playResult ? playResult : result;

	}

}// end of class