## 다익스트라(Dijkstra) 알고리즘

- 대표적인 최단 경로 탐색 알고리즘
- 현실 세계에 사용하기 매우 적합한 알고리즘 중 하나
- 하나의 최단 거리를 구할 때 그 이전까지 구했던 최단 거리 정보를 그대로 사용한다는 특징 



__동작 과정__

1. 출발 노드를 설정
2. 출발 노드를 기준으로 각 노드의 최소 비용을 저장
3. 방문하지 않은 노드 중에서 가장 비용이 적은 노드를 선택
4. 해당 노드를 거쳐서 특정한 노드로 가는 경우를 고려하여 최소 비용을 갱신
5. 3~4번 과정을 반복



```java
// n^2의 시간복잡도를 가짐
package reTest;

import java.util.Arrays;

public class 다익스트라 {
	public static void main(String[] args) {
		final int M = Integer.MAX_VALUE;
		int[][] G = {
				{0,2,5,1,M,M},
				{2,0,3,2,M,M},
				{5,3,0,3,1,5},
				{1,2,3,0,1,M},
				{M,M,1,1,0,2},
				{M,M,5,M,2,0}
		};
		
		int startNode = 0; // 시작 정점
		int[] D = G[startNode].clone(); // 가중치 배열, 시작 정점의 진출차수로 가중치배열을 초기화
		boolean[] visited = new boolean[G.length]; //사용한 정점들을 저장할 배열
		
		for(int i = 0; i < D.length; i++) {
			
			// 방문하지 않은 정점 중에서, 가중치가 최소인 정점을 찾아서 visited배열에 정점 추가
			int minIndex = 0; // 최소가중치를 찾기 위한 Idx
			int min = M; // 최소 가중치
			for (int j = 0; j < D.length; j++) {
				if(!visited[j] && D[j] < min) { //최소 정점 찾기
					min = D[j];
					minIndex = j;
				}
			}
			
			visited[minIndex]  = true; //가중치가 최소인 정점을 찾아냄
			//선택한 정점을 통해서 갈 수 있는(인접한) 정점의 가중치를 갱신
			
			for (int j = 0; j < D.length; j++) {
				// 사용하지 않은 정점, 인접한 정점, 가중치가 지금보다 더 작으면 갱신
				if(!visited[j] && G[minIndex][j] != M &&D[j] > G[minIndex][j] + D[minIndex]) {
					D[j] = G[minIndex][j] + D[minIndex];
				}
				
			}			
			
		}
		
		System.out.println(Arrays.toString(D));
	}
}
```



위의 코드는 N^2의 시간복잡도를 가짐. 따라서 최대한 빠르게 작동시켜야 하는 경우 힙 구조를 활용하여 시간 복잡도 o(n * logN)을 만들 수 있다. 위와 같은 소스코드는 정점의 갯수가 많은데 간선은 적을 때 치명적일 정도로 비효율적으로 작동할 수 있다.

```java
class Pair implements Comparable<Pair>{
    int vertex;
    double distance;
    
    Pair(int v, double d){
        vertex = v;
        distance = d;
    }
 
    @Override
    public int compareTo(Pair o) {
        return (int) (distance - o.distance);
    }
}
 
double dijkstra2(){
    double[] distance = new double[vertexNum];
    distance[0] = 0;
    for(int i=1; i<vertexNum; i++) distance[i] = Double.MAX_VALUE;
    PriorityQueue<Pair> heap = new PriorityQueue<>();
    heap.add(new Pair(0, distance[0]));
    while(!heap.isEmpty()){
        Pair p = heap.poll();
        if(distance[p.vertex] < p.distance) continue;
        for(int i : graph.get(p.vertex).keySet()){
            if(distance[i] > distance[p.vertex] + graph.get(p.vertex).get(i)){
                distance[i] = distance[p.vertex] + graph.get(p.vertex).get(i);
                heap.add(new Pair(i, distance[i]));
            }
        }
    }
    return distance[vertexNum - 1];


```

