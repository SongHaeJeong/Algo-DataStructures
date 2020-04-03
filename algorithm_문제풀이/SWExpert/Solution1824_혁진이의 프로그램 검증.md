## Solution1824_혁진이의 프로그램 검증

>__문제 풀이__
>
>1. 종료조건에 대해서 생각해봤다.
>   1. @를 찾았을 때 종료해줌
>   2. 계속 반복된다면 종료해줌 - > 4차원 배열을 통해서 같은 위치, 같은 방향, 같은 값이라면 재방문한거니깐 무한로프를 진행
>2. 이 후, 재귀를 통해서 계속 검사



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution1824_혁진이의프로그램검증 {
	private static int R, C; // ch 의 세로 가로 크기
	private static char[][] ch; // 배열
	private static boolean[][][][] visited ; //재방문 여부
	private static int[] dx = {0 , -1, 0 ,1}; // 동부서남
	private static int[] dy = {1, 0, -1, 0};
	private static boolean find ; // @ 종료조건에 만족하는지 안하는지 변수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase =1 ; testCase <=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			ch = new char[R][C];
			find = false;
			boolean flag  = false;
			for(int i = 0 ; i < R ; i++) {
				String str = br.readLine();
				ch[i] = str.toCharArray();
				for(int j = 0; j <C ; j++) {
					if(ch[i][j] =='@') flag = true;
				}
			}
			
			visited = new boolean[R][C][4][16];
			if(flag) {
				dfs(0,0,0,0);
			}
			sb = find? sb.append("#").append(testCase).append(" ").append("YES").append("\n"):
				sb.append("#").append(testCase).append(" ").append("NO").append("\n");
		}//end of testCase
		
		System.out.println(sb.toString());
	}
	//동북서남
	private static void dfs(int row, int column, int dir, int value) {
		if(find) return; //@ 종료프로그램을 발견했다면 다른 경우는 모두 종료
		if(ch[row][column] =='@') { 
			find = true; //발견했다면 find true로 바꿈
			return;
		}
		if(visited[row][column][dir][value]) return; //같은 위치와 방향 값을 visted 체크
		
		visited[row][column][dir][value] = true;
		char c = ch[row][column];
		
		if(c =='<') {
			dir = 2;
		}else if(c =='>') {
			dir = 0;
		}else if(c == 'v') {
			dir = 3;
		}else if(c == '^') {
			dir = 1;
		}else if(c == '_') {
			if(value == 0) dir = 0;
			else dir = 2;
		}else if(c =='|') {
			if(value == 0) dir =3;
			else dir = 1;
		}else if(c =='?') {
			for(int i = 0 ; i < dx.length ; i++) {
				int nx = row + dx[i];
				int ny = column + dy[i];
				if(nx < 0) nx = R-1;
				else if(nx >= R) nx =0;
				else if(ny < 0) ny = C-1;
				else if(ny>=C) ny = 0;
				dfs(nx, ny, i, value);
			}
		}else if(c =='+') {
			value = value == 15 ? 0 : value+1;
		}else if(c=='-') {
			value = value == 0 ? 15 : value-1;
		}else if(c=='0' || c=='1' || c=='2' || c=='3'
				|| c=='4' || c=='5' || c=='6' || c=='7' || c=='8' || c=='9') value = c-'0';
		
		int nx = row + dx[dir];
		int ny = column + dy[dir];
		if(nx < 0) nx = R-1;
		else if(nx >= R) nx =0;
		else if(ny < 0) ny = C-1;
		else if(ny >= C) ny = 0;
		dfs(nx, ny, dir, value);
		
		
	}
	
}//end of class

```

