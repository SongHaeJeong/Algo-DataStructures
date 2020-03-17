## Solution5653_줄기세포배양

>__풀이 방법__
>
>처음에 무한대라고 해서 list를 통해서 해결하려고 했으나, 번식 할 때 조건을 처리하기가 코드도 복잡해지고 시간이 터질거 같아서 배열을 생성함. 배열 크기 계산은 최대로 많이 생성 할 수 있을 때를 계산함. 예를 들어,  모든 줄기 세포 생명력이 1이고 K = 10 이라고 치면 1시간은 활성상태가 되고 1시간은 번식 함 => 번식하는데 2시간이 걸리니깐 여유분으로 N+K, M+K로 생성 시킴
>
>
>
>status에 따라 상태 변화를 표시함 1 : 비활성, 2 : 활성, 3 : 뒈짐
>
>process() 로직
>
>1.  활성 상태인거에 대해서 check() 함수 실행
>   - check() 함수는 기존의 줄기 세포가 존재하면 visited 함수에 의해서 continue; 
>   - 동시에 번식이 가능하면 크기 비교해서 집어넣음
>2. queue에서 하나씩 꺼내서 번식
>3. n.chage() 함수를 통해서 상태 변화
>
>

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5653_줄기세포배양 {
	private static int T, N, M, K;
	private static int[][] map;
	private static int[] dx = { -1, 0, 1, 0 }; //상하좌우 탐색을 위한 변수
	private static int[] dy = { 0, 1, 0, -1 };
	private static boolean[][] visited;
	private static Queue<Node> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int ans = 0;
			map = new int[K  + N][K  + M];
			visited = new boolean[K  + N][K  + M];
			queue = new LinkedList<Node>();
			for (int i = K / 2; i < K/2 + N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = K / 2; j < K/2 +M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) {
						visited[i][j] = true;
						// x, y, 상태 (1 : 비활성) , 활성상태가 되기 위한 시간, 죽는 시간
						queue.add(new Node(i, j, 1, map[i][j], map[i][j]));

					}
				}
			}
			process();
			ans = queue.size();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase
		System.out.println(sb.toString());

	}// end of main

	private static void process() {
		int time = 0;

		while (time++ < K) {
			int size = queue.size();

			
			for (Node n : queue) {
				if (n.status == 2) {
					check(n); // 동시에 번식이 가능하나 생명력이 가장 큰 세포 번식해주기 위해서 실행
					
				}
			}
			
			// 번식 실행
			for (int i = 0; i < size; i++) {
				Node n = queue.poll();
				if (n.status == 2) {
					for (int j = 0; j < dx.length; j++) {
						int nx = n.row + dx[j];
						int ny = n.column + dy[j];

						if (visited[nx][ny])
							continue;
						queue.add(new Node(nx, ny, 1, map[nx][ny], map[nx][ny]));
						visited[nx][ny] = true;
					}
				}

				n.chage(); // 번식 후 상태 변화
				if(n.status == 3) continue;
				queue.add(n);

			}

		}

	}// end of process();

	private static void check(Node n) {
		// TODO Auto-generated method stub
		for (int i = 0; i < dx.length; i++) {
			int nx = n.row + dx[i];
			int ny = n.column + dy[i];

			if (visited[nx][ny])
				continue;
			if (map[nx][ny] < n.life)
				map[nx][ny] = n.life;
		}

	}

	static class Node {
		int row, column, status, life, deathTime;

		public Node(int row, int column, int status, int life, int deathTime) {
			super();
			this.row = row;
			this.column = column;
			this.status = status;
			this.life = life;
			this.deathTime = deathTime;
		}
		
		// status 1 : 비활성 상태 , 2 : 활성 상태 , 3 : 죽음
		public void chage() {
			// TODO Auto-generated method stub
			switch (status) {
			case 1: // 비활성화 상태
				if (--deathTime == 0)
					status = 2;
				break;
			case 2: // 활성화 상태
				if (++deathTime == life)
					status = 3;
				break;
			}
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", status=" + status + ", life=" + life + ", deathTime="
					+ deathTime + "]";
		}

	}
}// end of class

```


