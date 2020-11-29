## Beakjoon - 마법사상어와파이어스톰

>##### 풀이방법
>
>문제 조건에 나온 순서대로 로직 구성
>
>문제 풀이 시간 : 1시간 20분
>
>오래걸린 부분 : L 크기에 맞게 Map을 구분하고 돌리는데 시간이 오래 걸림.

```java
package cjsonghae;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main20058_마법사상어와파이어스톰 {
	private static int N, Q , result, bigSnow;
	private static int[][] map;
	private static int[] lInfo; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		int size = (int)Math.pow(2, N); //맵 사이즈
		map = new int[size][size]; //맵에 대한 정보
		lInfo = new int[Q]; //L의 정보
		bigSnow = Integer.MIN_VALUE;
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < size; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			lInfo[i] = Integer.parseInt(st.nextToken());
		}
		// 모든 입력 받음
		
		process();
		System.out.println(result);
		bigSnow = bigSnow == Integer.MIN_VALUE ? 0 : bigSnow;
		System.out.println(bigSnow);
		
		
		
		
		
		
	}//end of main
	private static void process() {
		// TODO Auto-generated method stub	
		int idx = 0; //lInfo에서 L에 대한 정보를 구하기위해서 Index 표시
		while(Q > 0) {
			Q--; // 파이어스톰 총 Q번 시전
			splitAndRotate(idx);
			remove(); //얼음이 줄어드는거 체크
			idx++;
			
		}
		result();
	}
	private static void result() {
		// TODO Auto-generated method stub
		Queue<Pair> queue = new LinkedList<Pair>();
		boolean[][] visited = new boolean[map.length][map.length];
		
		
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j] > 0) result += map[i][j];
				if(map[i][j] > 0 && !visited[i][j]) {
					//가장큰 얼음덩어리를 찾기위한 로직
					visited[i][j] = true;
					queue.add(new Pair(i,j,map[i][j]));
					int size = 1;
					while(!queue.isEmpty()) {
						Pair p = queue.poll();
						
						for (int k = 0; k < dx.length; k++) {
							int nx = p.row + dx[k];
							int ny = p.column + dy[k];
							
							if(nx >=0 && ny >= 0 && nx < map.length && ny < map.length && !visited[nx][ny] && map[nx][ny] > 0) {
								visited[nx][ny] = true;
								queue.add(new Pair(nx, ny , map[nx][ny]));								
								size++;
							}
							
						}
						
					}
					
					bigSnow = bigSnow < size ? size : bigSnow;
					
				}
			}
		}
	}
	private static int[] dx = {-1 ,0 , 1, 0};
	private static int[] dy = {0 , -1, 0, 1};
	private static boolean[][] check;	
	
	private static void remove() {
		// TODO Auto-generated method stub
		ArrayList<Pair> list = new ArrayList<Pair>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[i][j] > 0) {
					int count = 0;				
					for (int k = 0; k < dx.length; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if(nx >=0 && ny >=0 && nx < map.length && ny < map.length && map[nx][ny] > 0) {
							count++;
						}
						
					}
					
					if(count < 3) {
						list.add(new Pair(i,j, map[i][j]));
					}
					
				}
			}
		}
		
		for (Pair p : list) {
			map[p.row][p.column]--;
		}
		
	}
	private static void splitAndRotate(int index) {
		// TODO Auto-generated method stub
		//맵을 나누고 회전
		int size = (int)Math.pow(2, lInfo[index]); 
		check = new boolean[map.length][map.length]; //맵에 대해서 구역을 나누고 회전시킬떄 표시하기위해서 생성 
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(!check[i][j]) {
					changeMap(i,j,size);					
				}
			}
		}
		
		
		
		
		
	}
	private static void changeMap(int row, int column, int size) {
		// TODO Auto-generated method stub
		int[][] temp = new int[size][size];
		int[][] rotate = new int[size][size];
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp.length; j++) {
				temp[i][j] = map[row+i][column+j];
			}
		}
		
		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate.length; j++) {
				rotate[i][j] = temp[temp.length-1-j][i];
			}
		}
		
		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate.length; j++) {
				map[row+i][column+j] = rotate[i][j];
				check[row+i][column+j] = true;
			}
		}
		
		
	}
	static class Pair{
		int row, column, value;

		public Pair(int row, int column, int value) {
			super();
			this.row = row;
			this.column = column;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Pair [row=" + row + ", column=" + column + ", value=" + value + "]";
		}
		
		
	}
}//end of class

```

