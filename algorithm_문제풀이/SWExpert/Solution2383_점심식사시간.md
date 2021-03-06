## Solution2383_점심식사시간

>__문제 풀이__
>
>테스트케이스 44, 48개까지 맞추고 로직을 새로 수정해서 간신히 pass 했다.
>
>1. 중복조합을 통해서 계단 2개에 대해서 사람들이 올 수 있는 모든 경우의 수를 정함
>
>2. 우선순위 큐를 생성해서 time, 몇번째의 계단, 상태를 설정 (상태 => -1 : 도착 전 , 0 : 도착, 1 : 내려가기 시작)
>
>3. 우선순위 큐의 compareTo 함수를 보면 시간순서대로 정렬하되 시간이 같으면 상태번호가 높은 순으로 정렬했다. 그 이유는 가장 먼저 계단에 도착하는거에 대해서 로직을 실행하되 한 계단에 3명의 인원만 내려갈 수 있기 때문이다.
>
>4. 주의할 점 : 계단이 도착한 후 1분 이후에 내려가기 시작
>
>   ​					계단에 대기하고 있는 상태에서 내려갈 수 있으면 바로 내려가기 시작.
>
>

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 *  문제 풀이 :
 *  사람이 갈 수 있는 계단입구에 대해서 배치
 *  배치 한 후 모두 내려왔으면 최소값 계산
 *  계단은 무조건 2개, 위치가 겹치지 않는다
 */
public class Solution2383_점심식사시간 {
	private static int[][] map;
	private static int N, ans;
	private static ArrayList<Node> people;
	private static Node[] stair;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ans = Integer.MAX_VALUE;
			map = new int[N][N];
			StringTokenizer st;
			people = new ArrayList<Node>();
			stair = new Node[2];
			int idx = 0;
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1)
						people.add(new Node(i, j));
					if (map[i][j] > 1)
						stair[idx++] = new Node(i, j);
				}
			}
			set = new int[people.size()];
			splitPeople(0);
			System.out.println("#" + tc + " " + ans);
		} // end of tc
		
	}// end of main

	private static int[] set;
	private static PriorityQueue<Pair> pq;

	private static void splitPeople(int dep) {
		// TODO Auto-generated method stub
		if (dep == people.size()) {
			pq = new PriorityQueue<>();
			for (int i = 0; i < set.length; i++) {
				int row = people.get(i).row;
				int column = people.get(i).column;
				int stairRow = stair[set[i]].row;
				int stairColumn = stair[set[i]].column;
				int diff = Math.abs(stairRow - row) + Math.abs(stairColumn - column);
				pq.add(new Pair(diff, set[i], -1));
			}
			move(); // 어디의 계단으로 갈지에 대해서 정하고 실제 움직임
			return;
		}

		for (int i = 0; i < stair.length; i++) {
			set[dep] = i;
			splitPeople(dep + 1);
		}

	}

	private static void move() {
		// TODO Auto-generated method stub
		int[] inStair = new int[stair.length];
		int time = 0;
		while (!pq.isEmpty()) {
			time++;
			
			while(!pq.isEmpty()) {
				Pair front = pq.peek();
				if(front.time != time) break;
				pq.poll();
				int myStair = front.stair;
				
				if(front.status != 1) { //계단 아직 안감
					//계단에 내려갈 수 있는지 체크
					if(inStair[myStair] < 3) {
						int nTime = 0;
						if(front.status == -1) {
							nTime = front.time + 1 + map[stair[myStair].row][stair[myStair].column];
						}else if(front.status == 0) { // 입구에 대기하고 있으면 
							nTime = front.time + map[stair[myStair].row][stair[myStair].column];
						}
						pq.add(new Pair(nTime, front.stair, 1));
						inStair[myStair]++;					
					}else { // 계단이 포화라서 못내려가는 상태라면 상태를 0으로 변화
						pq.add(new Pair(front.time +1, myStair, 0));
						
					}					
				}else {
					inStair[myStair]--;
				}
			}
				
		}
		ans = ans > time ? time : ans;
	}

	static class Node{
		int row, column;
	

		public Node(int row, int column) {
			super();
			this.row = row;
			this.column = column;
		}		

	}
	
	 static class Pair implements Comparable<Pair> {
	        int time;
	        int stair;
	        int status;
	 
	        public Pair(int time, int stair, int status) {
	            super();
	            this.time = time;
	            this.stair = stair;
	            this.status = status;
	        }
	 
	        @Override
	        public int compareTo(Pair o) {
	            // TODO Auto-generated method stub
	            if (this.time == o.time) {
	                return o.status - this.status;
	            } else {
	                return this.time - o.time;
	            }
	        }
	 
	    }

}// end of class

