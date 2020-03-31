## Solution2383_점심식사시간

>테스트케이스 44개에서 틀렸다.... 다시 한 번 풀어봐야되겠다
>
>

```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 *  문제 풀이 :
 *  사람이 갈 수 있는 계단입구에 대해서 배치
 *  배치 한 후 모두 내려왔으면 최소값 계산
 */
public class Solution2383_점심식사시간 {
	private static int N, ans;
	private static int[][] map;
	private static ArrayList<Node> people;
	private static Node[] stairs;
	private static int[] set;
	private static int[] peopleSet;	 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			ans = Integer.MAX_VALUE; // 최소 시간을 출력하기 위한 변수
			N = Integer.parseInt(br.readLine().trim()); // 맵의 크기
			map = new int[N][N]; // 사람과 계단 정보 표시한 배열
			people = new ArrayList<Node>(); // 사람 위치에 대한 정보 저장
			StringTokenizer st;			
			stairs = new Node[2]; // 계단에 대한 정보 저장
			int idx = 0;
			int cnt = 0;
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 정보 입력
					if(map[i][j] == 1) {
						cnt++;
						people.add(new Node(i,j,-1,0));
					}
					else if(map[i][j] > 1) stairs[idx++] = new Node(i,j,-1,map[i][j]); 
				}
			}
			
			peopleSet = new int[cnt];
			for(int i = 0; i < peopleSet.length; i++) {
				peopleSet[i] = i;
			}
			
			for(int i = 0; i < peopleSet.length; i++) {
				set = new int[i];
				combination(0, 0, i);
			}
			
			process(-1);
			
			
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		} // end of testCase
		System.out.println(sb.toString());
	}// end of main
	
	
	private static void combination(int idx, int k, int s) {
		// TODO Auto-generated method stub
		if(idx == s) {
			process(s);
			return;
		}
		for(int i = k ; i < peopleSet.length; i++) {
			set[idx] = peopleSet[i];
			combination(idx+1, i+1, s);
		}
	}


	private static void process(int num) {
		boolean[] splitTeam = new boolean[peopleSet.length];
		if(num != 0) {
			for(int i = 0; i < set.length; i ++) {
				splitTeam[set[i]] = true; // true인 인덱스가 1번 입구 false인 인덱스가 2번 입구
			}
			
		}else if(num == 0) {
			for (int i = 0; i < splitTeam.length; i++) {
				splitTeam[i] = true;
			}
		}
		Queue<Integer> firstStair = new LinkedList<Integer>(); // 첫 번째 계단
		Queue<Integer> secondStair = new LinkedList<Integer>(); // 두 번째 계단
		Queue<Node> waitStair = new LinkedList<Node>(); //세 번째 계단
		
		for(int i = 0; i < splitTeam.length; i ++) {
			if(splitTeam[i]) waitStair.add(new Node(people.get(i).row, people.get(i).column, 0, 0));
			else waitStair.add(new Node(people.get(i).row, people.get(i).column, 1, 0));
		}
		int time = 0;
		
		while(!waitStair.isEmpty() || !firstStair.isEmpty() || !secondStair.isEmpty()) {
			time++;
			
			if(!firstStair.isEmpty()) {
				int size = firstStair.size();
				for(int i = 0; i < size; i++) {
					int desendTime = firstStair.poll();
					if(desendTime < stairs[0].time ) {
						firstStair.add(desendTime+1);
					}
						
					
				}
			}
			
			if(!secondStair.isEmpty()) {
				int size = secondStair.size();
				for(int i = 0; i< size; i++) {
					int desendTime = secondStair.poll();
					if(desendTime < stairs[1].time) {
						secondStair.add(desendTime+1);
					}
				}
			}
			
			if(!waitStair.isEmpty()) {
				
				int size = waitStair.size();
				
				for(int i = 0; i < size ; i++) {
					Node n = waitStair.poll();
					if(n.selelctNum == 0) {
						if(Math.abs(stairs[0].row - n.row) + Math.abs(stairs[0].column - n.column) <= time && firstStair.size() < 3) {
							firstStair.add(0);
						}else {
							waitStair.add(n);
						}
						
						
					}else if(n.selelctNum == 1){
						if(Math.abs(stairs[1].row - n.row) + Math.abs(stairs[1].column - n.column) <= time && secondStair.size() < 3) {
							secondStair.add(0);
						}else {
							waitStair.add(n);
						}
					}
				}
				
				
			}
			
			
			
			
			
			
		}
		
		ans = ans > time ? time : ans;
		
		
	}

	static class Node{
		int row, column,selelctNum,time; 

		public Node(int row, int column, int selectNum , int time) {
			super();
			this.row = row;
			this.column = column;
			this.selelctNum = selectNum;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", selelctNum=" + selelctNum + ", time=" + time + "]";
		}
		
		

	
		
	}
}// end of class

```

