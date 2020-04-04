## Programmers_H-Index

>__문제 풀이__
>
>1. citations 배열에서 발표한 논문 인용 횟수에 대해 check ++ 해줬다.
>2. 뒤에서 부터 검사하여 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다는 조건에 맞으면 answer = 인덱스를 입력하고 break해줌



```java
import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);     
        int maxNum = citations[citations.length -1];
        
        int[] check = new int[maxNum+1];
        
        for(int i = 0 ; i < citations.length ; i ++){
            check[citations[i]]++;
        }
        
        for(int i = check.length -1; i >=0 ; i--){
            int highCnt = 0;
            int lowCnt = 0;
            
      
			for (int j = i; j < check.length; j++) {
				if (check[j] > 0) highCnt += check[j];
					
			}

			for (int j = i - 1; j >= 0; j--) {
				if (check[j] > 0) lowCnt += check[j];
					
			}
            if(i <= highCnt && i >= lowCnt) {
                answer = i;
                break;
            }else continue;
            
            
            
        }
        

        return answer;
    }
}
```

