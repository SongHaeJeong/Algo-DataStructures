## Solution5656_벽돌깨기

>배열의 크기가 최대로 했을 때 크지 않아서 완전탐색 함
>
>DFS를 이용해 벽돌을 깰 수 있는 곳 찾아서 
>
>벽돌 깰 때, DFS(map)을 가지고 다님
>
>BFS를 통해 벽돌을 깸



>__재풀이__
>
>처음 풀이와 비슷하나, DFS(Map)을 가지고 다니지 않고 2차원 배열 temp를 생성해주고 DFS가 끝나면 temp를 map으로 이동해줌.



```java
package test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5656_벽돌깨기 {
	private static int T, N, W, H, ans;
	private static Queue<Node> queue;
	private static int[][] map;
	private static int[] dx = { 1, 0, -1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 테스트 케이스
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 몇번 구슬을 던질지
			W = Integer.parseInt(st.nextToken()); // 가로 길이
			H = Integer.parseInt(st.nextToken()); // 세로 길이
			map = new int[H][W]; // 구슬에 대한 정보 입력
			queue = new LinkedList<Node>();
			
			// 맵에 대한 정보 입력 받기
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map[i].length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0) {
					}

				}
			}
		
			ans = Integer.MAX_VALUE;
			dfs(0, map);
			if(ans == Integer.MAX_VALUE) ans = 0;
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase

		System.out.println(sb.toString());
	}// end of main

	private static int[][] copy(int[][] map) {
		int[][] temp = new int[H][W];
		
		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, temp[i].length);
		}
		
		return temp;
		
	}



	private static void dfs(int idx, int[][] map) {
		// TODO Auto-generated method stub
		if(idx == N) {
			int sum = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(map[i][j] != 0) sum++;
				}
			}
			ans = Math.min(ans, sum);
			return;
		}
		
		for (int i = 0; i < W; i++) {
			int row = 0;
			
			while(row < H) {
				if(map[row][i] == 0) row++;
				else break;
			}
			
			if(row == H) continue;
			int[][] arr = copy(map);
			queue.add(new Node(row, i , map[row][i]));
			deleteWall(arr);
			print(arr);
			dfs(idx+1, arr);
			print(arr);
			
			
		}
		
		
		
	}





	//벽돌 삭제
	private static void deleteWall(int[][] map) {
		
		
		// Queue를 이용해서 벽돌 제거
		// map에 적인 숫자 -1 만큼 벽돌 제거 할 수 있다
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int len = node.value -1;
			map[node.row][node.column] = 0;
			
			for (int i = 0; i < dx.length; i++) {
				
				for (int j = 1; j <= len; j++) {
					
					int nx = node.row + dx[i] * j ;
					int ny = node.column + dy[i] * j;
					
					if(nx < 0 || ny < 0 || nx >=H || ny >= W || map[nx][ny] == 0) continue;
					
					if(map[nx][ny] != 0) {
						queue.add(new Node(nx, ny, map[nx][ny]));
						map[nx][ny] = 0;
					}
					
				}
				
				
			}
		}

		// 벽돌을 삭제하고 중간에 비어진 부분 내리기
		changeMap(map);

	}

	private static void changeMap(int[][] map) {
		// TODO Auto-generated method stub
		//큐에 모두 담고 다시 밑에서부터 복사해둠
		for (int i = 0; i < W; i++) {
			Queue<Integer> tempQueue = new LinkedList<Integer>();
			for (int j = H-1; j >=0; j--) {
				if (map[j][i] > 0) {
					tempQueue.add(map[j][i]);
					map[j][i] = 0;
					
				}
			}

			int idx = H - 1;
			int size = tempQueue.size();
			for (int j = 0; j < size; j++) {
				map[idx--][i] = tempQueue.poll();
			}

		}
		
	}
	
	

	private static void print(int[][] map) {
		// TODO Auto-generated method stub
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.print(map[i][j] + " ");
			}			
			System.out.println();
		}
		System.out.println("=============================");
	}



	static class Node {
		int row, column, value;

		public Node(int row, int column, int value) {
			super();
			this.row = row;
			this.column = column;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", value=" + value + "]";
		}

	}

}// end of class

```

```java
package reTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5656_벽돌깨기 {
	private static int N, W, H,ans;
	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map[0].length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
//			remove(1,2);
//			remove(2,2);
//			remove(8,6);
			
			
			select(0);
			if(ans == Integer.MAX_VALUE) ans = 0;
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase;
		System.out.println(sb.toString());

	}// end of main

	private static void select(int idx) {
		// TODO Auto-generated method stub
		if (idx == N) {
			int cnt = 0 ;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if(map[i][j] > 0) cnt++;
				}
			}
			
			ans = ans > cnt ? cnt : ans;
			return;
		}

		int[][] temp = new int[H][W];
		for (int i = 0; i < map.length; i++) {
			System.arraycopy(map[i], 0, temp[i], 0, map[i].length);
		}

		for (int i = 0; i < W; i++) {

			int row = 0;
			while (row < H) {
				if(map[row][i] == 0) row++;
				else break;
			}
			
			if(row == H) continue;
			
			remove(row, i);
//			print();
//			System.out.println("=========================");
			select(idx + 1);
			for (int j = 0; j < map.length; j++) {
				System.arraycopy(temp[j], 0, map[j], 0, map[j].length);
			}
//			print();
//			System.out.println("=========================");

		}
	}

	private static void print() {
		// TODO Auto-generated method stub
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, -1, 0, 1 };

	private static void remove(int row, int column) {
		// TODO Auto-generated method stub
		
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(new Node(row, column, map[row][column]));
		map[row][column] = 0;
		while (!queue.isEmpty()) {
			Node n = queue.poll();
			int len = n.num-1;
			for (int i = 0; i < dx.length; i++) {
				int x = n.row;
				int y = n.column;

				for (int j = 1; j <= len; j++) {

					int nx = x + dx[i] * j;
					int ny = y + dy[i] * j;

					if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 0 ) continue;
					queue.add(new Node(nx, ny, map[nx][ny]));
					map[nx][ny] = 0;

				}

			}

		}
		
		Queue<Integer> moveQueue = new LinkedList<Integer>();
		for(int i = 0 ; i < W; i++) {
			int idx = H;
			
			while(idx > 0) {
				idx--;	
				if(map[idx][i] > 0) {
					moveQueue.add(map[idx][i]);
					map[idx][i] = 0;
				}
				
				
			}
			
			idx = H-1;
			
			while(!moveQueue.isEmpty()) {
				map[idx][i] = moveQueue.poll();
				idx--;
			}
			
			
		}
		
		
		
	}

	static class Node {
		int row, column, num;

		public Node(int row, int column, int num) {
			super();
			this.row = row;
			this.column = column;
			this.num = num;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", num=" + num + "]";
		}

	}
}// end of class

```

