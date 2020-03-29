Programmers_스킬트리

>__문제 풀이__
>
>처음 코드 3중 포문, 2번째 코드 2중 for문 
>
>처음 작성한 코드는 3중 for문이여서 입력값이 커지면 시간 초과가 발생할 것 같아서 2번째 코드를 작성





```java
import java.util.ArrayList;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;      
        
        for(int i = 0; i < skill_trees.length; i++){
            String str = skill_trees[i]; // "BACDE"
            ArrayList<Integer> list = new ArrayList<Integer>(); //순서를 담을 list
            for(int j = 0; j < str.length(); j ++){
                char c = str.charAt(j); //"B"
                for(int k = 0; k < skill.length(); k++){
                    if(c == skill.charAt(k)){ //"B가 C에 있으면 C의 인덱스를 추가함"
                        list.add(k);
                        break;
                            
                    }//없으면 검사
                }
            }
            
            if(list.size() == 0) answer++; // list.size() == 0 이면, 선행해야 하는 스킬 없음
            else{
                if(list. size() == 1 && list.get(0) == 0) answer++; // 맨처음의 스킬을 배우는 경우
                else if(list.get(0) == 0){ // 맨처음 스킬을 배우고 그 이후의 조건 처리
                    boolean flag = true;
                    for(int j = 0 ; j < list.size()-1; j ++){
                        if(list.get(j)+1 != list.get(j+1)) { // 앞의 스킬과 1씩 차이나면 먼저 배우는거임 아니면 다음꺼 배우고 배운거임
                            flag = false;
                            break;
                        }
                    }
                    
                    if(flag) answer++;
                }
               
            }           
        }                      
        
        return answer;
    }
}
```

```java
import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for(String skillTree : skill_trees){
            int startIdx = 0 ;
            boolean flag = true;
            for(char ch : skillTree.toCharArray()){
                int idx = skill.indexOf(ch);
                
                if(idx == -1) continue;
                else if(startIdx == idx){
                    startIdx++;                    
                }else{
                    flag = false;
                    break;
                }
                
                
            }          
            
            if(flag) answer++;
            
        }                         
        return answer;
    }
}
```

