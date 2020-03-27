## Programmers_나누어 떨어지는 숫자배열

>문제 풀이
>
>1. 배열 arr에 대해 divisor로 떨어지면 list에 담는다
>2. list 크기만큼 answer에 대한 배열을 생성해주고 옮김



```java
import java.util.*;
class Solution {
  public int[] solution(int[] arr, int divisor) {
      int[] answer = {};
      ArrayList<Integer> list = new ArrayList<>();
      for(int i = 0; i < arr.length; i++){
          if(arr[i] % divisor == 0){
              list.add(arr[i]);
          }
      }
      if(list.size() > 0 ){
         answer = new int[list.size()];      
         for(int i=0 ; i < list.size(); i++){
          answer[i] = list.get(i);
        }
      
        Arrays.sort(answer);
      }else{
          answer = new int[1];
          answer[0] = -1;
      }     
      
      
      return answer;
  }
}
```



