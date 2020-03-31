## Programmers_큰수만들기

>__문제 풀이__
>
>1. 최종적으로 출력한 배열 result 생성 (크기는 number의 길이에서 k개 만큼 제거한 크기)
>2. stack을 이용해서 시뮬레이션
>   1. 앞에서 부터 stack 집어넣고 만약 새로 집어넣을 값이 기존에 들어있는 값보다 크면 기존의 값 제거
>   2. 제거 할 때, k-- >0  주의
>3. stack에 존재하는 값을 result에 모두 집어넣고 최종적으로 return

```java
import java.util.*;
class Solution {
 
    public String solution(String number, int k) {
        char[] result = new char[number.length()-k];
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i < number.length(); i++){
            char c = number.charAt(i);
            while(!stack.isEmpty() && stack.peek() < c && k-- > 0){
                stack.pop();
            }
            stack.push(c);
        }
        
        for(int i = 0 ; i < result.length;i++){
            result[i] = stack.get(i);
        }
        
        
        return new String(result);
    }
    

}
```

