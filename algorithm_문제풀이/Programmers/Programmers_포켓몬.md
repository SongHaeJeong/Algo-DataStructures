## Programmers_폰켓몬

```java
public class PhoneKetMon {
   public int solution(int[] nums){
       Queue<Integer> que = new LinkedList<>();
       int length = nums.length;
       int getPocket = length/2;
       for(int i=0; i<length; i++){
           que.offer(nums[i]);
      }
       System.out.println(que);

       int target=0;
       int cnt=0;
       //큐에서 꺼내서 비교할 때 서로 달라야하고, n/2 갯수를 넘으면 종료
       while(!que.isEmpty()){
           target = que.poll();
           System.out.println("target1 : "+target);
           cnt++;
           System.out.println("cnt1 : "+cnt);
           if(cnt == getPocket){
               return cnt;
          }
           while(!que.isEmpty()){
               if(que.peek() == target){
                   target = que.peek();
                   System.out.println("target2 : "+target);
                   que.poll();
              }else {
                   target = que.peek();
                   cnt++;
                   System.out.println("cnt2 : "+cnt);
                   que.poll();
                   if(cnt == getPocket){
                       return cnt;
                  }
              }
          }
      }
       System.out.println(cnt);
           return cnt;
  }
}
```