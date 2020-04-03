## Programmers_소수찾기

>__풀이 방법__
>
>1. numbers의 조각으로 나타낼 수 있는 숫자를 HashSet에 넣어줌
>   1. HashSet을 사용한 이유는 중복을 없애기 위해서 사용
>2. 소수인지 아닌지에 판단은 1과 자기자신을 제외하고 나누어지는 경우가 있으면 안됨.



```java
import java.util.*;
class Solution {
	private static char[] set;
	private static int answer;
	private static HashSet<Integer> hs;
	private static boolean[] visited;

	public static int solution(String numbers) {
		answer = 0;
		hs = new HashSet<>();
		visited = new boolean[numbers.length()];
		for (int i = 1; i <= numbers.length(); i++) {
			set = new char[i];
			permutation(0, i, numbers); // 종이 조각으로 만들 수 있는 숫자
		}

		Iterator<Integer> it = hs.iterator();
		while (it.hasNext()) {
			check(it.next());
		}

		return answer;
	}

	public static void permutation(int depth, int len, String numbers) {
		if (depth == len) {
			String temp = "";
			for (int i = 0; i < set.length; i++) {
				temp += set[i]+"";
			}
			hs.add(Integer.parseInt(temp));
			return;
		}
		
		for (int i = 0; i < numbers.length(); i++) {
			if(!visited[i]) {
				visited[i] = true;
				set[depth] = numbers.charAt(i);
				permutation(depth + 1,  len, numbers);
				visited[i] = false;
			}
		}
	}

	public static void check(int num) {
		if (num == 0 || num == 1)
			return;
		int idx = 2;
		boolean flag = true;
		while (idx < num) {
			if (num % idx == 0)
				return;
			else
				idx++;
		}

		answer++;

	}
}
```

