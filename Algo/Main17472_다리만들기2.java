package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main17472_다리만들기2 {
	private static int N, M;
	private static int[][] map;
	private static boolean[][] countNation;
	private static int[] dx = { 0, -1, 0, 1 };
	private static int[] dy = { 1, 0, -1, 0 };
	private static ArrayList<Connect> list;
	private static int[][] nation;
	private static Connect[] set;
	private static boolean[] allConnect;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		countNation = new boolean[N][M];
		int count = 1;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 1 && !countNation[i][j]) {
					countBFS(i, j, count++);
				}
			}
		}

		// 연결 가능한 곳 찾기
		list = new ArrayList<Connect>();
		countNation = new boolean[N][M]; // 1번 섬에서 2번 섬 연결과 2번 섬 1번 섬 연결 같다고 판단
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] != 0 && !countNation[i][j]) {
					isPossible(i, j);
				}
			}
		}

		// 연결 가능한 지역들 다 찾고 이제 최소값 찾기

		result = Integer.MAX_VALUE;
		nation = new int[count - 1][count - 1];
		set = new Connect[nation.length - 1];
		combi(0, 0);

		if (result == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(result);

	}// end of main

	// 사다리 연결할 수 있는 곳 중에서 조합
	private static void combi(int idx, int k) {
		// TODO Auto-generated method stub
		if (idx == set.length) {
			process(); // 섬이 모두 이어졌는지 확인
			return;
		}

		for (int i = k; i < list.size(); i++) {
			set[idx] = new Connect(list.get(i).preNation, list.get(i).nextNation, list.get(i).ladderCount);
			combi(idx + 1, i + 1);
		}
	}

	// 다리 연결
	private static void process() {
		// TODO Auto-generated method stub
		allConnect = new boolean[nation.length];
		for (int i = 0; i < set.length; i++) {
			System.out.println(set[i].preNation + " " + set[i].nextNation + " " + set[i].ladderCount);
		}
		System.out.println();

		int startIdx = set[0].preNation - 1;
		dfs(startIdx, 0, 0);

		for (int i = 0; i < allConnect.length; i++) {
			if (!allConnect[i])
				return;
		}

		int ans = 0;
		for (int i = 0; i < set.length; i++) {
			ans += set[i].ladderCount;
		}

		result = Math.min(result, ans);
		System.out.println(result);

	}

	private static void dfs(int startIdx, int idx, int k) {

		allConnect[startIdx] = true;

		if (idx == set.length) {
			return;
		}

		for (int i = k; i < set.length; i++) {

		}

	}

	// 연결가능한 섬 찾기
	private static void isPossible(int row, int column) {

		for (int i = 0; i < dx.length; i++) {
			int countLadder = 0;
			int LadderIdx = 1;
			while (true) {
				int nRow = row + dx[i] * LadderIdx;
				int nColumn = column + dy[i] * LadderIdx;

				if (nRow >= 0 && nColumn >= 0 && nRow < N && nColumn < M && map[nRow][nColumn] == 0) {
					LadderIdx++;
					countLadder++;
				} else {
					if (nRow >= 0 && nColumn >= 0 && nRow < N && nColumn < M && countLadder >= 2
							&& map[nRow][nColumn] != 0) {
						list.add(new Connect(map[row][column], map[nRow][nColumn], countLadder));
						countNation[nRow][nColumn] = true;
						break;
					} else
						break;

				}

			}

		}

	}

	// 섬의 번호 체크
	private static void countBFS(int row, int column, int count) {
		// TODO Auto-generated method stub
		Queue<Node> queue = new LinkedList<>();
		map[row][column] = count;
		queue.add(new Node(row, column));
		countNation[row][column] = true;

		while (!queue.isEmpty()) {
			Node n = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int nx = n.row + dx[i];
				int ny = n.column + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || countNation[nx][ny] || map[nx][ny] != 1)
					continue;

				countNation[nx][ny] = true;
				map[nx][ny] = count;
				queue.add(new Node(nx, ny));

			}
		}
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	static class Connect {
		int preNation;
		int nextNation;
		int ladderCount;

		public Connect(int preNation, int nextNation, int ladderCount) {
			super();
			this.preNation = preNation;
			this.nextNation = nextNation;
			this.ladderCount = ladderCount;
		}

		@Override
		public String toString() {
			return "Connect [preNation=" + preNation + ", nextNation=" + nextNation + ", ladderCount=" + ladderCount
					+ "]";
		}

	}

	static class Node {
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

	}
}// end of class
