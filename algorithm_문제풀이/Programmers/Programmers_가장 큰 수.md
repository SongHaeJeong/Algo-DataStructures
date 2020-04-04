## Programmers_가장 큰 수

>__문제 풀이__
>
>1. 문제를 읽고 순열을 숫자를 조합하고 가장 큰 수를 구하려고 했으나 시간초과 및 틀렸다.
>2. 이 후, comparator를 이용해서 풀었다.
>3. 주의할 점 : [0,0,0,0] 이라는 입력이 주어지면 최종값은 0을 출력해야됨.

```java
* Pass Code * 
import java.util.*;
class Solution {
	public String solution(int[] numbers) {
	        String answer = "";
	        String[] num = new String[numbers.length];
	        for(int i = 0; i < num.length ; i ++){
	            num[i] = String.valueOf(numbers[i]);
	        }
	        Arrays.sort(num, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return (o2 + o1).compareTo(o1 + o2);
				}
			});
	        
	        if(num[0].equals("0")) return "0";
	        else {
	        	for(int i = 0 ; i < num.length;i++) {
	        		answer += num[i];
	        	}
	        }
	        return answer;
 }

}
```



```java
class Solution {
    private static String answer ;
    private static boolean[] visited;
    private static String[] set;
    private static Long ans;
    public String solution(int[] numbers) {
        answer = "";
        visited = new boolean[numbers.length];
        set = new String[numbers.length];
        ans = (long)0;
        permutation(0 , numbers.length , numbers);
        answer = ans +"";
        return answer;
    }
    

    public void permutation(int idx, int len , int[] numbers){
        if(idx == len ){
            String temp = "";
            for(int i = 0; i < set.length; i++){
                temp += set[i];
            }
            
            ans = ans < Long.parseLong(temp) ? Long.parseLong(temp) : ans;            
            return;
        }
        
        for(int i = 0; i < numbers.length ;i ++){
            if(!visited[i]){
                visited[i] = true;
                set[idx] = numbers[i] + "";
                permutation(idx +1 , len , numbers);
                visited[i] = false;
            
            }
        }
    }

}
```



