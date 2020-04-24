## Programmers_숫자야구

>__문제 풀이__
>
>1. 3중 포문을 이용해서 중복이 안되게 가능한 숫자를 만듬.
>2. check() 함수를 통해 strike 갯수, ball의 갯수를 찾아서 조건에 맞는지 확인함



```
import java.util.*;
class Solution {
    static int answer;
    public int solution(int[][] baseball) {
        answer = 0;
        
        for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if (i == j)
					continue;
				for (int k = 1; k <= 9; k++) {
					if (i == k || j == k)
						continue;
					int num = i * 100 + j * 10 + k;
					check(num, baseball);
				}
			}
		}
        
        return answer;
    }
    public void check(int resultNumber, int[][] baseball){
        boolean isRight = true;
		int strike = 0;
		int ball = 0;
		String numbToStr = Integer.toString(resultNumber);

		for (int i = 0; i < baseball.length; i++) {
			String tryBall = Integer.toString(baseball[i][0]);
			for (int j = 0; j < 3; j++) {
				char chr = tryBall.charAt(j);
				if (chr == numbToStr.charAt(j)) {
					strike++;
				} else {
                    System.out.println(Character.toString(chr));
					if (numbToStr.contains(Character.toString(chr))) {
						ball++;
					}
				}
			}
			if (strike != baseball[i][1] || ball != baseball[i][2]) {
				isRight = false;
				break;
			}
			strike = 0;
			ball = 0;
		}

		if (isRight) {
			answer++;
		}
    }
}
```

