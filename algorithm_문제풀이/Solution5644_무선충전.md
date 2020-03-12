## Solution5644_무선충전

```
package test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution5644_무선충전 {

	private static class Node implements Comparable<Node> {
		int x, y, length, capacity;

		Node(int x, int y, int length, int capacity) {
			this.x = x;
			this.y = y;
			this.length = length;
			this.capacity = capacity;
		}

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Node arg0) {
			return arg0.capacity - this.capacity;
		}
	}

	static int size, bcNum, result;
	static Node A, B;
	static Node[] bc;
	static int[] dx = { 0, -1, 0, 1, 0 }; // 상, 우, 하, 좌
	static int[] dy = { 0, 0, 1, 0, -1 };
	static int[][] move;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tnum = stoi(br.readLine());

		for (int t = 1; t <= tnum; t++) {
			st = new StringTokenizer(br.readLine());
			size = stoi(st.nextToken());
			bcNum = stoi(st.nextToken());
			result = 0;
			move = new int[2][size];
			bc = new Node[bcNum];
			A = new Node(1, 1);
			B = new Node(10, 10);

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < size; j++) {
					move[i][j] = stoi(st.nextToken());
				}
			}

			for (int i = 0; i < bcNum; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				bc[i] = new Node(y, x, stoi(st.nextToken()), stoi(st.nextToken()));
			}

			simulation();
			System.out.println("#" + t + " " + result);
		}

	}

	private static void simulation() {
		for (int i = 0; i < size; i++) {
			findBest();
			A = new Node((A.x + dx[move[0][i]]), (A.y + dy[move[0][i]]));
			B = new Node((B.x + dx[move[1][i]]), (B.y + dy[move[1][i]]));
		}
		findBest();
	}

	private static void findBest() {
		ArrayList<Node> a = new ArrayList<>();
		ArrayList<Node> b = new ArrayList<>();

		for (int i = 0; i < bcNum; i++) {
			if (isPossible(A, bc[i])) {
				a.add(new Node(bc[i].x, bc[i].y, bc[i].length, bc[i].capacity));
			}
			if (isPossible(B, bc[i])) {
				b.add(new Node(bc[i].x, bc[i].y, bc[i].length, bc[i].capacity));
			}
		}

		Collections.sort(a);
		Collections.sort(b);

		if (a.isEmpty() && b.isEmpty()) { // 
			return;
		} else if (a.isEmpty()) {
			result += b.get(0).capacity;
		} else if (b.isEmpty()) {
			result += a.get(0).capacity;
		} else if (a.get(0).x == b.get(0).x && a.get(0).y == b.get(0).y) {

			if (a.size() == 1 && b.size() == 1) {
				result += a.get(0).capacity;
			} else if (a.size() == 1) {
				result += a.get(0).capacity + b.get(1).capacity;
			} else if (b.size() == 1) {
				result += b.get(0).capacity + a.get(1).capacity;
			} else {
				result += a.get(0).capacity + Math.max(a.get(1).capacity, b.get(1).capacity);
			}

		} else {
			result += a.get(0).capacity + b.get(0).capacity;
		}

	}

	private static boolean isPossible(Node n, Node target) {
		if ((Math.abs(target.x - n.x) + Math.abs(target.y - n.y)) <= target.length) {
			return true;
		}
		return false;
	}

	private static int stoi(String input) {
		return Integer.parseInt(input);
	}
}


```



