## Solution5648_원자소멸시물레이션

>풀이 방식
>
>1. 문제를 잘 읽지 못해서 처음 23개만 맞고 계속 틀리길래 다시 읽으니깐 0.5초에 만나는 것을 계산해줘야됨
>2. 그래서 기존의 원자크기 위치 + 1000 해줬던 걸 해결하기 위해서 *2 해줌
>3. 배열의 크기가 4001 2차원 배열을 통해 Main문에서 계속 생성해주니깐 런타임에러뜸
>4. 그래서 이동해서 배열의 크기를 벗어날 때 0 값으로 만들어주고 이차원 배열 map에 대해서 한번만 선언시킴
>
>

```java
package test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5648_원자소멸시뮬레이션{
	private static int N, ans;
	private static int[][] map = new int[4001][4001];
	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static Queue<Node> atom;
	private static ArrayList<Node> delete;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine().trim());

			atom = new LinkedList<Node>();
			delete = new ArrayList<Node>();
			// N만큼 원자의 위치 입력 받기
			// dir 0 : 상, 1 : 하, 2 : 좌, 3 : 우
			StringTokenizer st;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				map[(x + 1000)* 2][(y+1000) * 2] = k;
				atom.add(new Node((x+1000) *2 ,(y+1000) *2, dir, k));
			}
			ans = 0;
			process();
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");

		} // end of testCase
		System.out.println(sb.toString());
	}// end of main

	private static void process() {
		// TODO Auto-generated method stub
		while (!atom.isEmpty()) {

			ArrayList<Node> list = new ArrayList<>(); // 움직였을 때 위치
			int size = atom.size();
			here : for (int i = 0; i < size; i++) {
				Node n = atom.poll();
				
				for (Node node : delete) {
					if(n.x == node.x && n.y == node.y) {
						ans += n.energy;
						continue here;
					}
				}
				
				int nx = n.x + dx[n.dir];
				int ny = n.y + dy[n.dir];
				if (nx < 0 || ny < 0 || nx >= 4001 || ny >= 4001) {
					
					map[n.x][n.y]= 0; 
					continue;
				}
				// 원자별로 먼저 이동
				map[n.x][n.y] = 0;
				list.add(new Node(nx, ny, n.dir, n.energy));
				atom.add(new Node(nx, ny, n.dir, n.energy));
				map[nx][ny] += n.energy;
			}

			delete = new ArrayList<>(); // 중복된거 찾기
			// 충돌하는지 검사
			Collections.sort(list);
			Node n = null;
			for (Node node : list) {
				if(n== null) {
					n = node;
					continue;
				}
				
				if(n.x == node.x && n.y == node.y) {
					delete.add(n);
				}else {
					n = node;
				}
				
			}

		}

	}

	static class Node implements Comparable<Node>{
		int x, y, dir, energy;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int dir, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.energy = energy;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", dir=" + dir + ", energy=" + energy + "]";
		}

		@Override
		public int compareTo(Node o) {
			if(this.x != o.x ) {
				return this.x - o.x;
			}else if(this.x == o.x && this.y != o.y) {
				return this.y - o.y;
			}
			return 1;
		}

	}
}// end of class

```

