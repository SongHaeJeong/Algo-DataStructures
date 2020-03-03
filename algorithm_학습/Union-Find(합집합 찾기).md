#### Union-Find(합집합 찾기)

- 여러 개의 노드가 존재할 때 두 개의 노드를 선택해서, 현재 두 노드가 서로 같은 그래프에 속하는지 판별

- Find 알고리즘은 두 개의 노드의 부모 노드를 확인하여 현재 같은 집합에 속하는지 확인

```java
package test;

public class UnionFind {
	public static void main(String[] args) {
		int[] arr = new int[11];
		for (int i = 1; i < arr.length; i++) {
			arr[i] = i;
		}
		
		union(arr, 1, 2);
		if(findParent(arr, 1 ,2) == 1) System.out.println("같은 부모");
		if(findParent(arr, 1 ,3) == 0) System.out.println("다른 부모");
		
	}//end of main
	
	//부모의 위치 찾기
	private static int getParent(int[] arr, int a) {
		if(arr[a] == a) return a;
		return arr[a] = getParent(arr, arr[a]);
	}
	
	// 같은 부모 노드를 가지는지 확인
	private static int findParent(int[] arr, int a, int b) {
		a = getParent(arr, a);
		b = getParent(arr, b);
		if(a==b) return 1;
		else return 0;
	}
	
	// 각 부모 노드를 합침
	private static void union(int[] arr, int a, int b) {
		a = getParent(arr, a);
		b = getParent(arr, b);
		if(a < b) arr[b] = a;
		else arr[a] =b ;
	}
	
}//end of class

```

