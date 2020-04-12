## Programmers_섬연결하기

```java
import java.util.*;
class Solution {
   public int solution(int n, int[][] costs) {
        int answer = 0;
        int[][] adj = new int[n][n];
        for(int i = 0; i < costs.length; i++) {
            adj[costs[i][0]][costs[i][1]] = adj[costs[i][1]][costs[i][0]] = costs[i][2];
        }
        boolean[] visit = new boolean[n];
        List<Integer> connect = new ArrayList<>(n+1);
        connect.add(0);
        visit[0] = true;
        while(connect.size() < n) {
            int min = Integer.MAX_VALUE;
            int minIdx = -1;
            for(int island = 0; island < connect.size(); island++) {
                int i = connect.get(island);
                for(int j = 0; j < n; j++) {
                    if(!visit[j] && i != j && adj[i][j] > 0 && adj[i][j] < min) {
                        min = adj[i][j];
                        minIdx = j;
                    }
                }
            }
            visit[minIdx] = true;
            connect.add(minIdx);
            answer += min;
        }
        return answer;
    }
}
```

