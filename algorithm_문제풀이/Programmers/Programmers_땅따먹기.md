## Programmers_땅따먹기

>__문제 풀이__
>
>처음에 DFS를 통해서 모든 경우를 탐색하는 방법을 사용하려고 했으나, 입력 값이 100,000 이라서 시간초과를 고민했다.
>
>이 후, DP를 이용해서 현재위치에서 가장 최대값을 저장해주고 배열의 끝까지 반복해줌
>
>마지막에 Arrays.sort()를 사용하여 answer에 집어 넣어줌



```java
import java.util.*;
class Solution {
    int solution(int[][] land) {
        int answer = 0;
for (int i = 1; i < land.length; i++) {
			land[i][0] += Math.max(land[i-1][1], Math.max(land[i-1][2], land[i-1][3]));
			land[i][1] += Math.max(land[i-1][0], Math.max(land[i-1][2], land[i-1][3]));
			land[i][2] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][3]));
			land[i][3] += Math.max(land[i-1][0], Math.max(land[i-1][1], land[i-1][2]));
		}
		
		for(int i = 0 ; i < land.length; i++) {
			Arrays.sort(land[i]);
		}
		
		answer = land[land.length-1][3];

        return answer;
    }+++
}
```

