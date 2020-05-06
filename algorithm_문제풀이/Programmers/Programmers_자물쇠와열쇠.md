## Programmers_자물쇠와열쇠

>__문제 풀이__
>
>회전에 관해서는 4번 회전하면 처음과 같은 상황이니깐 DFS의 종료조건을 생각했음
>
>사방으로 이동하는것을 구현하는데 있어서 어려움이 생겨서 해설을 참고했고 기존 배열에 *3 크기로 늘려주면 모든 방법을 검사할 수 있다는 것을 이해함
>
>1. 두번의 DFS를 통해서 해결함
>
>구현하기가 어렵게 느껴짐.

```java
class Solution {
    static boolean answer;
    public boolean solution(int[][] key, int[][] lock) {
        answer = false;
        int len = lock.length;
        int[][] copyLock = new int[len * 3][len * 3];
        
        for(int i = 0; i < lock.length; i++){
            for(int j = 0; j < lock.length ; j++){
                copyLock[i + len][j + len] = lock[i][j];
            }
        }
        
        dfs(key, copyLock, 0 );
        return answer;
    }
    
    public void dfs(int[][] key, int[][] lock, int cnt){
        check(key, lock, 0 ,0); // key, lock, x, y 값
        if(answer) return;
        if(cnt >= 4) return;
        int[][] temp = rotate(key); // key를 회전시킴
        dfs(temp , lock, cnt +1 );
    }
    //키를 회전할 함수
    public int[][] rotate(int[][] key){
        int[][] temp = new int[key.length][key.length];
        for(int i = 0; i < key.length; i++){
            for(int j = 0; j < key.length ; j ++){
                temp[i][j] = key[j][key.length-1-i];
            }
        }
        
        return temp;
    
    }
    
    public void check(int[][] key, int[][] lock, int row, int column){
        if(answer) return;
        if(column + key.length > lock.length){ //범위를 벗어나면
            row++;  // 다음행의 처음부터 검사진행해야됨
            column = 0;
        }
        
        if(row + key.length > lock.length ) return; // row가 범위를 벗어나면 return
        
        int[][] newLock = new int[lock.length][lock.length];
        for(int i = 0 ; i < newLock.length ; i ++){
            System.arraycopy(lock[i], 0 , newLock[i], 0 , newLock.length);
        }
        boolean flag = false;
        loop : for(int i = 0 ; i < key.length; i++){
            for(int j = 0; j < key.length; j++){
                if(key[i][j] == 1){
                    if(newLock[row + i][column + j] == 1){
                        flag = true;
                        break loop ;
                    }
                    newLock[row + i][column + j] = 1;
                }
            }
        }
        
        if(!flag){
            loop : for(int i = 0 ; i < lock.length / 3 ; i++){
                for(int j = 0; j < lock.length / 3 ; j++){
                    if(newLock[i + lock.length/3][j + lock.length/3] != 1){
                        flag = true;
                        break loop;
                    }
                }
            }
        }
        
        if(!flag) {
            answer =true;
        }
        
        check(key, lock, row, column+1);
        
        
    }
    
}
```

