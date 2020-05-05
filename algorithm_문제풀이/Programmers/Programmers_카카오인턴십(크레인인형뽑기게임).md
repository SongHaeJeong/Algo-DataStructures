## Programmers_카카오인턴십(크레인인형뽑기게임)

>__문제 풀이__
>
>1. moves 1차원 배열의 값 -1 = board 열의 값임
>2. for문을 통해서 1번에서 구한 열에서 맨위에 있는 인형을 찾음
>3. Stack을 활용해서 똑같은 인형이 있는지 확인함

```java
import java.util.*;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0 ; i < moves.length; i++){
            int idx = moves[i] - 1;
            int nextCatch = 0;
            for(int j = 0 ; j < board.length; j++){
                
                if(board[j][idx] > 0) {
                    nextCatch = board[j][idx];
                    board[j][idx] = 0;
                    // System.out.println(nextCatch);
                    break;
                }
                
            }
            if(nextCatch > 0){
              if(stack.size() == 0) {
                stack.add(nextCatch);
            }else{
                int preCatch = stack.peek();
                if(nextCatch == preCatch) {
                    answer += 2;
                    stack.pop();
                }else{
                    stack.add(nextCatch);
                }
            }
            }
          
            
            
            
        }
        return answer;
    }
}
```

