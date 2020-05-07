## Trie(트라이)

__Trie 자료구조란?__

- 일반 트리 자료구조 중 하나로입니다
- 텍스트 자동 완성 기능과 같이 문자열을 저장하고 탐색하는데 유용한 자료구조입니다.



__Trie 자료구조의 특징 ?__

- 정렬된 트리 구조이다. (데이터에 따라 이진트리일 때도 있다)
- Trie는 자식노드를 맵<Key, Value> 형태로 가지고 있다.
- 루트를 제외한 노드의 자손들은 해당 노드와 공통 접두어를 가진다.
- 루트 노드는 빈 문자와 연관이 있다.(특정 문자가 할당되어있지 않다)



__트라이 구현 코드__

```java
package reTest;

import java.util.HashMap;
import java.util.Map;

public class Trie {

	static class Node {
		private String character; // 알파벳 문자, 키 값
		private int value; // 데이터
		private Node[] children; // 하위 노드 배열 변수
		private boolean leaf; // leaf 노드 여부 확인
		final int ALPHABET_SIZE = 26;

		public Node(String character) {
			this.character = character;
			this.children = new Node[ALPHABET_SIZE];
		}

		public Node getChild(int index) {
			return children[index];
		}

		public void setChild(int index, Node node, int value) {
			node.setValue(value);
			this.children[index] = node;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getCharacter() {
			return character;
		}

		public void setCharacter(String character) {
			this.character = character;
		}

		public Node[] getChildren() {
			return children;
		}

		public void setChildren(Node[] children) {
			this.children = children;
		}

		public boolean isLeaf() {
			return leaf;
		}

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		@Override
		public String toString() {
			return this.character;
		}

	}
	
	private Node root; //루트노드
	private int indexOfSingleChild; // 단일 노드의 인덱스
	
	public Trie() {
		this.root = new Node(""); //루트 노드 빈문자열로 초기화)
	}
	
	public void insert(String key, int value) {
		Node tempNode = root; //루트 노드로 초기화
		
		//입력한 Key의 길이 만큼 반복
		for (int i = 0; i < key.length(); i++) {
			char c = key.charAt(i); // 알파벳 추출
			int asciiIndex = transformASCIIIndex(c); //추출한 알파벳을 배열 인덱스에 맞게 저장할 수 있도록 ascii 값 변환
			// 추출한 알파벳을 가진 하위노드가 존재하지 않으면
			if(tempNode.getChild(asciiIndex) == null) {
				Node node = new Node(String.valueOf(c)); // 새로운 노드 생성
				tempNode.setChild(asciiIndex, node, value); //하위 노드로 세팅
				tempNode = node; //하위 노드로 이동
			}else {
				tempNode = tempNode.getChild(asciiIndex); // 하위 노드로 이동
			}
		}
		
		tempNode.setLeaf(true); // 알파벳 문드자들이 삽입이 완료되고 마지막 노드를 leaf 노드로 설정
	}
	
	// 탐색 : 해당 키가 존재하는지 여부 반환
	public boolean search(String key) {
		Node trieNode = root; //루트 노드로 초기화
		
		for (int i = 0; i < key.length(); i++) {
			int asciiIndex = transformASCIIIndex(key.charAt(i)); // 추출한 알파벳을 배열 인덱스에 맞게 ascii 값 변환
			
			if(trieNode.getChild(asciiIndex) == null) { // 추출한 알파벳을 가진 하위 노드가 존재하지 않으면 false 리턴
				return false;
			}else {
				trieNode = trieNode.getChild(asciiIndex); // 하위 노드로 이동
			}
		}
		
		return true;
	}
	
	// 탐색 : 해당 키값에 해당하는 값 변환
	public Integer searchAsMap(String key) {
		Node trieNode = root; //루트 노드로 초기화
		
		for (int i = 0; i < key.length(); i++) {
			int asciiIndex = transformASCIIIndex(key.charAt(i)); // 추출한 알파벳을 배열 인덱스에 맞게 ascii 값 변환
			
			if(trieNode.getChild(asciiIndex) != null) { // 추출한 알파벳을 가진 하위 노드가 존재하지 않으면 false 리턴
				trieNode = trieNode.getChild(asciiIndex); //하위 노드로 이동
			}else {
				return null;
			}
		}
		
		return trieNode.getValue();
	}
	
	// 가장 긴 접두사 반환
	public String longestCommonPrefix() {
		Node trieNode = root;
		String longestCommonPrefix = ""; // 빈 문자열로 초기화
		
		//하위노드가 여러개 이거나 leaf 노드일 때까지 반복 수행
		while(countNumOfChildren(trieNode) == 1 && !trieNode.isLeaf()) {
			trieNode = trieNode.getChild(indexOfSingleChild);
			longestCommonPrefix = longestCommonPrefix + String.valueOf((char) (indexOfSingleChild + 'a'));
		}
		return longestCommonPrefix;
	}
	
	
	
	//하위 노드의 갯수 반환
	private int countNumOfChildren(Node trieNode) {
		int numOfchildren = 0; // 하위 노드 개수 0으로 초기화
		
		for (int i = 0; i < trieNode.getChildren().length; i++) {
			if(trieNode.getChild(i) != null) {
				numOfchildren++; // 하위 노드 1 증가
				indexOfSingleChild = i; // 단일 노드의 인덱스
			}
		}
		return numOfchildren;
	}

	private int transformASCIIIndex(char c) {
		// TODO Auto-generated method stub
		return c-'a';
	}
	
	public static void main(String[] args) {
		Trie trie = new Trie();
		
		trie.insert("apple", 1);
		trie.insert("approve", 2);
		trie.insert("air", 3);
		trie.insert("appa", 4);
		trie.insert("appb", 5);
		
		System.out.println("search : true or false----");
		System.out.println(trie.search("apple"));
		System.out.println(trie.search("archive"));
		System.out.println();
		
		System.out.println("-----search : as map----");
		System.out.println(trie.searchAsMap("apple"));
		System.out.println(trie.searchAsMap("archive"));
		
		Trie trieForCommonPrefix = new Trie();
		trieForCommonPrefix.insert("hope", 1);
		trieForCommonPrefix.insert("hobby", 2);
		trieForCommonPrefix.insert("horror", 3);
		trieForCommonPrefix.insert("hospital", 4);
		trieForCommonPrefix.insert("horse", 5);
		String str = trieForCommonPrefix.longestCommonPrefix();
		System.out.println(str);
		System.out.println("longest common prefix : " + trieForCommonPrefix.longestCommonPrefix());
	}
}

```

__트라이 이용 문제__

https://programmers.co.kr/learn/courses/30/lessons/60060

