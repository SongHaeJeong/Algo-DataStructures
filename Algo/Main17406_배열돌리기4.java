package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 *  순열로 순서를 정하고 이 후 배열돌리기 실행
 *  순열을 한 이유는 (3,4,2) , (4,2,1) 최소값과 (4,2,1), (3,4,2) 값이 다르기 떄문
 *  이 후 조건에 맞게 코드를 구현 
 * 
 */
public class Main17406_배열돌리기4 {
	private static int N, M, K;
	private static int[][] map;
	private static Node[] set;
	private static ArrayList<Node> list;
	private static boolean[] visited;
	private static int[][] temp;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N + 1][M + 1];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 순열을 짜기 위해서 set visited 생성
		set = new Node[K];
		visited = new boolean[K];

		list = new ArrayList<Node>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		result = Integer.MAX_VALUE;
		permu(0);
		System.out.println(result);
	}// end of main

	// 순열
	private static void permu(int idx) {
		if (idx == K) {
			process(); // 배열 돌리기
			check();// 최소값 찾기
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (!visited[i]) {
				set[idx] = new Node(list.get(i).row, list.get(i).column, list.get(i).s);
				visited[i] = true;
				permu(idx + 1);
				visited[i] = false;
			}
		}
	}

	private static void check() {
		// TODO Auto-generated method stub
		for (int i = 1; i < temp.length; i++) {
			int value = 0;
			for (int j = 1; j < temp[i].length; j++) {
				value += temp[i][j];
			}
			
			result = Math.min(result, value);
		}
		
		
		
		
	}

	// 배열돌리기 구현
	private static void process() {
		temp = new int[map.length][map[0].length];
		for (int i = 0; i < N + 1; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, temp[0].length);
		}

		for (int i = 0; i < set.length; i++) {
			Node n = set[i];
			int row = n.row;
			int column = n.column;
			int s = n.s;
			int preRow = row - s;
			int preColumn = column - s;
			int nextRow = row + s;
			int nextColumn = column + s;

			// 배열 돌리기
			while (preRow != nextRow && preColumn != nextColumn) {

				int memoFirst = temp[preRow][preColumn];

				for (int j = preRow; j < nextRow; j++) {
					temp[j][preColumn] = temp[j + 1][preColumn];
				}

				for (int j = preColumn; j < nextColumn; j++) {
					temp[nextRow][j] = temp[nextRow][j + 1];
				}

				for (int j = nextRow; j > preRow; j--) {
					temp[j][nextColumn] = temp[j - 1][nextColumn];
				}

				for (int j = nextColumn; j > preColumn; j--) {
					temp[preRow][j] = temp[preRow][j - 1];
				}

				temp[preRow][preColumn + 1] = memoFirst;

				preRow++;
				preColumn++;
				nextRow--;
				nextColumn--;

			}

		}
	}

	static class Node {
		int row;
		int column;
		int s;

		public Node(int row, int column, int s) {
			super();
			this.row = row;
			this.column = column;
			this.s = s;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", s=" + s + "]";
		}

	}
}// end of class
