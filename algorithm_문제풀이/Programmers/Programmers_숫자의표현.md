## Programmers_숫자의표현

>__문제 풀이__
>
>1. 이중포문을 이용해서 해결함 (최악의 경우 1억번의 연산이라 1초 정도 걸린다고 판단함)
>2. i = 1부터 시작해서 j= i 부터 값이 n보다 크면 break, n 과 같으면 answer++ 해줌

```java
class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i = 1; i <= n ; i++){
            int result = 0;
            for(int j = i ; j <= n; j++){
                result += j;
                if(result > n ) break;
                if(result == n) answer++;
            }
        }
        return answer;
    }
}
```

