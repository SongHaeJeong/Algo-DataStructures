### 크루스칼 알고리즘(Kruskal Algorithm)



- 크루스칼 알고리즘은 가장 적은 비용으로 모든 노드를 연결하기 위해 사용하는 알고리즘

  -다시 말해 최소 비용 신장 트리를 만들기 위한 대표적인 알고리즘

1. 정렬된 순서에 맞게 그래프에 포함시킴
2. 포함시키기 전에는 사이클 테이블을 확인 
   - 사이클 발생하는지의 여부는 Union-Find 알고리즘 적용
3. 사이클을 형성하는 경우 간선을 포함하지 않음



```java
package test;

import java.util.ArrayList;
import java.util.Collections;

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int cost;
    Edge(int v1, int v2, int cost) {
        this.v1 = v1;
        this.v2 = v2;
        this.cost = cost;
    }
    @Override
    public int compareTo(Edge o) {
        if(this.cost < o.cost)
            return -1;
        else if(this.cost == o.cost)
            return 0;
        else
            return 1;
    }
}
public class Main {
    public static int[] parent;
    public static ArrayList<Edge> edgeList;
	
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x != y)
            parent[y] = x;
    }
	
    public static int find(int x) {
        if(parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return true;
        else return false;
    }
    public static void main(String[] args) {
        edgeList = new ArrayList<Edge>();
        edgeList.add(new Edge(1,4,4));
        edgeList.add(new Edge(1,2,6));
        edgeList.add(new Edge(2,3,5));
        edgeList.add(new Edge(2,4,3));
        edgeList.add(new Edge(2,5,7));
        edgeList.add(new Edge(2,6,8));
        edgeList.add(new Edge(3,6,8));
        edgeList.add(new Edge(4,5,9));
        edgeList.add(new Edge(5,6,11));
		
        parent = new int[7];
        for(int i = 1; i <= 6; i++) {
            parent[i] = i;
        }
		
        Collections.sort(edgeList);
		
        int sum = 0;
        for(int i = 0; i < edgeList.size(); i++) {
            Edge edge = edgeList.get(i);
            if(!isSameParent(edge.v1, edge.v2)) {
                sum += edge.cost;
                union(edge.v1, edge.v2);
            }
        }
		
        System.out.println(sum);
    }	
}
```



