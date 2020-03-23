## Solution2117_홈 방범 서비스

>시뮬레이션 문제이다.
>
>조심해야될 점이 while(true) 문에서 종료 조건을 집에서 낼 수 있는 비용이 운영비용보다 적으면 종료시키는 것이 아니라, 전체 낼 수 있는 비용보다 운영비용이 커질 때 종료 시켜야된다.
>
>

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2117_홈방법서비스 {
	private static int N, M, ans;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int maxValue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 집의 크기
			M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용

			map = new int[N][N]; // 집의 정보 나타내는 2차원 배열
			maxValue = 0; // 맵에서 최대로 낼 수 있는 서비스 제공받는 집들을 통해 얻는 수익
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 집 정보 입력 받기
					if(map[i][j] == 1) maxValue += M;
				}
			}

			ans = 0; // 정답 출력

			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					check(i, j);
				}
			}

			// 각 테스트케이스별로 sb.append 추가한 후에 마지막에 한꺼번에 출력
			// System.out.println()보다 빠름
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		}
		System.out.println(sb.toString());

	}// end of main

	private static void check(int i, int j) {
		// TODO Auto-generated method stub
		int k = 0;

		while (true) {
			Queue<Node> queue = new LinkedList<Node>(); // 탐색하기 위해 queue 생성
			boolean[][] visited = new boolean[N][N]; //중복 방지하기 위해 visited 생성

			visited[i][j] = true; //초기 설정
			queue.add(new Node(i, j, 1)); //초기 설정
 
			k++; // k 증가
			int count = 0; // 집의 갯수 파악
			int fee = 0; // 서비스 제공 받는 집들을 통해 얻는 수익
			int operatorValue = k * k + (k - 1) * (k - 1); // 운영 비용

			while (!queue.isEmpty()) {

				Node n = queue.poll();
				if (map[n.row][n.column] == 1) {
					fee += M; 
					count++;
				}
				
				
				// 4방을 탐색하지만 len < k보다 작을 때면 탐색
				for (int l = 0; l < dx.length; l++) {
					int nx = n.row + dx[l];
					int ny = n.column + dy[l];

					if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;
					if(n.len < k) {
						visited[nx][ny] = true;
						queue.add(new Node(nx, ny, n.len+1));
					}
					
				}

			}

			if (fee >= operatorValue) {
				ans = ans < count ? count : ans;
			}
			
			// 종료 조건은 fee < operationValue 일때가아닌 운영비용이 최대로 서비스 비용을 낼 수 있는 비용보다 클 때 종료 시킴
			if(maxValue < operatorValue) break;
		}

	}

	static class Node {
		int row, column, len;

		public Node(int row, int column, int len) {
			super();
			this.row = row;
			this.column = column;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", len=" + len + "]";
		}

		

	}

}// end of class
```

