## Solution5644_무선충전

>__문제 풀이__
>
>재풀이 시간 : 1시간
>
>1. BC 무선충전기에 대한 row, column, dir, power에 대한 정보를 받기
>2. A,B에 대한 움직임 배열로 받기
>3. 문제에 조건에 따라 시뮬레이션 문제이지만,  T= 11초 일 때, 반으로 나누는 값이 더 큰지, 따로 충전하는것이 큰지에 대해서는 2중 포문을 통해서 해결함
>   1. 2중 포문같은 경우 BC의 입력값이 1 ~ 8이라서 2중포문으로 해결해줌

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5644_무선충전 {
	private static int T, M, A;
	private static int[] moveA; // 사용자 A 이동 정보
	private static int[] moveB; // 사용자 B 이동 정보
	private static int astartX, astartY; // A사용자 초기 위치
	private static int bstartX, bstartY; // B 사용자 초기 위치
	private static Node[] BC; // BC에 대한 정보
	private static int ans; // 최종 결과 값
	private static int[] dx = { 0, -1, 0, 1, 0 }; // 이동 X, 상 우 하 좌
	private static int[] dy = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 이동 정보
			A = Integer.parseInt(st.nextToken()); // BC의 개수

			moveA = new int[M]; // A에 대한 이동 정보 배열
			moveB = new int[M]; // B에 대한 이동 정보 배열

			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < moveA.length; i++) {
				moveA[i] = Integer.parseInt(st.nextToken()); // A 이동 정보 입력
			}

			st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < moveB.length; i++) {
				moveB[i] = Integer.parseInt(st.nextToken()); // B 이동 정보 입력
			}

			BC = new Node[A]; // BC에 대한 정보 입력
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int column = Integer.parseInt(st.nextToken());
				int row = Integer.parseInt(st.nextToken());
				int len = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());
				BC[i] = new Node(row, column, len, power);
			}
			astartX = 1;
			astartY = 1;
			bstartX = 10;
			bstartY = 10;
			ans = Integer.MIN_VALUE;
			process();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase
		System.out.println(sb.toString());

	}// end of main

	// 사용자들이 이동하면서 체크하는 함수
	public static void process() {
		
		//초기에도 충전할 수 있는지 확인해야되니깐 getScore 한번 호출 
		int result = getScore(astartX, astartY, bstartX, bstartY); 
		
		for (int i = 0; i < moveA.length; i++) {
			int naRow = astartX + dx[moveA[i]]; 
			int naColumn = astartY  + dy[moveA[i]];
			
			int nbRow = bstartX + dx[moveB[i]];
			int nbColumn = bstartY  + dy[moveB[i]];
			
			// 이동하면서 얻은 점수 반환
			result += getScore(naRow, naColumn, nbRow, nbColumn);
			astartX = naRow;
			astartY = naColumn;
			bstartX = nbRow;
			bstartY = nbColumn;
			
			
		}
		ans = result;
	}

	// 점수 획득하는 함수
	public static int getScore(int aRow, int aColumn, int bRow, int bColumn) {

		int[][] bcCheck = new int[2][A]; // 두명의 사용자가 BC에 해당하는지 체크하는 함수

		for (int i = 0; i < BC.length; i++) {
			Node n = BC[i];

			if ((Math.abs(aRow - n.row) + Math.abs(aColumn - n.column)) <= n.len) {
				bcCheck[0][i] = n.power; //충전할 수 있으면 충전파워 입력해줌
			}

			if ((Math.abs(bRow - n.row) + Math.abs(bColumn - n.column)) <= n.len) {
				bcCheck[1][i] = n.power;
			}

		}

		int max = 0;
		for(int i=0; i<A; i++) {
			for(int j=0; j<A; j++) {
				int sum = bcCheck[0][i]+bcCheck[1][j];
                
                 // 같은 BC를 이용하는 경우 값을 반으로 나눠줘야한다.
                 // 주의할 점은 한 쪽은 아예 값이 0일수도 있으므로(해당 BC를 이용할 수 없는 위치) 정확히 둘다 같이 이용하고 있는 경우에만 나누어주어야한다.
				if(i == j && bcCheck[0][i] == bcCheck[1][j])
					sum /= 2;
				if(sum > max) max = sum;
			}
		}


		

		return max;
	}
	
    // BC에 대한 정보 담기 위해서 static class 선언
	static class Node {
		int row, column, len, power;

		public Node(int row, int column, int len, int power) {
			super();
			this.row = row;
			this.column = column;
			this.len = len;
			this.power = power;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", len=" + len + ", power=" + power + "]";
		}

	}

}// end of class

