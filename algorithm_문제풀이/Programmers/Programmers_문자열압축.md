## Programmers_문자열압축

>__풀이 방법__
>
>문제에서 요구사항에 맞게 코드 작성했습니다.
>
>

```java
import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        //문자열 s에대해서 일정 크기 만큼 자르고 압축하여 길이가 가장 짧은 값 출력
        //"aabbaccc" 문자열에서 s.length() /2 만큼 잘라야 반복되고 그 이상으로는 반복되지않는다
        for(int i = 1 ; i <= s.length()/2 ; i++){
            //i만큼 문자열을 자를 수 있다.
            int result = split(i,s);
            answer = answer > result ? result : answer;
        }
        
        
        
        return answer;
    }
    
    public int split(int size, String s){
      String result= "";
		
		List<String> words = new ArrayList<String>();
		
		// 나머지는 크기만 뒤에 붙여주면 된다.
		int exist = s.length()%size;
		
		// size글자씩 짤라서 list에 넣는다.
		for ( int i = 0 ; i <= s.length()-size; i+=size ) {
			words.add(s.substring(i,i+size));
		}
		
		int cnt = 1 ;
		
		// list대상으로 비교작업한다.
		for ( int i = 0 ; i < words.size()-1 ; i ++ ) {
			
			// 같은경우
			if ( words.get(i).equals(words.get(i+1))) {
				cnt++;
			} else { // 틀린경우
				if ( cnt > 1 ) result = result + String.valueOf(cnt) + words.get(i) ;
				else result += words.get(i);
				
				cnt = 1;
			}
		}
		
		if ( cnt > 1 ) result = result + String.valueOf(cnt) + words.get(words.size()-1);
		else result += words.get(words.size()-1);
		
		return result.length()+exist;
    }
}
```

