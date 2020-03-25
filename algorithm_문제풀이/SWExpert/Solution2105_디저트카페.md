## Solution2105_디저트카페

```
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution2105_디저트카페 {
	private static int N,ans, startRow, startColumn;
	private static int[][] map;
	private static int[] dx = { 1, 1, -1, -1 };
	private static int[] dy = { 1, -1, -1, 1 };

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
			
			ans = Integer.MIN_VALUE;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map.length; j++) {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(map[i][j]);
					startRow = i;
					startColumn = j;
					dfs(list, i, j, 0);
				}
			}

		}

	}// end of main



	private static void dfs(ArrayList<Integer> list, int row, int column, int dir) {
		// TODO Auto-generated method stub
		if(dir== 3){
			if(startRow == row && startColumn == column) {
				ans = Math.max(ans, list.size());
				return;
			}else {
				return;
			}
		}
		
		
		int nx = row + dx[dir];
		int ny = column + dy[dir];
		if(isPossbile(list, nx, ny)) {
			list.add(map[nx][ny]);
			dfs(list, nx, ny, dir);
			list.remove(list.size()-1);
		}else {
			//아니면 방향을 바꿔줘야됨
			dfs(list, row, column , (dir + 1) % dx.length);			
		}
		
		
		
		
	}



	// 중복을 막기 위해서 사용
	private static boolean isPossbile(ArrayList<Integer> list, int row , int column) {
		// TODO Auto-generated method stub
		boolean flag = true;
		
		if(row < 0 || column < 0 || row >= N || column >= N) return false;
		
		for (Integer integer : list) {
			if(map[row][column] == integer) return false;
		}
		
		
		return flag;
	}
	
	
}// end of class

```

