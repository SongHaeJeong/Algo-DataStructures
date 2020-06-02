## Solution2115_벌꿀채취

>__문제 풀이__
>
>N의 크기가 크지 않아서 모든 경우의 수에 대해서 체크함.
>
>1. findPostion()을 통해서 A,B에 대한 위치를 찾는다.
>2. A에 위치에 대해서 찾으면 getPrice() 함수에서 재귀를 통해서 얻을 수 있는 최대값 획득
>3. A에 최대 값 획득 후, A위치 고정이고 B가 얻을 수 있는 위치에 대해서 getPrice() 함수 돌림
>   1. 중복에 관해서는 visited 함수를 통해서 체크해줬고 모든 B에 대해서 체크한 후 visited 해제함 
>   2. 해제한 이유는 예를 들어, A : (0, 0) , (0, 1) 에 대해서 모든 B를 돌고 난 후 다음 A는 (0,1), (0,2) 가 되어야되기 떄문.



>__재풀이 :40분__

```java
import java.util.Arrays;
import java.util.Scanner;
 
public class Solution2115_벌꿀채취 {
    static int n,m,c;
    static int[][] honey;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int t = 1; t <= T; t++) {
            /******************************
             * 재귀를 이용해 각라인의 최대치를 구하고
             * 정렬을 통해 가장 이득이 높은 두라인의
             * 합을 구한다.
             *******************************/
            n = sc.nextInt();
            m = sc.nextInt();
            c = sc.nextInt();
            honey = new int[n][n];
            
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    honey[i][j] = sc.nextInt();
                }
            }
            res = new int[n];
            selectLine();
            Arrays.sort(res);
            System.out.println("#"+t+" "+(res[n-1]+res[n-2]));
            
        }
    }
    
    static boolean[] visited;
    static int max;
    public static void scv(int start, int benefit,int size,int deep,int[] line) {
        if(size > c) return;
        max = Math.max(benefit, max);
        if(deep == m) return;
        
        
        for(int i = start; i < start+m; i++) {
            if(!visited[i]) {
                visited[i] = true;
                int h = line[i];
                scv(start, benefit+(h*h), size+h, deep+1, line);
                visited[i] = false;
            }
        }
        
    }
    static int[] res;
    private static void selectLine() {
            for(int i = 0; i < n; i++) {
                for(int u = 0; u <= n-m;u++) {
                    max = 0; 
                    visited = new boolean[n];
                    scv(u, 0, 0, 0, honey[i].clone());
                    res[i] = Math.max(res[i],max);
                }
            }
    }
    
}
 

```





```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Soltuion2115_벌꿀채취 {
	private static int N, M, C;
	private static int[][] map;
	private static boolean[][] visited;
	private static int ans;
	private static int aValue;
	private static int bValue;
	private static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MIN_VALUE;
			findPosition(); //A, B에 대한 위치 찾기
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		} // end of testCase;
		System.out.println(sb.toString());

	}// end of main

	private static void findPosition() {
		// TODO Auto-generated method stub
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j <= map.length-M; j++) {
				
				//A에 대해서 먼저 값 찾기
				for(int k = j; k < j + M ; k ++) {
					visited[i][k] = true;					
				}
				result = 0;
				aValue = 0;
				bValue = 0;
				getPrice(i, j, 0, 0, 0); // A값에 대한 시작위치, 통의 볼륨, idx, 값어치			
				aValue = result;
				
				//B 가능한 부분 찾고 최대값 계산하기
				for (int k = 0; k < map.length; k++) {
					for (int q = 0; q <= map.length-M; q++) {
					
						for (int l = q; l <q+M; l++) {
							if(!isPossible(k,l)) break; //A와 겹치니깐 안됨
							
							else {
								result = 0;
								getPrice(k,l,0,0,0);
								bValue = Math.max(bValue, result);
							}
						}
						
						
					}
				}
				
				for (int k = 0; k < j+M; k++) {
					visited[i][k] = false;
				}
				
				
				ans = Math.max(ans, aValue + bValue);
				
				
			}
		}

	}

	private static boolean isPossible(int row, int column) {
		boolean possible = true;
		if(column + M > N) return false;
		for(int m=0; m<M; ++m) {
			if(visited[row][column+m]) {
				possible = false;
				break;
			}
		}
		
		return possible;
	}

	private static void getPrice(int row, int column, int volumn, int idx, int value) {
		// TODO Auto-generated method stub
		if(volumn > C) return; // C 보다 크면 담지 말아야됨
		result = Math.max(result, value);
		if(idx == M) return; 
		
		getPrice(row, column + 1, volumn + map[row][column], idx +1, value + (map[row][column] * map[row][column]));
		getPrice(row, column +1, volumn, idx +1, value);
	}

}// end of class

```

