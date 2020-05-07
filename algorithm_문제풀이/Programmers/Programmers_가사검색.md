## Programmers_가사검색

>트라이 개념을 학습하고 가사검색 문제를 풀었다
>
>기존의 3중 포문을 이용해서 풀었을 때는 효율성을 하나도 맞지 못해서 이것저것 검색해서 Trie 자료구조로 문제를 풀어야 통과된다는 것을 알게되었다.



```java
package reTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Programmers_가사검색 {
	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };

		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?" };

		System.out.println(Arrays.toString(solution(words, queries)));
	}// end of main

	 public static int[] solution(String[] words, String[] queries) {
	        int[] answer = new int[queries.length];
	        Trie root = new Trie('*');
	        for (int i = 0; i < words.length; i++) {
	            String word = words[i];
	            Trie prev = root;
	            for (int j = 0; j < word.length(); j++) {
	                char c = word.charAt(j);
	                Trie curr = new Trie(c);
	                prev = prev.putChild(curr, word.length());
	            }
	        }
	         
	        for (int i = 0; i < queries.length; i++) {
	            String query = queries[i];
	            Trie trav = root;
	            if (query.charAt(0) == '?') continue;
	            for (int j = 0; j < query.length(); j++) {
	                char c = query.charAt(j);
	                if (c == '?') {
	                    answer[i] = trav.getNumChildrenWithLen(query.length());
	                    break;
	                }
	                trav = trav.getChild(c);
	                if (trav == null) {
	                    answer[i] = 0;
	                    break;
	                }
	                
	            }
	        }
	        
	        //reverse
	        Trie rootReverse = new Trie('*');
	        for (int i = 0; i < words.length; i++) {
	            String word = words[i];
	            Trie prev = rootReverse;
	            for (int j = word.length() - 1; j >= 0; j--) {
	                char c = word.charAt(j);
	                Trie curr = new Trie(c);
	                prev = prev.putChild(curr, word.length());
	            }
	        }
	         
	        for (int i = 0; i < queries.length; i++) {
	            String query = queries[i];
	            Trie trav = rootReverse;
	            if (query.charAt(0) != '?') continue;
	            for (int j = query.length() - 1; j >= 0; j--) {
	                char c = query.charAt(j);
	                if (c == '?') {
	                    answer[i] = trav.getNumChildrenWithLen(query.length());
	                    break;
	                }
	                trav = trav.getChild(c);
	                if (trav == null) {
	                    answer[i] = 0;
	                    break;
	                }
	            }
	        }
	        return answer;
	    }
	    
	    static class Trie {
	        char c;
	        HashMap<Character, Trie> children;
	        HashMap<Integer, Integer> numChildrenWithLen;
	        
	        Trie(char c) {
	            this.c = c;
	            children = new HashMap<Character, Trie>();
	            numChildrenWithLen = new HashMap<Integer, Integer>();
	        }
	        
	        Trie putChild(Trie t, int len) {
	            if (!children.containsKey(t.c)) {
	                children.put(t.c, t);
	            }
	            if (numChildrenWithLen.containsKey(len)) {
	                numChildrenWithLen.put(len, numChildrenWithLen.get(len) + 1);
	            }
	            else {
	                numChildrenWithLen.put(len, 1);
	            }
	            return children.get(t.c);
	        }
	        
	        Trie getChild(char c) {
	            return children.get(c);
	        }
	        
	        int getNumChildrenWithLen(int len) {
	            if (numChildrenWithLen.containsKey(len)) return numChildrenWithLen.get(len);
	            return 0;
	        }
	    }
}


```

