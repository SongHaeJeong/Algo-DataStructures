## Programmers_JadenCase문자열만들기

>__문제 풀이__
>
>1. 문자열 UpperCase, LowerCase 함수를 이용했다.



주의할 점 : 마지막에 공백있는 8번 케이스에 대해서 처리를 해줘야됨



```java
import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";     
        String str = ""; // 문자열을 조작하기 위해서 생성
        for(int i = 0 ; i < s.length() ; i++){
            // System.out.println(s.charAt(i));
            if(s.charAt(i) ==' '){
                if(str.length() > 0){
                     String first = str.substring(0,1).toUpperCase();
                     String second = str.substring(1,str.length()).toLowerCase();
                     answer += first + second;
                }             
               
                answer += " ";                
                str ="";
            }else{
                str +=s.charAt(i);                
            }
        }
           if(str.length() > 0){
                     String first = str.substring(0,1).toUpperCase();
                     String second = str.substring(1,str.length()).toLowerCase();
                     answer += first + second;
                }       
         
        
       
        // System.out.println(answer);
         
        return answer;
    }
}
```

