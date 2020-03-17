## Solution5650_핀볼게임



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5650_핀볼게임 {
	private static int N, ans;
	private static int[][] map;
	private static int[] dx = { -1, 0 , 1, 0 }; 
	private static int[] dy = { 0, 1, 0 , -1 };
	private static ArrayList<Node> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 변수
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine()); // 맵의 크기
			map = new int[N][N]; // 맵의 정보를 담을 2차원 배열
			ans = Integer.MIN_VALUE; // 최대 값을 구하기 위한 변수
			list = new ArrayList<Node>(); //웜홀에 대한 정보 담기
			
			for (int i = 0; i < map.length; i++) { // 맵의 정보 입력
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] >=6 && map[i][j] <= 10) {
						list.add(new Node(i,j, map[i][j])); // 웜홀에 대한 정보 입력
					}
				}
			}
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					if(map[i][j] == 0) {
						for (int dir = 0; dir < dx.length; dir++) {
							// 시작 위치와 방향은 임의로 설정 가능하다고 조건 되어있음
							process(i, j, dir);
						}
					}
				}
			}

//			sb.append("#").append(testCase).append(" ").append(ans).append('\n');
			System.out.println("#"+testCase + " " + ans);

		}
//		System.out.println(sb.toString());
	}// end of main
	
	//조건에 따라 실행하기 위한 process메소드
	// 웜홀 6~10 , 블랙홀 -1, 게임판에서 웜홀 또는 블랙홀이 존재하지 않은 경우도 존재
	// 웜홀이 있는 경우 반드시 쌍으로 존재
	// 출발위치로 오거나 , 블랙홀에 빠질 때 끝나게 됨
	// 북동남서
	private static void process(int row, int column, int dir) {
		// TODO Auto-generated method stub
		int getScore = 0;
		int startRow = row;
		int startColumn = column;
		while(true) {
			
			//이동 방향에 따라서 계속 진행
			int nx = row + dx[dir];
			int ny = column + dy[dir];			

			//범위 밖으로 나갈 때는 방향을 반대로 하고 점수 올림
			if(nx < 0 || ny <0 || nx >=N || ny >= N) {
				getScore++;
				nx = row;
				ny = column;
				dir = (dir + 2) % dx.length;
			}
			
			if(map[nx][ny] == 0) {
				row = nx;
				column = ny;
			
			}
			if(map[nx][ny] >=1 && map[nx][ny] <= 5 ) { // 벽돌에 부딪히는 경우
				
				getScore++; // 점수 증가				
				row = nx;
				column = ny;
				if(map[nx][ny] == 1) {
					if(dir == 1 || dir == 0) {
						// 블록의 수평면이나 수직면을 만날 경우 방향을 반대로 바꿔
						dir = (dir +2 ) % dx.length; 
					}else { 
						//경사면을 만날 때는 직각으로 진행 방향 꺽임
						if(dir == 2) dir = 1;
						else dir = 0;
					}
				}else if(map[nx][ny] == 2) {
					if(dir == 1 || dir == 2) {
						// 블록의 수평면이나 수직면을 만날 경우 방향을 반대로 바꿔
						dir = (dir +2 ) % dx.length; 
					}else { 
						//경사면을 만날 때는 직각으로 진행 방향 꺽임
						if(dir == 0) dir = 1;
						else dir = 2;
					}
				}else if(map[nx][ny] == 3) {
					if(dir == 2 || dir == 3) {
						// 블록의 수평면이나 수직면을 만날 경우 방향을 반대로 바꿔
						dir = (dir +2 ) % dx.length; 
					}else { 
						//경사면을 만날 때는 직각으로 진행 방향 꺽임
						if(dir == 1) dir = 2;
						else dir = 3;
					}
				}else if(map[nx][ny] == 4) {
					if(dir == 0 || dir == 3) {
						// 블록의 수평면이나 수직면을 만날 경우 방향을 반대로 바꿔
						dir = (dir +2 ) % dx.length; 
					}else { 
						//경사면을 만날 때는 직각으로 진행 방향 꺽임
						if(dir == 1) dir = 0;
						else dir = 3;
					}
				}else if(map[nx][ny] == 5) {
					// 5일때는 무조건 반대 방향
					dir =(dir +2 ) % dx.length;
				}			
				
			}
			
			if(map[nx][ny] >=6 && map[nx][ny] <=10) { //웜홀에 빠지는 경우
				
				//웜홀에 빠졌을 때 웜홀의 번호는 같으나 다른 위치에 존재하는 값으로 nx,ny 값 변경
				for (Node li : list) {
					if((nx != li.row || ny != li.column) && map[nx][ny] == li.pairNumber) {
						row = li.row;
						column = li.column;						
						break;
					}				
				}				
				
			}
			
			if(nx>=0 && ny>=0 && nx < N && ny < N && map[nx][ny] == -1) break; // 블랙홀에 빠짐
			if(nx == startRow && ny == startColumn ) break; // 처음 위치로 돌아올 때
			
		}
		
		
		ans = ans < getScore ? getScore : ans;
		
	}
	
	static class Node{
		int row;
		int column;
		int pairNumber; //웜홀이 쌍으로 존재하니깐 필요한 넘버
		public Node(int row, int column, int pairNumber) {
			super();
			this.row = row;
			this.column = column;
			this.pairNumber = pairNumber;
		}
		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", pairNumber=" + pairNumber + "]";
		}
		
		
		
	}
}// end of class

```

