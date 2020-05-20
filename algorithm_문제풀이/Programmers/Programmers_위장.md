## Programmers_위장

>__문제 풀이__
>
>1. 최대한 경우의 수를 계산하여 문제에 접근하려고 함.
>
>2. HashMap<String, Integer> hm을 생성
>
>3. hm에 Type을 키 값으로 지정해주고 중복으로 들어오면 +1 을 해줌
>
>4. 이제 규칙을 찾아주면됩니다.
>
>   1. face : 3, head : 2 , eyewear : 1 일 때,
>
>      가능한 경우의 수 : (3 * 2 * 1 ) , (3 * 2) , ( 3 * 1 ), (2 * 1) , 3 , 2, 1 의 합이다. 이런 경우는
>
>      (face+1)  * (head + 1) * (eyewear + 1) -1 의 식으로 나타낼 수 있음

```java
import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
       	int answer = 1;
		HashMap<String, Integer> hm = new HashMap<>();

		for (int i = 0; i < clothes.length; i++) {
			String cloth = clothes[i][0];
			String type = clothes[i][1];
			if (hm.containsKey(type)) {
				hm.put(type, hm.get(type) + 1);

			} else {
				hm.put(type, 1);
			}
		}
        
		
	    for (String keys : hm.keySet()){
            answer *= (hm.get(keys) +1);
        }	
        answer -= 1;       
        
        return answer;
    }
}
```



