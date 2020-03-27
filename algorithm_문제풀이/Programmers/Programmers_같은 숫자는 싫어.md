## Programmers_같은 숫자는 싫어

>풀이 방법 
>
>배열의 크기만큼 i , i +1 를 검사해서 다르면 list에 집어넣고, 같으면 계속 검사하는 식으로 진행
>
>주의할 점은 마지막에 검사했을 때, 다르면 둘다 집어넣줘야되고 같으면 앞에 부분만 넣어줘야되는 상황을 먼저 체크했다.



```java
import java.util.*;

public class Solution {
	public int[] solution(int []arr) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for(int i =0 ; i < arr.length-1; i ++){
            
           if(i+1 == arr.length-1 && arr[i] != arr[i+1]){
                list.add(arr[i]);
                list.add(arr[i+1]);                    
            }else if(i+1 == arr.length-1 && arr[i] == arr[i+1]){
                list.add(arr[i]);
            }else  if(arr[i] == arr[i+1]) {
                continue;
            }
            else list.add(arr[i]);
        }
        
        int[] answer = new int[list.size()];
        for(int i =0; i < answer.length; i++){
            answer[i] = list.get(i);
        }
        
    
        

        return answer;
	}
}
```

