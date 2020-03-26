## Solution2477_차량 정비소

>__문제 풀이__
>
>2시간 걸려서 풀었다. 기존의 Queue 하나를 이용해서 모든 상태 변화에 따른 처리를 해주려고 했으나 시간 처리가 꼬여버려서 각 상태 별로 Queue가 존재했고 모든 점검을 마친 상태에 대해서는 ArrayList<>로 처리해줬다.
>
>
>
>1. 내부 클래스 Node를 만들어주고 a_time, b_time, c_num, a, b 를 만들어줌
>
>   - a_time : 접수 창구에서의 흐른 시간, b_time : 정비 창구에서의 흐른 시간, c_num : 멤버의 번호, a : 접수 창구를 이용한 사람의 넘버, b : 정비 창구를 이용한 사람의 넘버
>
>2. while() 문을 통해 필요한 시뮬레이션 돌림
>
>   1.  모든 인원에 대해서 접수 대기에 이동할 수 있는지 판별하고 이동 시킴
>
>   2.  정비 창구에 대한 로직 처리 -> 접수 창구에 대한 로직을 먼저 처리하지 않는 이유는 접수 창구에서 시간이 다되면 정비 창구로 바로 이동하는 것이 아닌 다음턴에 이동하기 때문에 정비 창구 로직 먼저 계산
>
>   3.  접수 창구에 대한 로직 처리
>
> ​     
>
>__코드를 보면서 이해하면 더 쉽게 이해 될 것이다.__





```java
package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution2477_차량정비소 {
	private static int N, M, K, A, B;
	private static int[] ai;
	private static int[] bj;
	private static int[] customer;
	private static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 창구의 수
			M = Integer.parseInt(st.nextToken()); // 정비 창구의 개수
			K = Integer.parseInt(st.nextToken()); // 차량 정비소를 방문한 고객의 수
			A = Integer.parseInt(st.nextToken()); // 고객이 이용한 접수 창구번호 A
			B = Integer.parseInt(st.nextToken()); // 고객이 이용한 장비 창구번호 B

			ai = new int[N + 1]; // 접수 창구에 대한 정보 1부터 시작이니깐 N + 1
			bj = new int[M + 1]; // 장비 창구에 대한 정보 1부터 시작이니깐 N + 1
			customer = new int[K + 1]; // 고객 도착시간에 대한 정보

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				ai[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < M + 1; i++) {
				bj[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i < K + 1; i++) {
				customer[i] = Integer.parseInt(st.nextToken());
			}

			ans = 0; // 최종 답 출력			
			solution();
			if (ans == 0) {
				ans = -1;
			}

			System.out.println("#" + tc + " " + ans);

		}//end of testCase

	}//end of main
	

	private static void solution() {
		// TODO Auto-generated method stub
		Queue<Customer> waitAi = new LinkedList<>(); //접수 창구를 기다리는 정보
		Queue<Customer> waitBj = new LinkedList<>(); //정비 창구를 기다리는 정보
		Customer[] visitedAj = new Customer[N + 1]; // 접수 창구
		Customer[] visitedBj = new Customer[M + 1]; // 정비 창구
		List<Customer> end = new ArrayList<>(); //마지막

		int cnt = 0;
		int arrived = 0;

		while (true) {
			
			// 모든 인원에 대해서 처리 했으면 중지
			if (cnt == K) {
				break;
			}
			
			// 모든 인원에 대해서 접수 -> 접주 대기로 이동했으면 검사할 필요 없음
			if (arrived < K) {
				for (int i = 1; i < K + 1; i++) {
					if (customer[i] == 0) {
						waitAi.add(new Customer(0, 0, i, 0, 0));
						arrived++;
					}
					customer[i]--;
				}
			}
			
			
			// 정비 창구에 대한 로직 처리
			// 정비 창구에 대한 로직을 먼저 처리하는 이유는 문제를 보면 접수 창구에서 시간이 다되면 정비 창구로 바로 이동하는 것이 아닌 다음턴에 이동하기 떄문
			for (int i = 1; i < M + 1; i++) {
				if (visitedBj[i] == null && !waitBj.isEmpty()) {
					visitedBj[i] = waitBj.poll();
					visitedBj[i].b = i;
				}
				if (visitedBj[i] != null) {
					visitedBj[i].b_time++;
					if (visitedBj[i].b_time == bj[i]) {
						end.add(visitedBj[i]);
						visitedBj[i] = null;
						cnt++;
					}
				}
			}
			
			// 접수 창구에 대한 로직 처리
			for (int i = 1; i < N + 1; i++) {
				if (visitedAj[i] == null && !waitAi.isEmpty()) {
					visitedAj[i] = waitAi.poll();
					visitedAj[i].a = i;
				}
				if (visitedAj[i] != null) {
					visitedAj[i].a_time++;
					if (visitedAj[i].a_time == ai[i]) {
						waitBj.add(visitedAj[i]);
						visitedAj[i] = null;
					}
				}
			}

		}
		
		//마지막에 A, B 와 같은 정보가 있을 시 더해줌
		for (int i = 0; i < end.size(); i++) {
			if (end.get(i).a == A && end.get(i).b == B) {
				ans += end.get(i).c_num;
			}
		}
	}//end of solution


	static class Customer {
		int a_time; 
		int b_time;
		int c_num;
		int a;
		int b;

		public Customer(int a_time, int b_time, int c_num, int a, int b) {
			this.a_time = a_time;
			this.b_time = b_time;
			this.c_num = c_num;
			this.a = a;
			this.b = b;
		}

		@Override
		public String toString() {
			return "Customer [a_time=" + a_time + ", b_time=" + b_time + ", c_num=" + c_num + ", a=" + a + ", b=" + b
					+ "]";
		}
		
		
	}
}//end of class
	
```





