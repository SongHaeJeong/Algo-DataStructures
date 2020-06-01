## Programmers_올바른 괄호

>__문제 풀이__
>
>1. Stack을 이용해서 ( 일때는 추가하고 )일때는 POP을 통해서 올바른 괄호인지 검사함
>2. 만약, POP을 해야될 때, 사이즈가 0이거나 문자열에 대해서 모든 검사를 끝냈을 때, Stack의 사이즈가 0이 아니면 answer = false로 지정함
>
>

```java
import java.util.*;
class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> st = new Stack<Character>();
        for(int i= 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                st.add('(');
            }else{
                if(st.size() == 0){
                    answer = false;
                    break;
                }else{
                    st.pop();
                }
            }
        }
        
        if(st.size() != 0) answer = false;
        
    

        return answer;
    }
}
```