```

```java
package reTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution2383_점심식사시간 {
   private static int N , ans;
   private static int[][] map;
   private static ArrayList<Node> sList;
   private static ArrayList<People> people;
   private static int[] set;
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      StringBuilder sb = new StringBuilder();
      for (int testCase = 1; testCase <= T; testCase++) {
         N = Integer.parseInt(br.readLine());
         ans = Integer.MAX_VALUE;
         map = new int[N][N];
         sList = new ArrayList<Node>();
         people = new ArrayList<People>();
         StringTokenizer st ;
         for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < map.length; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
               if(map[i][j] > 1) sList.add(new Node(i, j , map[i][j]));
               if(map[i][j] == 1) people.add(new People(i,j, 0, 0 ));
            }
         }
         
         set = new int[people.size()];
         select(0);
         sb.append("#").append(testCase).append(" ").append(ans).append("\n");
         
      }
      System.out.println(sb.toString());
   }//end of main
   
   private static void select(int idx) {
      // TODO Auto-generated method stub
      if(idx == set.length) {
         solution();
         return;
      }
      
      for (int i = 0; i < sList.size(); i++) {
         set[idx] = i;
         select(idx+1);
      }
   }

   private static void solution() {
      // TODO Auto-generated method stub
      int[] st = new int[2];
      PriorityQueue<Stair> pq = new PriorityQueue<>(new Comparator<Stair>() {

         @Override
         public int compare(Stair o1, Stair o2) {
            // TODO Auto-generated method stub
            if(o1.time == o2.time) {
               return o2.status - o1.status;
            }else {
               return o1.time - o2.time;
            }
         }
      });
      for (int i = 0; i < set.length; i++) {
         int num = set[i];
         int diffLen = Math.abs(people.get(i).row - sList.get(num).row) + Math.abs(people.get(i).column - sList.get(num).column);
         pq.add(new Stair(num, diffLen, -1));
         
      }
      
      int time = 0;
      while(!pq.isEmpty()) {
         time++;
         
         while(!pq.isEmpty()) {
            Stair s = pq.peek();   
            if(time != s.time) break;
            pq.poll();
            int stair = s.stairNum;
            
            if(s.status != 1) {
               if(st[stair] < 3) {
                  int nTime = 0;
                  if(s.status == -1) {
                     nTime = s.time + 1  + map[sList.get(stair).row][sList.get(stair).column];                     
                  }else if(s.status == 0) {
                     nTime = s.time + map[sList.get(stair).row][sList.get(stair).column];
                  }
                  
                  pq.add(new Stair(stair, nTime, 1));
                  st[stair]++;
                  
               }else {
                  pq.add(new Stair(stair, s.time +1 , 0));
               }       
               
               
            }else {
               st[stair]--;
            }
            
         }
      }
      
      ans = ans > time ? time : ans;
   }
   
   static class Stair{
      int stairNum, time, status;

      public Stair(int stairNum, int time, int status) {
         super();
         this.stairNum = stairNum;
         this.time = time;
         this.status = status;
      }

      @Override
      public String toString() {
         return "Stair [stairNum=" + stairNum + ", time=" + time + ", status=" + status + "]";
      }
      
      
      
      
   }

   static class People{
      int row, column, time, status;

      public People(int row, int column, int time, int status) {
         super();
         this.row = row;
         this.column = column;
         this.time = time;
         this.status = status;
      }

      @Override
      public String toString() {
         return "People [row=" + row + ", column=" + column + ", time=" + time + ", status=" + status + "]";
      }
      
      
   }
   
   static class Node{
      int row, column, len;

      public Node(int row, int column, int len) {
         super();
         this.row = row;
         this.column = column;
         this.len = len;
      }

      @Override
      public String toString() {
         return "Node [row=" + row + ", column=" + column + ", len=" + len + "]";
      }
      
      
   }
}//end of class
```

