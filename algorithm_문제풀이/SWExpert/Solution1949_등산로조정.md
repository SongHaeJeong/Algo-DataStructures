## Solution1949_등산로조정

>__문제풀이__
>
>1. DFS를 이용해서 문제를 해결 했다.
>
>2. 처음 제출할 때 48개만 통과했으나, 문제를 자세히 읽어보니 K 높이 만큼 벽돌을 제거하는것이 아닌 최대 K만큼이니깐 최소한으로 꺨 수 있는 크기를 정해줬다.
>
>   예를 들어, K = 4  이라고 가정하면, 4 -> 5 상황에서 등산로를 설치 X 그래서 최대 K 깊이 만큼 높이를 제거해줘야됨 K만큼 제거하는 경우 4 -> 1  ==> 1에서 갈 수 있는 곳은 더이상 존재 X
>
>   그러나 4 -> 3 이 되면 3에서 높이 1,2에 대해서 등산로를 설치할 수 있다.





```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution1949_등산로조정 {
	private static int ans, N,K;
	private static int[][] map;
	private static int[] dx = {0, -1, 1 ,0};
	private static int[] dy = {-1 ,0, 0, 1};
	private static ArrayList<Node> list;
	private static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase =1 ; testCase <= T ; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); //맵의 크기
			K = Integer.parseInt(st.nextToken()); // 깍을 수 있는 깊이
			map = new int[N][N];
			visited = new boolean[N][N]; //방문한길은 다시 방문하면안됨.
			int maxNum = 0; //최고 높은 봉우리 찾기 위한 변수
			for(int i = 0 ; i < map.length ; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j< map.length ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(maxNum < map[i][j] ) maxNum = map[i][j];
				}
			}
			
			list = new ArrayList<Node>();//높은 봉우리 집어넣을 리스트
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(map[i][j] == maxNum) list.add(new Node(i,j));
				}
			}
//			visited[2][4] = true;
			ans = Integer.MIN_VALUE;
//			dfs(1, 2, 4, 0 , map[2][4]);
			for(Node n : list) {
				visited[n.row][n.column]= true; //초기 조건
				dfs(1, n.row, n.column,0, map[n.row][n.column]);
				visited[n.row][n.column] = false; //백트레킹
			}
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
			
			
		}//end of testCase
		System.out.println(sb.toString());
	}//end of main
	//북남서동
	private static void dfs(int len, int row, int column, int count, int mapValue) {
		// TODO Auto-generated method stub
		ans = ans < len ? len : ans ; //최대값을 계속 갱신해준다
		
		for(int i = 0 ; i < dx.length ; i++) {
			int nx = row + dx[i]; 
			int ny = column + dy[i];
            //범위를 벗어나거나 방문한 지점이면 다시 검사
			if(nx < 0 || ny < 0 || nx >=N || ny >=N || visited[nx][ny] ) continue;
            //count == 1 의미는 벽돌을 K만큼 깼다는 의미
			if(mapValue <= map[nx][ny] && count ==1) continue;
            
            //벽돌을 한번 꺠지는 않았지만 등산로를 설치 할 수 없을 때는 벽돌을 깨서 가능한지 안되는지 확인
			if(mapValue <=map[nx][ny] && count == 0) {
				if(map[nx][ny] - K < mapValue) {
                    // 최대 K 만큼 깰 수 있다고 명시되어있으니깐 무조건 K 만큼 꺠는게 아님
					int diffHigh = Math.abs(map[row][column] - map[nx][ny]) +1;
					visited[nx][ny] = true;
					dfs(len+1, nx, ny, count+1, map[nx][ny] - diffHigh);
					visited[nx][ny] = false;
				}
			}else {
				visited[nx][ny] = true;
				dfs(len +1, nx, ny ,count, map[nx][ny]);
				visited[nx][ny] = false;
			}
		}
		
	}



	static class Node{
		int row, column;

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
}//end of class

```

