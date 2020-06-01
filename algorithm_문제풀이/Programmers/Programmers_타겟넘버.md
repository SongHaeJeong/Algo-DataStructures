## Programmers_타겟넘버

>__문제 풀이__
>
>1. DFS를 활용함 (배열, 인덱스, 타겟 숫자)
>2. numbers[idx] * 1 , numbers[idx] *= -1을 통해서 모든 경우 검사함



```java
package solve;

public class Programmers_타겟넘버 {
	public static void main(String[] args) {
		int[] numbers = {1,1,1,1,1};
		int target = 3;
		System.out.println(solution(numbers, target));
	}

	static int answer;

	public static int solution(int[] numbers, int target) {
		answer = 0;
		dfs(numbers, 0, target);
		return answer;
	}

	public static void dfs(int[] numbers, int idx, int target) {
		if (idx == numbers.length) {
			int num = 0;
			for (int i = 0; i < numbers.length; i++) {
				num += numbers[i];
			}
			if (target == num)
				answer++;

			return;

		}

		numbers[idx] *= 1;
		dfs(numbers, idx + 1, target);

		numbers[idx] *= -1;
		dfs(numbers, idx + 1, target);

	}
}

```

