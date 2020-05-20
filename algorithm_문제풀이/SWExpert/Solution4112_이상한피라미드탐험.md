## Solution4112_이상한피라미드탐험

>__문제 풀이__
>
>1. 노드를 생성하여 BFS를 통해 탐색하려고 했으나 조건이 너무 까다로워서 Greedy 방식을 생각함
>2. 피라미드의 앞부분의 넘버를 저장할 수 있는 배열을 생성함
>3. a -> b를 갈 때, 층의 차이에 따른 갈 수 있는 이동거리를 생각하고 이 후,  현재 위치와 보물의 위치의 차이를 빼줘서 최종 답을 출력

```java
package reTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution4112_이상한피라미드탐험 {
	private static int a, b;
	static int[] levels; // 방이 몇번부터 시작하는지에 대해 저장
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//모든 테케에서 동일하게 사용
		initLevel();
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken()); //민지위치
			b = Integer.parseInt(st.nextToken()); //보물위치			
			int ans = 0;
			// 보물과 민지의 위치가 같다면 볼일 바로 출력
			
			if(a != b) {
				if(a > b) { //민지가 언제나 위에 있도록 SWAP 이렇게 안만들어주면 두가지 로직에 대해 풀이를 해줘야됨
					int temp = a;
					a = b;
					b = temp;
					
				}
				// 각 요소의 현재 층 확인하기
				int aLevel = getLevel(a);
				int bLevel = getLevel(b);
				
				// 민지가 b층으로 이동했을 때 생성되는 피라미드의 왼쪽과 오른쪽
				int startLeft = a;
				int startRight = a;
				
				for (int i = aLevel, j =1; i < bLevel; i++, j++) {
					startLeft += i;
					startRight = startLeft + j;
				}
				// 일단 해당층으로 이동
				ans = bLevel - aLevel;
				
				// 피라미드의 범위를 넘어선다면 차이를 계산
				if(b < startLeft) {
					ans+= (startLeft - b);
				}else if(b > startRight) {
					ans += b - startRight;
				}
				
				
			}
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
			
			
		}//end of testCase
		System.out.println(sb.toString());
	}//end of main
	private static int getLevel(int pos) {
		// TODO Auto-generated method stub
		int level = 0;
		for (int i = 1; i < levels.length; i++) {
			if(pos <= levels[i]) {
				level = i;
				break;
			}
		}
		return level;
	}
	private static void initLevel() {
		// TODO Auto-generated method stub
		// MAX가 10000임
		
		levels = new int[144];
		for(int i = 1 ; i < levels.length ;i++) {
			levels[i] = levels[i-1] + i;
		}
//		System.out.println(Arrays.toString(levels));
	}
}//end of class

```

