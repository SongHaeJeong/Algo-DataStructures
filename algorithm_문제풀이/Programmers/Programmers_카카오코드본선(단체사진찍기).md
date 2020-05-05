## Programmers_카카오코드본선(단체사진찍기)

>__문제 풀이__
>
>1. 순열을 통해 모든 찍을수있는 경우의 수를 구함
>2. 이후, 조건에 따라 조건을 제시한 프렌즈의 위치와 상대방의 위치를 찾음
>3. 조건에 맞는 경우의 수만 answer++ 해줌

```java
package reTest;

import java.util.Arrays;

public class Programmers_단체사진찍기 {
	public static void main(String[] args) {
		int n =2 ;
		String[] data = {"N~F=0", "R~T>2"};
		solution(n, data);
		System.out.println(answer);
	}
	private static int answer;
	private static String[] friends = {"A", "C", "F", "J" , "M", "N", "R","T"};
	private static String[] set;
	private static boolean[] visited;
	private static String[] command;
	private static void solution(int n, String[] data) {
		// TODO Auto-generated method stub
		
		answer = 0 ;
		command = data.clone();
		set = new String[friends.length];
		visited = new boolean[friends.length];
		permutation(0);
		
	}
	private static void permutation(int idx) {
		// TODO Auto-generated method stub
		if(idx == set.length) {
//			System.out.println(Arrays.toString(set));
			process();
			return;
		}
		for (int i = 0; i < friends.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				set[idx] = friends[i];
				permutation(idx+1);
				visited[i] = false;
			}
		}
	}
	private static void process() {
		// TODO Auto-generated method stub
		for (int i = 0; i < command.length; i++) {
			String str = command[i];
			char first = str.charAt(0);
			char third = str.charAt(2);
			char commandIf = str.charAt(3);
			int num = str.charAt(4)-'0';
			int firstIdx = 0;
			int thirdIdx = 0;
			for (int j = 0; j < set.length; j++) {
				if(first == set[j].charAt(0)) firstIdx = j;
				if(third == set[j].charAt(0)) thirdIdx = j;
				
			}
			int diffLen = Math.abs(firstIdx - thirdIdx) -1;
			if(commandIf == '=') {
				if(diffLen == num) continue;
				else return;
			}else if(commandIf =='>') {
				if(diffLen > num) continue;
				else return;
			}else if(commandIf=='<') {
				if(diffLen < num) continue;
				else return;
			}
			
					
		}
		answer++;
	}
}

```

