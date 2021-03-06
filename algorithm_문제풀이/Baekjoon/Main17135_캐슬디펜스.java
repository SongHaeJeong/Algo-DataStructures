package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 *  핵심은 Collections.sort 하는 부분이다.
 *  sort 한번으로 가장 왼쪽에 있는 적을 공격 할 수 있고
 *  int len을 통해 동시에 공격하는 부분도 해결했음.* 
 * 
 */

public class Main17135_캐슬디펜스 {
	private static int N, M, D, result;
	private static int[][] map;
	private static int[] set;
	private static int[] arr;
	private static ArrayList<Node> list;
	private static ArrayList<Node> tempList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					list.add(new Node(i, j));
			}
		}

		arr = new int[M];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		set = new int[3]; // 궁수위치 3명 뽑기
		result = Integer.MIN_VALUE;
		combi(0, 3, 0);
		System.out.println(result);
	}// end of main

	private static void combi(int idx, int count, int k) {
		// TODO Auto-generated method stub
		if (idx == count) {
			tempList = new ArrayList<Node>();
			for (int i = 0; i < list.size(); i++) {
				tempList.add(new Node(list.get(i).row, list.get(i).column));
			}
			process();
			return;
		}

		for (int i = k; i < arr.length; i++) {
			set[idx] = arr[i];
			combi(idx + 1, count, i + 1);
		}
	}

	private static void process() {
		// TODO Auto-generated method stub
		int[][] temp = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, M);
		}

		Collections.sort(tempList);
		// 궁수로 잡기
		int count = 0;
		while (true) {

			if (tempList.size() == 0)
				break;

			ArrayList<Node> deleted = new ArrayList<Node>();
			for (int i = 0; i < set.length; i++) {
				int setRow = N;
				int setColumn = set[i];
				//가장 가까운 곳 집어넣기
				int len = Integer.MAX_VALUE;
				for (Node n : tempList) {
					len = len > Math.abs(n.row - setRow) + Math.abs(n.column - setColumn) ? Math.abs(n.row - setRow) + Math.abs(n.column - setColumn) : len;					
				}
				for (Node n : tempList) {
					if (len == Math.abs(n.row - setRow) + Math.abs(n.column - setColumn) && len <= D) {
						deleted.add(n);
						break;
					}
				}
				

			}
			
			int preCount = tempList.size();
			// 죽인 갯수
			for (Node d : deleted) {				
				tempList.remove(d);
			}
			
			count += preCount - tempList.size();
			
			
			deleted = new ArrayList<Node>();
			//다 죽이고 게임이 끝남 --> 적이 아래로 한칸씩 옮김
			for (Node li : tempList) {
				int nRow = li.row +1;
				if(nRow >= N) deleted.add(li);
				else {
					li.row = nRow;					
				}				
			}
			
			for (Node d : deleted) {
				tempList.remove(d);
			}
			

		}

		result = Math.max(result, count);

	}

	static class Node implements Comparable<Node> {
		int row;
		int column;

		public Node(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + "]";
		}

		@Override
		public int compareTo(Node n) {
			if (row == n.row) {
				return 0;
			}else if(row != n.row)
				return column - n.column;
			return -1;
		}

	}
}// end of class
