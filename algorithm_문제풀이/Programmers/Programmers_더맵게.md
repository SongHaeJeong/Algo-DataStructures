## Programmers_더맵게

>__문제 풀이__
>
>1. 우선순위 큐를 활용해서 문제 풀었다.
>2. try catch를 이용해서 모든 음식의 스코빌 지수를 K이상으로 만들 수 없는 경우에는 -1 return 함

```java
import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        	PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		});
		
		for(int i = 0 ; i < scoville.length ; i++) {
			pq.add(scoville[i]);
		}
		
		while(!pq.isEmpty()) {
            
			if(pq.peek() >= K) break;
			answer++;
			try {				
				int most = pq.poll();
				int second = pq.poll();
				pq.add(most + (second * 2));
			} catch (Exception e) {
				return -1;
			}		
			
		}       
        
        return answer;
    }
}
```

