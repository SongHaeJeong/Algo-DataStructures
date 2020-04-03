## Programmers_괄호변환

>__문제 풀이__
>
>1. 입력에 따라서 u,v를 변환시켜준다.
>2. 올바른 기호인지 아닌지에 따라서 __재귀적__으로 나누어주고 조건에 맞게 실행



```java
import java.util.*;
class Solution {


	public  String solution(String w) {
		// TODO Auto-generated method stub
		return makeCorrect(w);
	}

	private  String makeCorrect(String w) {
		if (w.length() == 0) return "";

		int idx = findIdx(w);
		String u = w.substring(0, idx);
		String v = w.substring(idx, w.length());

		if (correct(u)) {

			return u + makeCorrect(v);

		} else {
			String temp = "(" + makeCorrect(v) + ")"; // 4-1 ~ 4-3			
			String str = reverse(u.substring(1, u.length()-1)); // 첫번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집
			return temp+str;	
			
		}

	}

	private  String reverse(String str) { // 괄호 ( 일때는 )로 만들어준다
		StringBuilder sb = new StringBuilder();
		for(int i = 0 ; i < str.length() ; i++) {
			if(str.charAt(i) == '(') sb.append(')');
			else sb.append('(');
		}
		return sb.toString();
	}

	// 올바른 괄호 문자열인지 아닌지 판단
	private  boolean correct(String u) {
		Stack<Character> st = new Stack<Character>();

		for (int i = 0; i < u.length(); i++) {

			if (u.charAt(i) == '(')
				st.add('(');
			else {
				if (st.isEmpty())
					return false;
				else {
					st.pop();

				}
			}
		}

		return true;
	}

	// w를 가장 작은 크기의 균현 문자열로 자를 수 있는 index return
	private  int findIdx(String w) {
		int a = 0; // '(' 갯수 파악
		int b = 0; // ')' 갯수 파악

		for (int i = 0; i < w.length(); i++) {
			if (w.charAt(i) == '(')
				++a;
			else
				++b;
			if (a == b)
				return i + 1;
		}
		return 0;
	}

    
}
```

