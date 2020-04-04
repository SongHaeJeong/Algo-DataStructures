## Programmers_전화번호 목록

>__문제 풀이__
>
>1. 이중 포문을 통해서 하나씩 검사하면서 풀음
>2. startsWith 함수를 이용해서 간단하게 풀었다.



```java
import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
         boolean answer = true;
	         for(int i =0; i<phone_book.length; i++){
            for(int j =0; j<phone_book.length; j++){
                if(i == j) continue;
                if(phone_book[j].startsWith(phone_book[i])){//접두어라면
                    return false;   
                }
            }
        }
	        return answer;
    }
}
```

