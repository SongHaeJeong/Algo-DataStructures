## Programmers_거스름돈

>__문제풀이__
>
>1. DP를 이용해서 문제를 해결함
>
>   | _    | _1   | 2    | 3    | 4    | 5    |
>   | ---- | ---- | ---- | ---- | ---- | ---- |
>   | 1원  | 1    | 1    | 1    | 1    | 1    |
>   | 2원  | 1    | 2    | 2    | 3    | 3    |
>   | 5원  | 1    | 2    | 2    | 3    | 4    |
>
>   위의 그래프를 보면 1원일 때, 0~N 까지 1가지 가능함
>
>   2원일 때 , dp(i)(j) = dp(i-1)(j) + dp(i)(j- money[i]) 이라는 공식을 찾을 수 있음



```java
import java.util.*;
class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        
        Arrays.sort(money); // DP를 이용하려고 함 
        //초기 세팅
        int firstNum = money[0];
        int[][] dp = new int[money.length][n+1];
        
        for(int i = 0; i < dp[0].length; i++){
            if(i % firstNum ==0 ) dp[0][i] = 1;
        }
        
        for(int i = 1 ; i <dp.length; i++){
            // 초기 세팅
            for(int j = 0; j <= money[i] ; j++){
                if(j % money[i] == 0) dp[i][j] = dp[i-1][j] + 1;
                else dp[i][j] = dp[i-1][j];
            }
            
            for(int j = money[i]+1; j < dp[0].length; j++){
                dp[i][j] += dp[i-1][j] + dp[i][j-money[i]];
            }
        }
     
        answer = dp[dp.length-1][n] % 1000000007;
        return answer;
    }
}
```

