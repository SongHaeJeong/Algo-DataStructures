## Programmers_지형이동

>__문제 풀이__
>
>1차시기에 76.7 /100 (나머지 시간 초과)
>
>다시 풀고 합격하면 문제 설명하겠습니다.

```java
import java.util.*;
class Solution {
    private static int answer;
    private static boolean[][] visited;
    private static ArrayList<Ladder> list ;
    public int solution(int[][] land, int height) {
       answer = 0;
	        visited = new boolean[land.length][land[0].length];
	       list = new ArrayList<Ladder>();  //사다리를 설치할 수 있는 지역 추가
	        int row = 0;
	        int column = 0;
	        while(true){            
	            BFS(row, column , land, height);
	            if(check()) break;
	            else {
	                Collections.sort(list);
	                for(Ladder l : list){
	                    if(visited[l.sR][l.sC] && visited[l.eR][l.eC]) continue;
	                    else {
	                        visited[l.sR][l.sC] = true;
	                        visited[l.eR][l.eC] = true;
	                        row = l.eR;
	                        column = l.eC;
	                        answer += l.dh;
	                        break;
	                    }
	                }
	                
	            }
	            
	        }  
        
        
        return answer;
    }
    
  
    private boolean check(){
        for(int i = 0 ; i < visited.length; i++){
            for(int j = 0 ; j < visited[0].length ; j++){
                if(!visited[i][j]) return false;
            }
        }
        return true;
    }
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0 , 1};
    public void BFS( int row, int column , int[][] land,int height){
            boolean flag = false;
	        Queue<Node> queue = new LinkedList<Node>();	        
	        queue.add(new Node(row, column ,0));
	        visited[row][column] = true;
	        
	        while(!queue.isEmpty()){
	            Node n = queue.poll();
	            for(int i = 0;  i < dx.length; i++){
	                int nx = n.row + dx[i];
	                int ny = n.column  + dy[i];
	                int pH = land[n.row][n.column];            
	                if(nx < 0 || ny < 0 || nx >= land.length || ny >=land[0].length)  continue;
                    if(visited[nx][ny] ) {
                        flag = true;
                        continue;
                    }
	                int diffHeight = Math.abs(land[nx][ny] - pH);
	                if(diffHeight <= height) {
	                    flag = true;
	                    visited[nx][ny] = true;
	                    queue.add(new Node(nx, ny, 0));
	                }else{
	                    list.add(new Ladder(n.row, n.column, nx, ny, Math.abs(land[nx][ny] - pH)));
	                }
	            }         
	            
	        }
	            
	        if(!flag) visited[row][column] = false;
	        
        
    }
    
    static class Ladder implements Comparable<Ladder>{
        int sR, sC, eR,eC, dh;
        
        public Ladder(int sR, int sC, int eR, int eC, int dh){
            this.sR = sR;
            this.sC = sC;
            this.eR = eR;
            this.eC =eC;
            this.dh = dh;
        }
        
        public int compareTo(Ladder o){
            return this.dh - o.dh;
        }
    }
    
    static class Node {
        int row, column , dh; //가로, 세로, 높이의 차이
        
        public Node(int row, int column, int dh){
            this.row = row;
            this.column = column;
            this.dh = dh;
        }
    }
}
```

