## Programmers_최댓값과최솟값

Collections.sort를 이용해서 간단하게 풀음

```java
import java.util.*;
 
class Solution {
  public String solution(String s) {
      String answer = "";
      ArrayList<Integer> arr = new ArrayList<Integer>();
      String[] str = s.split(" ");
 
      for(int i=0; i<str.length; i++) {
          arr.add(Integer.parseInt(str[i]));
      }
 
      answer = "" + Collections.min(arr) + " " + Collections.max(arr);
 
      return answer;
  }
}

```

