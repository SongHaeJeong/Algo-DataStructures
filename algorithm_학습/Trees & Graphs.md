## Trees & Graphs

__트리의 종류__

>- Binary Tree
>- Binary serach Tree
>- Complete Binary Tree
>- Full Binary Tree
>- Perfect Binary Tree



__Binary Tree의 3가지 순회방법__

>- Inorder(Left, Root, Right) - 7 3 8 1 9 4 10 0 11 5 2 6
>- Preorder(Root, Left, Right) - 0 1 3 7 8 4 9 10 2 5 11 6
>- Postorder(Left, Right, Root) - 7 3 8 1 9 10 4 1 11 5 6 2 0

![	](https://user-images.githubusercontent.com/59730002/77659295-3b23f080-6fbb-11ea-9199-1ad143ae0e91.png)

__3가지 순회방법 JAVA 구현__

```java
package test;


class Node{
	int data;
	Node left;
	Node right;		
}

class Tree{
	public Node root;
	

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node node) {
		this.root = node;
	}
	
	public Node makeNode(Node left, int data, Node right) {
		Node newNode = new Node();
		newNode.data = data;
		newNode.left = left;
		newNode.right = right;
		return newNode;
	}
	
	public void Inorder(Node n) {
		if(n != null) {
			Inorder(n.left);
			System.out.print(n.data + " ");
			Inorder(n.right);
		}
	}
	
	public void Preorder(Node n) {
		if(n != null) {
			System.out.print(n.data + " ");
			Preorder(n.left);
			Preorder(n.right);
		}
	}
	
	public void Postorder(Node n) {
		if(n != null) {
			Postorder(n.left);
			Postorder(n.right);
			System.out.println(n.data);
		}
	}

}

public class Main {	
	
	public static void main(String[] args) throws Exception {
		Tree t = new Tree();
		Node n4 = t.makeNode(null, 4, null);
		Node n5 = t.makeNode(null, 5, null);
		Node n2 = t.makeNode(n4, 2, n5);
		Node n3 = t.makeNode(null, 3, null);
		Node n1 = t.makeNode(n2, 1, n3);		
		t.setRoot(n1);
		
		t.Inorder(t.getRoot());
		System.out.println();
		t.Preorder(t.getRoot());
		System.out.println();
		t.Postorder(t.getRoot());
		
	}// end of main
}// end of class

```

__배열을 이진검색트리로 만들기__

__Binary Heaps__

>- 최대 힙
>  - 가장 큰 값을 Root 노드에 존재하고 각 부모 노드는 자식 부모보다 큰 조건
>- 최소 힙 
>  - 가장 작은 값을 Root 노드의 존재하고 각 부모 노드는 자식 부모보다 작은 조건

>__최소힙에 노드 삽입하기__
>
>1.  완전 트리에 맨 끝에 왼쪽 부터 노드를 삽입한다.
>2.  정렬이 되어있지 않은 상태이기 때문에 자신의 부모노드랑 비교해서 자신이 작으면 부모랑 교체 
>   1. 부모 노드와 계속 크기를 비교하면서 교체
>
>__시간 복잡도__ O(log n)
>
>__최소힙에서 노드 가져오기__
>
>1. 루트 노의 값을 가져온다. (최소힙을 구현하는 이유에 대해서 생각)
>2. 맨 마지막 노드의 값을 가지고 온 후 자식의 노드와 비교해서 작은 값을 위로 올림
>   1. 자식 노드와 계속 크기를 비교하면서 교체 
>
>__시간 복잡도__ O(log n)



__Trie (트라이)__

>문자열에서 검색을 빠르게 도와주는 자료구조
>



__Graph__

>__표현 방법__
>
>- Adjacency Matrix : 배열을 이용해서 나타냄
>- Adjacency List : LinkedList를 이용해서 나타냄

>__검색 방법__
>
>__Depth-Frist Search(DFS)__ : Stack
>
>- inorder
>- preorder
>- postorder  
>
>__Breadth-First-Search(BFS)__ : Queue







