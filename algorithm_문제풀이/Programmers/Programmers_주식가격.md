## Programmers_주식가격

>__문제 풀이__
>
>선택된 가격이 언제 떨어지는지에 대해서 체크하는 문제
>
>2중 for문을 이용해서 해결 



```java
import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i = 0 ; i < prices.length -1 ; i ++){
            int checkPrice = prices[i];          
            for(int j = i+1; j< prices.length ; j++){
                if(checkPrice <= prices[j]){
                   answer[i]++;
                }else{
                    answer[i]++;                    
                    break;
                }
            }
            
        }
        
        answer[answer.length-1] = 0;
       
        
        return answer;
    }
}
```

