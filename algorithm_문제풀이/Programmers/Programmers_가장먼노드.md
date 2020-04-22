## Programmers_가장먼노드

>__문제 풀이__
>
>1. 인접행렬을 생성하구 BFS를 통해서 해결하려고 했으나 테스트케이스 7,8,9 통과하지 못해서 질문 검색을 통해 보니깐 로직 문제가 아닌 메모리상의 문제라 인접리스트로 받아서 같은 로직으로 구현하라고 해서 통과했음
>2. 주석으로 되어있는 부분은 인접행렬을 사용 한 풀이



```java
import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        ArrayList<ArrayList<Integer>> graphList = new ArrayList<>();
        
        boolean[] visited = new boolean[n+1];
        for(int i = 0 ; i < n+1 ; i++){
            graphList.add(new ArrayList<Integer>());
        }
        
        for(int i = 0 ; i < edge.length; i++){
            graphList.get(edge[i][0]).add(edge[i][1]);
            graphList.get(edge[i][1]).add(edge[i][0]);
        }      
        
     
		visited[0] = visited[1] = true;
		Queue<Integer> queue = new LinkedList<Integer>();
        
		queue.add(1);
		int startIdx = 1;
		while (!queue.isEmpty()) {
			answer = queue.size();
			for (int i = 0; i < answer; i++) {

				startIdx = queue.poll();

				for (int j = 0; j < graphList.get(startIdx).size(); j++) {
					if (!visited[graphList.get(startIdx).get(j)]) {
						visited[graphList.get(startIdx).get(j)] = true;
						queue.add(graphList.get(startIdx).get(j));
					}
				}
			}
		}

        
//         int[][] graph = new int[n+1][n+1]; // 2차원 배열을 통해 연결되어있는지 체크
//         boolean[] visited= new boolean[n+1]; // 방문했는지에 대한 체크
//         for(int i = 0 ; i < edge.length; i++){
//             graph[edge[i][0]][edge[i][1]] = 1;
//             graph[edge[i][1]][edge[i][0]] = 1;
//         }
        
        
//         Queue<Integer> queue = new LinkedList<Integer>();
//         visited[1] = true;
//         queue.add(1);
//         int startIdx = 1; // 1 노드부터 시작
//         //BFS를 이용해서 한 점에서 갈 수 있는 곳 다 저장하고 그 크기만큼 answer에 입력
//         while(!queue.isEmpty()){
//             answer = queue.size();
            
//             for(int i = 0 ; i < answer ; i++){ // 연결되어있는 노드 큐에 삽입
//                 startIdx = queue.poll();
                
//                 for(int j = 1; j < graph.length; j++){ // 노드가 0번인거는 시작하지 않음
//                     if(!visited[j] && graph[startIdx][j] == 1){
//                         visited[j] = true;
//                         queue.add(j);
//                     }
//                 }
                
                
//             }
            
        
//         }
        
        
        return answer;
    }
}
```

