## Programmers_124나라의숫자

>__문제풀이__
>
>| 10진법 | 124 나라의 숫자 |
>| ------ | :-------------- |
>| 1      | 1               |
>| 2      | 2               |
>| 3      | 4               |
>| 4      | 11              |
>| 5      | 12              |
>| 6      | 14              |
>| 7      | 21              |
>| 8      | 24              |
>|        |                 |
>
>1. 처음 든 생각이 나머지를 이용해서 진수 변환을 생각해서 그렇게 접근했다.
>2. 주의 할 점 : 3일때 4가 되니깐 나머지가 0일 때는 4로 변환
>3. 6일 때 : 6 % 3 = 0  -> 4붙이고 몫은 2니깐  ==> 24라고 예상 했으나 14라는 답이 나오길래 9에 대해서도 해보니깐 나머지가 0인 경우는 4를 붙이고 그 값에 대해서 -1을 해줘야됨.

```java
class Solution {
  public String solution(int n) {
      String answer = "";
      int amd = 0;
      StringBuilder sb = new StringBuilder();
      while(n > 0){
          amd = n % 3;
          n /= 3;
          
          if(amd == 0){
              n -= 1;
              answer = 4 + answer;
          }else{
              answer = amd + answer;
          }
          
          
          
      }
      
      return answer;
  } 

}
```

