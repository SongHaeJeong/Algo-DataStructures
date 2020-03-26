## Solution2105_디저트카페

>__풀이 방법__
>
>1. 어느 한지점에서 대각선으로 출발하여 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
>   - 위의 조건에 대해서 (우하, 좌하, 좌상, 우상) 의 방향으로 고정시켰다. 왜냐하면 우하에서 시작해서 사각형을 만드는 것이나, 좌하에서 시작해서 만드는 것을 동일하다고 생각함
>2. 문제에서 디저트의 값은 1~100이라고 명시 , 방문 표시에 관해서는 visited 배열을 통해서 해결
>3. 이 후, 이동하는 로직에 대해서는 DFS로 처리 
>   - DFS의 종료 조건 : dir == 4 이면 종료
>   - 갈 수 있는 방향으로 계속 가다가 갈수없게 되면 방향 틀어줌





```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution2105_디저트카페 {
	private static int N, ans, startRow, startColumn;
	private static int[][] map;
	private static int[] dx = { 1, 1, -1, -1 };
	private static int[] dy = { 1, -1, -1, 1 };
	private static boolean[][] visited;
	private static boolean[] nVisited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N]; // 디저트에 대한 정보
			for (int i = 0; i < map.length; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			visited = new boolean[N][N]; // 방문 체크
			nVisited = new boolean[101]; // 숫자 1~100 중복 체크

			ans = Integer.MIN_VALUE;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					startRow = i;
					startColumn = j;
					visited[i][j] = true;
					nVisited[map[i][j]] = true;
					dfs(i, j, 1, 0);					
					visited[i][j] = false;
					nVisited[map[i][j]] = false;
				}
			}

			if (ans < 4)
				ans = -1;
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		}
		System.out.println(sb.toString());

	}// end of main

	private static void dfs(int row, int column, int cnt, int dir) {
		// TODO Auto-generated method stub
		if (dir == 4)
			return;

		int nx = row + dx[dir];
		int ny = column + dy[dir];

		if (!isPossbile(nx, ny))
			return;
		if (visited[nx][ny] || nVisited[map[nx][ny]]) {
			// 도착점과 시작점이 같을 때는 최대값을 계산
			if (startRow == nx && startColumn == ny)
				ans = Math.max(ans, cnt);
			return;
		}

		nVisited[map[nx][ny]] = true; // 디저트 가게를 방문하는 곳 중에 숫자 중복이 있는지 확인
		visited[nx][ny] = true; // 방문 표시
		dfs(nx, ny, cnt + 1, dir); // 현재가고 있는 방향으로 계속 진행
		dfs(nx, ny, cnt + 1, dir + 1); // 방향을 틀어줌
		visited[nx][ny] = false;
		nVisited[map[nx][ny]] = false;

	}

	// 범위 설정
	private static boolean isPossbile(int row, int column) {
		// TODO Auto-generated method stub
		boolean flag = true;

		if (row < 0 || column < 0 || row >= N || column >= N)
			return false;

		return flag;
	}

}// end of class
	
```

