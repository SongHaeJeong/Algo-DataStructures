package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16637_괄호추가하기 {
	private static int result, N;
	private static int[] playerIdx;
	private static int[][] play;
	private static boolean[] visited;
	private static int[] set;
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	playerIdx = new int[9];    	
    	play = new int[N][9];
    	visited = new boolean[9];
    	
    	for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				play[i][j] = Integer.parseInt(st.nextToken());
			}
		}
    	
    	for (int i = 0; i < playerIdx.length; i++) {		
    		playerIdx[i] = i;
		}
    	
    	//4번타자는 항상 정해져있음
    	playerIdx[0] = 3;
    	playerIdx[3] = 0;
    	visited[3] = true;
    	set = new int[10];
    	
    	per(0);// 플레이어 순서 정해주기
    }//end of main
	private static void per(int idx) {
		if(idx == 10) {
			return;
		}
		
		for (int i = 0; i < playerIdx.length; i++) {
			if(!visited[i]) {
				set[idx] = playerIdx[i];
				visited[i] = true;
				per(idx+1);
				visited[i] = false;
			}
		}
	}
}//end of class