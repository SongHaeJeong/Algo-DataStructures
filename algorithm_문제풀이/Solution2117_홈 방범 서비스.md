## Solution2117_홈 방범 서비스

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution{
	private static int N;
	private static int M;
	private static int[][] map;
	private static int[] dx = { 1, 0, -1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };
	private static int maxValue;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			result = Integer.MIN_VALUE;
			maxValue = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						maxValue += M;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					process(i, j);
				}
			}
			sb.append("#").append(testCase).append(" ").append(result).append("\n");
			
		}
		System.out.println(sb.toString());
	}// end of main

	public static void process(int x, int y) {
		Queue<Info> queue ;
		int k=1;
		while(true) {
			queue = new LinkedList<>();
			queue.add(new Info(x, y, 1));
			boolean[][] visited = new boolean[N][N];
			int protectValue = k * k + (k-1)*(k-1);
			int cnt = 0;
			int fee = 0;
			
			while(!queue.isEmpty()) {
				Info info = queue.poll();
				visited[info.x][info.y] = true;
				if(map[info.x][info.y]==1 ) {
					fee += M;
					cnt++;
				}
				for (int i = 0; i < dx.length; i++) {
					int nx = info.x + dx[i];
					int ny = info.y + dy[i];
					if(nx>=0 && ny >=0 && nx < N && ny< N) {
						if(info.len < k &&!visited[nx][ny]) {
							visited[nx][ny] = true;
							queue.add(new Info(nx, ny, info.len+1));
						}
							
					}
				}				
				
			}
			
			if(fee-protectValue >=0) {
				result = result < cnt ? cnt : result;
			}
			
			if(protectValue > maxValue) break;
			
			k++;
			
			
		}
		
	}

	public static class Info {
		int x;
		int y;
		int len;

		public Info(int x, int y, int len) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
		}

	}
}// end of class
	
	
```

