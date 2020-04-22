## Programmers_카펫

>__문제 풀이__
>
>Red가 4개 , Brown : 14개 일 때  => 가로 6 세로 3의 크기가 나온다
>
>Red가 4개 , Brown : 12개 일 때 => 가로 4, 세로 4의 크기가 나온다
>
>그림을 그려서 보면 규칙이 존재해서 문제를 풀어봤더니 테스트케이스 6,7,8번에 걸려 다른 규칙을 찾아보려고 했다
>
>이 후, RED의 가로길이 *2 + Red 세로길이 * 2 + 4 가 Brown의 갯수와 같다는 규칙을 찾아 문제를 해결함 
>
>첫번째 코드는 6,7,8번이 맞지 않는 코드이고 두번째가 정답인 코드이다.
>
>

```java
class Solution {
    public int[] solution(int brown, int red) {
        int[] answer = new int[2];        
        // Brown : 8, Red 1 => 최소의 크기 가로 3 세로 3
        // 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다 
        // 중앙에는 빨간색, 테두리 1줄은 갈색
        
       int row = 3;
		int column = 3;
		int num = brown +red;
		int redStart = red;
		int mod = 2;
		while (true) {
			if(red+2 >= column && num == (red+2) * column) {
				answer[0] = red+2;
				answer[1] = column;
				break;
			}else {
				red = redStart;				
				column++;
				red = red/mod++;				
			}
		
		}
        
        
        return answer;
    }
}
```



```java
class Solution {
    public int[] solution(int brown, int red) {
             
        // Brown : 8, Red 1 => 최소의 크기 가로 3 세로 3
        // 가로 길이는 세로 길이와 같거나, 세로 길이보다 깁니다 
        // 중앙에는 빨간색, 테두리 1줄은 갈색
        
          int width = 0;
        int height = 0;
        
        for(int i=1; i<=red/2+1; i++) {
            width = i;            
            height = (red%i==0) ? red/i:red/i+1;
            
            if(2*width + 2*height + 4 == brown) break;
            
        }
            
        int[] answer = {Math.max(width, height)+2, Math.min(width, height)+2};
        
        return answer;
        
        
       
    }
}
```

