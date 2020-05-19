## Solution5650_핀볼게임

재풀이 시간 : 1시간 10분

>__문제 풀이__
>
>1. startGame(), findPair(), changeDir() 함수를 통해 문제를 해결함
>2. startGame()
>   1. 핀볼의 위치와, 방향은 임의로 설정 가능하다고 했음
>   2. 리스트(ball)에 핀볼을 놓을 수 있는 곳 모두 저장
>   3. 북, 서 , 남, 동의 방향 배열을 만들어주고 시뮬레이션 돌림
>3. findPair()
>   1. 웜홀을 저장하는 리스트(warmHole)에 모두 저장
>   2. 웜홀을 만났을 때, 같은 숫자의 웜홀로 이동하는 함수
>   3. 위치는 다르나 같은 숫자의 값인 Node n을 찾고 n을 return 해줌
>4. changeDir()
>   1. 블록을 만났을 때 방향 변환 함수



pacakge reTest : 다시 푼 코드

pacakge test : 처음 풀었던 코드





```java
package reTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5650_핀볼게임 {
	private static int N, ans;
	private static int[][] map;
	private static ArrayList<Node> ball;
	private static ArrayList<Node> warmHole;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	private static int startRow;
	private static int startColumn;
	private static int startDir;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine()); // 맵의 크기
			ans = Integer.MIN_VALUE; // 최대값을 구하기 위해 최소값으로 설정
			map = new int[N][N]; // 맵 생성
			ball = new ArrayList<Node>();
			warmHole = new ArrayList<Node>();
			StringTokenizer st;
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] >= 6)
						warmHole.add(new Node(i, j, map[i][j])); //웜홀의 위치 저장
					if (map[i][j] == 0)
						ball.add(new Node(i, j)); // 핀볼 위치 모두 저장
				}
			}
			for (Node n : ball) {
				startRow = n.row;
				startColumn = n.column;
				startDir = 0;

				for (int i = 0; i < dx.length; i++) {
					// 시작위치와 방향은 임의로 설정한다고 되어있음
					startDir = i;
					startGame(n.row, n.column, i); // 핀볼의 시작 위치, 방향
				}
			}
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		} // end of testCase
		System.out.println(sb.toString());

	}// end of main

	private static void startGame(int row, int column, int dir) {
		// TODO Auto-generated method stub
		int getScore = 0;

		while (true) {

			int nx = row + dx[dir];
			int ny = column + dy[dir];

			if (nx < 0 || ny < 0 || nx >= N || ny >= N) { // 벽에 부딪힌거면
				getScore++;
				nx = row;
				ny = column;
				dir = (dir + 2) % dx.length;

			}

			if (map[nx][ny] == 0) {
				row = nx;
				column = ny;
			}

			if (map[nx][ny] >= 1 && map[nx][ny] <= 5) { // 블록에 부딪힘

				// 블록에 따라서 방향을 다르게 만들어줘야됨
				int newDir = changeDir(nx, ny, dir);
				getScore++;
				row = nx;
				column = ny;
				dir = newDir;

			}
			if (map[nx][ny] >= 6 && map[nx][ny] <= 10) { // 웜홀에 부딪힘
				Node n = findPair(nx, ny);
				row = n.row;
				column = n.column;

			}

			if (map[nx][ny] == -1)
				break;
			if (nx == startRow && ny == startColumn)
				break;

		}

		ans = ans < getScore ? getScore : ans;
	}

	// 블록의 숫자에 따라 , 방향에 따라서 값 바꿔줘야됨
	private static int changeDir(int nx, int ny, int dir) {
		// TODO Auto-generated method stu
		// 북서남동으로 현재 되어있음
		int newDir = 0;
		if (map[nx][ny] == 1) {
			if (dir == 1) { // 현재 방향이 서쪽으로 진행방향이면 방향을 북쪽으로 변환시켜줘야됨
				newDir = 0;
			} else if (dir == 2) {
				newDir = 3;
			} else {
				newDir = (dir + 2) % dx.length;
			}

		} else if (map[nx][ny] == 2) {
			if (dir == 1) {
				newDir = 2;
			} else if (dir == 0) {
				newDir = 3;
			} else {
				newDir = (dir + 2) % dx.length;
			}

		} else if (map[nx][ny] == 3) {
			if (dir == 3) {
				newDir = 2;
			} else if (dir == 0) {
				newDir = 1;
			} else {
				newDir = (dir + 2) % dx.length;
			}
		} else if (map[nx][ny] == 4) {
			if (dir == 2) {
				newDir = 1;
			} else if (dir == 3) {
				newDir = 0;
			} else {
				newDir = (dir + 2) % dx.length;
			}
		} else {
			newDir = (dir + 2) % dx.length;
		}

		return newDir;
	}// end of changeDir

	// 웜홀의 쌍을 찾기 위한 함수
	private static Node findPair(int nx, int ny) {
		// TODO Auto-generated method stub
		for (Node n : warmHole) {
			if (map[nx][ny] == n.number && (n.row != nx || n.column != ny))
				return n;
		}
		return null;
	}// end of findpair

	static class Node {
		int row, column, number;

		public Node(int row, int column, int number) {
			super();
			this.row = row;
			this.column = column;
			this.number = number;
		}

		public Node(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", number=" + number + "]";
		}

	}
}// end of class

```

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

