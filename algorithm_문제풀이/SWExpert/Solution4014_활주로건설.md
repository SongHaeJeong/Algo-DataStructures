## Solution4014_활주로건설

>단순 시뮬레이션 문제임. 문제에 나오는 조건을 하나씩 구현함

>__재풀이 시간__ : 20분 ?
>
>조건에 맞게 하나씩 구현함

```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014_활주로건설 {
	private static int N, X , ans ;
	private static int[][] map;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); //테스트케이스 변수 입력
		StringBuilder sb = new StringBuilder(); // 최종적으로 출력할 것 Sysout보다 빠름
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 맵의 크기
			X = Integer.parseInt(st.nextToken()); // 활주로의 길이
			
			map = new int[N][N]; //맵 생성
			
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = 0;
			connect(); // 활주로 건설 가능한지 판단
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
	}//end of main
	private static void connect() {
		
		
		
		//가로 먼저 검사
		for (int i = 0; i < map.length; i++) {
			//동일한 셀에 경사로를 겹쳐서 사용할 수 없기 떄문에 사용
			boolean[] flag = new boolean[map.length];
			boolean isPossible = true;
			here: for (int j = 0; j < map.length-1; j++) {
				// 경사로의 높이는 1이니깐 1보다 크면 체크 안해줘두됨
				if(Math.abs(map[i][j] - map[i][j+1]) > 1) {
					isPossible = false;
					break;  
				}
				else {
					if(map[i][j] - map[i][j+1] == 0) continue;
					// 뒤에있는게 더 크다는 의미
					else if(map[i][j] - map[i][j+1] == -1) {
						//경사로의 길이만큼 건설이 가능한지 체크			
						
						for (int k = j; k > j-X; k--) {
							if(k>=0 && !flag[k] && map[i][k] == map[i][j+1]-1) flag[k] =true;
							else {
								isPossible =false;
								break here;
							}
						}
						
						
					}else {
						//앞에 셀이 높이가 더 크다는 의미이고 경사로의 길이만큼 건설이 가능한지 체크
						for (int k = j+1; k < X + j+1; k++) {
							//사다리 건설
							if(k< N && !flag[k] && map[i][k] == map[i][j]-1) flag[k] = true; 
							else {
								isPossible = false;
								break here;
							}
						}						
					}
				}
			}
			
			if(isPossible) ans++;
		}
		//세로검사
		for (int i = 0; i < map.length; i++) {
			//동일한 셀에 경사로를 겹쳐서 사용할 수 없기 떄문에 사용
			boolean[] flag = new boolean[map.length];
			boolean isPossible = true;
			here: for (int j = 0; j < map.length-1; j++) {
				// 경사로의 높이는 1이니깐 1보다 크면 체크 안해줘두됨
				if(Math.abs(map[j][i] - map[j+1][i]) > 1) {
					isPossible = false;
					break;  
				}
				else {
					if(map[j][i] - map[j+1][i] == 0) continue;
					// 뒤에있는게 더 크다는 의미
					else if(map[j][i] - map[j+1][i] == -1) {
						//경사로의 길이만큼 건설이 가능한지 체크			
						
						for (int k = j; k > j-X; k--) {
							if(k>=0 && !flag[k] && map[k][i] == map[j+1][i]-1) flag[k] =true;
							else {
								isPossible =false;
								break here;
							}
						}
						
						
					}else {
						//앞에 셀이 높이가 더 크다는 의미이고 경사로의 길이만큼 건설이 가능한지 체크
						for (int k = j+1; k < X + j+1; k++) {
							//사다리 건설
							if(k< N && !flag[k] && map[k][i] == map[j][i]-1) flag[k] = true; 
							else {
								isPossible = false;
								break here;
							}
						}						
					}
				}
			}
			
			if(isPossible) ans++;
		}
		
		
		
	}
	private static void check(int i) {
		// TODO Auto-generated method stub
		for (int j = 0; j < map.length; j++) {
			
		}
	}
}//end of class

```





```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution4014_활주로건설 {
	private static int N, X, ans;

	private static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];

			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

				}

			}
			ans = 0;
			check();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}// end of main

	private static void check() {
		// TODO Auto-generated method stub

		for (int i = 0; i < map.length; i++) {
			boolean[] visited = new boolean[map[0].length];
			boolean flag = true;
			for (int j = 0; j < map.length - 1; j++) {
				if (map[i][j] == map[i][j + 1])
					continue;
				if (Math.abs(map[i][j] - map[i][j + 1]) == 1) {

					// 오른쪽으로 다리 건설
					if (map[i][j] > map[i][j + 1]) {

						for (int k = j + 1; k <= j + X; k++) {
							if (k >= N || visited[k] || map[i][k] != map[i][j + 1]) {
								flag = false;
								break;
							}else {
								visited[k] = true;
							}

						}

					} else {
						// 왼쪽으로 다리 건설
						for (int k = j; k > j - X; k--) {
							if (k < 0 || visited[k] || map[i][k] != map[i][j]) {
								flag = false;
								break;
							}else {
								visited[k] = true;
							}
						}
					}

				} else {
					// 2 이상 차이
					flag = false;
					break;
				}
			}

			if (flag)
				ans++;
		}
		
		
		for (int i = 0; i < map.length; i++) {
			boolean[] visited = new boolean[map.length];
			boolean flag = true;
			for (int j = 0; j < map.length - 1; j++) {
				if (map[j][i] == map[j+1][i])
					continue;
				if (Math.abs(map[j][i] - map[j+1][i]) == 1) {

					// 오른쪽으로 다리 건설
					if (map[j][i] > map[j+1][i ]) {

						for (int k = j + 1; k <= j + X; k++) {
							if (k >= N || visited[k] || map[k][i] != map[j+1][i]) {
								flag = false;
								break;
							}else {
								visited[k] = true;
							}

						}

					} else {
						// 왼쪽으로 다리 건설
						for (int k = j; k > j - X; k--) {
							if (k < 0 || visited[k] || map[k][i] != map[j][i]) {
								flag = false;
								break;
							}else {
								visited[k] = true;
							}
						}
					}

				} else {
					// 2 이상 차이
					flag = false;
					break;
				}
			}

			if (flag)
				ans++;
		}

	}
}// end of class


```

