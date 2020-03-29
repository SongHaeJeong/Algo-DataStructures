## Programmers_기능개발

>풀이 방법
>
>progress 함수의 순서대로 배포가 가능하고 앞에꺼를 배포할 때 뒤에 개발이 다 되어 있는 경우에는 같이 배포한다. 배포가 일어날 때 마다 같이 배보되는 수를 배열에 집어 넣고 최종적으로 출력
>
>1. queue를 통해서 개발을 진행하고
>2. 개발이 끝난 경우 list에 담아서 배포되는 수를 저장했다



```java
import java.util.*;

class Solution {
    static class Pair{
     	int progress, speed, number;

		public Pair(int progress, int speed, int number) {
			this.progress = progress;
			this.speed = speed;
			this.number = number;
		}

		@Override
		public String toString() {
			return "Pair [progress=" + progress + ", speed=" + speed + "]";
		}
		
    }
    public int[] solution(int[] progresses, int[] speeds) {
     	int[] answer = {};
		ArrayList<Integer> list = new ArrayList<Integer>();
		Queue<Pair> queue = new LinkedList<Pair>();

		for (int i = 0; i < progresses.length; i++) {
			queue.add(new Pair(progresses[i], speeds[i], i));
		}
		int idx = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			int cnt = 0;
			boolean flag = false;
			for (int i = 0; i < size; i++) {
				Pair p = queue.poll();

				if (p.progress >= 100 && p.number == idx) {
					idx++;
					flag = true;
					cnt++;
				} else {
					queue.add(new Pair(p.progress + p.speed, p.speed, p.number));
				}

			}
			if (flag)
				list.add(cnt);
		}

		answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
    }
}
```

