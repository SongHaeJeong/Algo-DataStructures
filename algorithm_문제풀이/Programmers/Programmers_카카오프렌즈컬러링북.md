## Programmers_카카오프렌즈컬러링북

>__문제 풀이__
>
>BFS를 통해 몇개의 영역이 존재하고 영역이 존재한다면 몇개의 원소로 이루어져 있는지 확인함.



```java
import java.util.*;
class Solution {
    private static int numberOfArea;
    private static int maxSizeOfOneArea;
    private static int maxRow ;
    private static int maxColumn;
    private static int[] dx = {-1 , 1, 0 , 0};
    private static int[] dy = {0, 0 ,-1, 1};
  public int[] solution(int m, int n, int[][] picture) {
      numberOfArea = 0;
      maxSizeOfOneArea = 0;
      maxRow = m;
      maxColumn = n;
      boolean[][] visited = new boolean[m][n];
      for(int i=0; i < m ; i++){
        for(int j =0 ; j < n ; j++){
            if(picture[i][j] != 0 && !visited[i][j]){
                numberOfArea++;
                solution(i,j,picture, visited);          
            }   
        }        
      }
      
      
      int[] answer = new int[2];
      answer[0] = numberOfArea;
      answer[1] = maxSizeOfOneArea;
      return answer;
  }
  
 public void solution(int row, int column, int[][] picture, boolean[][] visited){
     Queue<Pair> queue = new LinkedList<>();
     visited[row][column] =true;
     queue.add(new Pair(row, column, picture[row][column]));     
     int size=  1;
     while(!queue.isEmpty()){
         Pair p = queue.poll();
         for(int i = 0 ; i < dx.length ; i ++){
             int nx = p.row + dx[i];
             int ny = p.column + dy[i];
             
             if(nx < 0 || ny < 0 || nx>= maxRow || ny >= maxColumn || visited[nx][ny] || p.data != picture[nx][ny])                  {
                    continue; 
                }
             visited[nx][ny] = true;
            size++;
             queue.add(new Pair(nx, ny, picture[nx][ny]));
             
             
         }
         
     }
     maxSizeOfOneArea = maxSizeOfOneArea < size ? size : maxSizeOfOneArea ;
 }
    
 static class Pair{
     int row, column,data;
     
     public Pair(int row, int column, int data){
         this.row = row;
         this.column = column;
         this.data = data;
     }
 }
    
}
```