```

```java
package solve;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5644_무선충전 {
	private static Node[] BC;	
	private static int ans,A,M;
	private static int[] aMove;
	private static int[] bMove;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			aMove = new int[M]; //a의 움직임
			bMove = new int[M]; //b의 움직임

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < aMove.length; i++) {
				aMove[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < bMove.length; i++) {
				bMove[i] = Integer.parseInt(st.nextToken());
			}

			BC = new Node[A];

			for (int i = 0; i < BC.length; i++) { //bc에 대한 정보 저장
				st = new StringTokenizer(br.readLine(), " ");
				int column = Integer.parseInt(st.nextToken()) - 1;
				int row = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken());
				int power = Integer.parseInt(st.nextToken());

				BC[i] = new Node(row, column, c, power);
			}
			
			ans = Integer.MIN_VALUE; // 최대값을 구하기 위해 최소값으로 설정
			move();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());

	}// end of main
	private static void move() {
		// TODO Auto-generated method stub
		int aStartRow = 0; //A의 처음 위치
		int aStartColumn = 0;
		int bStartRow = 9; //B의 처음 위치
		int bStartColumn = 9;
		
		ans = getScore(aStartRow, aStartColumn, bStartRow, bStartColumn);
		
		for (int i = 0; i < aMove.length; i++) {
			int newARow = aStartRow + dx[aMove[i]];
			int newAColumn = aStartColumn + dy[aMove[i]];
			
			int newBRow = bStartRow + dx[bMove[i]];
			int newBColumn = bStartColumn + dy[bMove[i]];
			
			ans += getScore(newARow, newAColumn, newBRow, newBColumn);
			
			aStartRow = newARow;
			aStartColumn = newAColumn;
			bStartRow = newBRow;
			bStartColumn = newBColumn;
			
			
		}
	}
	public static int getScore(int aRow, int aColumn, int bRow, int bColumn) {

		int[][] bcCheck = new int[2][A]; // 두명의 사용자가 BC에 해당하는지 체크하는 함수

		for (int i = 0; i < BC.length; i++) {
			Node n = BC[i];

			if ((Math.abs(aRow - n.row) + Math.abs(aColumn - n.column)) <= n.c) {
				bcCheck[0][i] = n.power;
			}

			if ((Math.abs(bRow - n.row) + Math.abs(bColumn - n.column)) <= n.c) {
				bcCheck[1][i] = n.power;
			}

		}

		int max = 0;
		for(int i=0; i<A; i++) {
			for(int j=0; j<A; j++) {
				int sum = bcCheck[0][i]+bcCheck[1][j];
                
                 // 같은 BC를 이용하는 경우 값을 반으로 나눠줘야한다.
                 // 주의할 점은 한 쪽은 아예 값이 0일수도 있으므로(해당 BC를 이용할 수 없는 위치) 정확히 둘다 같이 이용하고 있는 경우에만 나누어주어야한다.
				if(i == j && bcCheck[0][i] == bcCheck[1][j])
					sum /= 2;
				if(sum > max) max = sum;
			}
		}


		

		return max;
	}
	private static int[] dx = { 0, -1, 0, 1, 0 };
	private static int[] dy = { 0, 0, 1, 0, -1 }; 

	static class Node {
		int row, column, c, power;

		public Node(int row, int column, int c, int power) {
			super();
			this.row = row;
			this.column = column;
			this.c = c;
			this.power = power;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", c=" + c + ", power=" + power + "]";
		}

	}
}// end of class

```



