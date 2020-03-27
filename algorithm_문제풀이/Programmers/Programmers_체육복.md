## Programmers_체육복

>문제 풀이 
>
>문제에 나오는 조건대로 풀어줬다. 
>
>그나마 까다로웠던 것은 여유분을 가지고 있는 학생이 있지만 자신이 잃어버렸을 때는 다른 사람에게 양도를 못해주는 조건을 자세히 안읽고 지나가서 처음에 2개빼고 맞았다.
>
>문제를 자세히 읽어야

```java
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        boolean[] student = new boolean[n+1];

        for(int i = 1; i< student.length; i++){
            student[i] = true;            
        }

        for(int i = 0; i < lost.length; i++){
            student[lost[i]] = false;
        }

        for(int i =0; i < reserve.length; i ++){
            if(!student[reserve[i]]){
                student[reserve[i]] = true;
                reserve[i] = 0;
            }
        }


        for(int i = 0; i <reserve.length; i++){  
            if(reserve[i] > 0){
                     if(student[reserve[i]]){

                if(reserve[i]-1 >=1 && !student[reserve[i]-1]){
                    student[reserve[i]-1] = true;
                    continue;
                }

                if(reserve[i]+1 <= n && !student[reserve[i] +1]){
                    student[reserve[i] + 1]= true;
                    continue;
                }         


            }else{
                student[reserve[i]] = true;
            }  
            }


        }

        for(int i = 1; i < student.length; i++){
            if(student[i]) answer++;
        }



        return answer;
    }
}	
```

