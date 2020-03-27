## Programmers Level-test 2

![image](https://user-images.githubusercontent.com/59730002/77714863-6fc89400-701d-11ea-8e4f-207f94631426.png)

```java
import java.util.*;

public class Solution {

    public int solution(int n) {
        int ans = 1;

        while(n != 1){
            if(n % 2 == 1){
                ans ++;
                n--;
            }else if(n  % 2 == 0){
                n = n /2 ;
            }      

        }


        return ans;
    }


}
```

__시간 초과난 코드 DFS 구현__

>입력값이  1<= n <=10억이다.
>
>DFS로 구현할 시 재귀를 타고 타고 10억까지 도착하고 이전의 값에서 +1 해본다면 터질 것이다.

```java
import java.util.*;

public class Solution {
    private static int ans;
    public int solution(int n) {
        ans = Integer.MAX_VALUE;
        dfs(1, 1, n );
        return ans;
    }
    
    public void dfs(int idx, int value , int n){
        if(ans <= value) return;
        if(idx > n) return;
        if(idx == n){
            ans = Math.min(ans, value);
            return;
        }
        
        dfs(idx * 2, value, n );
        dfs(idx +1 , value +1, n);         
        
    }
}
```

