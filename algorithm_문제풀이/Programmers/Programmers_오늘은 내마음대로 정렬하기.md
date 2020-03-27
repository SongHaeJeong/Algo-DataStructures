## Programmers_오늘은 내마음대로 정렬하기

>풀이 방법
>
>Collections.sort()를 이용했고 compareTo() method 이용 
>
>정렬해주는 기준을 보면 String 해당 인덱스의 문자를 보고 기준해주고 동일한게 존재한다면 기존의 사전처럼 정렬해줌.
>
>밑에 코드를 통해 정렬을 해줬는데, 다른 사람의 코드를 보니깐 원하는 인덱스를 맨앞으로 붙이고 정렬을 해줬다. 





```java
import java.util.*;
class Solution {
  public String[] solution(String[] strings, int n) {
      String[] answer = {};
      ArrayList<Pair> list  = new ArrayList<Pair>();
      for(int i = 0; i < strings.length ; i++){
          String str = strings[i];
          list.add(new Pair(str, str.charAt(n)));          
      }
      
      Collections.sort(list);
      answer = new String[list.size()];
      for(int i = 0; i < answer.length; i++){
          answer[i] = list.get(i).str;
      }
      return answer;
  }

  static class Pair implements Comparable<Pair>{
      String str;
      char c;
      
      public Pair(String str, char c){
         this.str = str;
         this.c = c;
      }
      
      public int compareTo(Pair o){
        
        if(this.c != o.c) {
			return this.c - o.c;
		}else if(this.c == o.c) {
			return this.str.compareTo(o.str);
		}
		return 1;
      }
      
      
      
  }
    
    
    
}
```

