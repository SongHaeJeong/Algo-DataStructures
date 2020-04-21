## Programmers_모의고사

>__문제 풀이__
>
>완탐을 이용해서 문제를 해결함 (두가지 비슷한 버전으로 풀이 함)



```java
import java.util.Arrays;
class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        for(int i = 0 ; i < answers.length; i++){
            if(answers[i] == a[i% (a.length)]) aCount++;
            if(answers[i] ==  b[i% (b.length)] ) bCount++;
            if(answers[i] == c[i % (c.length)]) cCount++;
        }
        int number = 0;
        int[] arr = new int[3];
        arr[0] = aCount;
        arr[1] = bCount;
        arr[2] = cCount;
        Arrays.sort(arr);
        if(arr[2] == aCount) number++;
        if(arr[2] == bCount) number++;
        if(arr[2] == cCount) number++;
        
        

        
        answer = new int[number];
      
        if(number == 1){
            if(arr[2] == aCount){
                answer[0] = 1;
            }else if(arr[2] == bCount){
                answer[0] = 2;
                
            }else{
                answer[0] = 3;
            }
         }else if(number == 2){
            if(arr[1] == aCount && arr[2] == bCount){
                answer[0] = 1;
                answer[1] =2;
            }else if(arr[1] == bCount && arr[2] == cCount){
                answer[0] = 2;
                answer[1] = 3;
            }else{
                answer[0] = 1;
                answer[1] = 3;
            }
        }else{
            answer[0]= 1;
            answer[1] = 2;
            answer[2] = 3;
        }
        
        
        
        
        return answer;
    }
}
```

```java
import java.util.*;

class Solution {
    static class Node{
        int number, count;
        
        public Node(int number, int count){
            this.number = number;
            this.count = count;
        }
    }
    public int[] solution(int[] answers) {
        int[] answer = {};
        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        int aCount = 0;
        int bCount = 0;
        int cCount = 0;
        for(int i = 0 ; i < answers.length; i++){
            if(answers[i] == a[i% (a.length)]) aCount++;
            if(answers[i] ==  b[i% (b.length)] ) bCount++;
            if(answers[i] == c[i % (c.length)]) cCount++;
        }
        int number = 0;
        Node[] arr = new Node[3]; // 몇번인원이고 몇개의 정답을 맞췄는지 판단하는 배열
        arr[0] = new Node(1, aCount);
        arr[1] = new Node(2, bCount);
        arr[2] = new Node(3, cCount);
        
        Arrays.sort(arr, new Comparator<Node>() {
           public int compare(Node o1, Node o2){
               if(o1.count != o2.count)  return o1.count - o2.count;
               else return 0;
               
           }
        });
        
        
        int count = 0;
        int num = arr[2].count;
        for(int i =arr.length-1 ; i >=0 ; i--){
                if(arr[i].count == num) count++;
                
        }
        
        answer = new int[count];
        int arrIdx = 2;
        for(int i = answer.length-1; i >=0 ; i--){
            answer[i] = arr[arrIdx--].number; 
        
        }       
        
        
        
        return answer;
    }
}
```

