## Solution5644_무선충전

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



