## Programmers_포켓몬

>__문제풀이__
>
>입력으로 주어진 배열에 대해서 HashMap<>에 입력을 받습니다. HashMap에 입력을 받는 이유는 단순히 중복 처리를 위해서 받은거임
>
>이 후 , 조건에 따라서 answer의 값을 지정해줌

```java
import java.util.*;
class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        HashMap<Integer, Integer> hm =new HashMap<Integer, Integer>();
        for(int i = 0 ; i < nums.length; i++){
            hm.put(nums[i], 1);
        }
        
        if(nums.length / 2 <= hm.size()) answer = nums.length/2;
        else answer = hm.size();
        return answer;
    }
}
```

