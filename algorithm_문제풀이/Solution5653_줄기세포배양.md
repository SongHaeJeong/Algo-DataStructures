## Solution5653_줄기세포배양

__풀이 1__

```java
package test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution5653_줄기세포배양 {
    private static int n, m, k, nx, ny;
    private static int[][] map;
    private static boolean[][] visit;
    private static Queue<Cell> queue = new LinkedList<>();
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};
    private static final short DEATH = 0, ACTIVE = 1, INACTIVE = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            map = new int[n + k + 2][m + k + 2];
            visit = new boolean[n + k + 2][m + k + 2];
            queue.clear();

            int temp;
            for (int i = k / 2 + 1; i < n + k / 2 + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = k / 2 + 1; j < m + k / 2 + 1; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    if (temp != 0) {
                        map[i][j] = temp;
                        visit[i][j] = true;
                        queue.add(new Cell(i, j, temp));
                    }
                }
            }

            int answer = solution();
            System.out.println("#" + t + " " + answer);
        }
    }

    private static int solution() {
        int count = k;
        Cell cell;
        while (count-- > 0) {
            int len = queue.size();
            for (Cell c : queue) {
                if (c.status == ACTIVE) check(c); // 주변에 세포 value 정해줌
            }

            for (int t = 0; t < len; t++) {
                cell = queue.poll();
                if (cell.status == ACTIVE) { // 활성화 상태인 경우만 번식
                    for (int i = 0; i < 4; i++) { // 상하좌우
                        nx = cell.x + dx[i];
                        ny = cell.y + dy[i];

                        if (visit[nx][ny]) continue;

                        queue.add(new Cell(nx, ny, map[nx][ny])); // 번식된 세포 추가
                        visit[nx][ny] = true; // 방문 처리
                    }
                }

                cell.next(); // 세포 상태 변화
                
                if (cell.status == DEATH) continue; // 죽은 세포는 queue에서 제외
                queue.add(cell);
            }
        }
        return queue.size();
    }

    private static void check(Cell cell) {
        for (int i = 0; i < 4; i++) {
            nx = cell.x + dx[i];
            ny = cell.y + dy[i];

            if (visit[nx][ny]) continue;

            if (map[nx][ny] < cell.value) map[nx][ny] = cell.value;
        }
    }

    static class Cell {
        int x, y;
        int value, temp;
        short status;

        public Cell(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.temp = value;
            this.status = INACTIVE;
        }

        public void next() {
            switch (status) {
                case INACTIVE: // 비활성화 상태
                    if (--temp == 0) status = ACTIVE;
                    break;
                case ACTIVE: // 활성화 상태
                    if (++temp == value) status = DEATH;
                    break;
            }
        }
    }
}
```

__풀이 2__

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution5653_줄기세포배양 {
	private static int T, N, M, K;
	private static int[][] map;
	private static ArrayList<Node> list;
	private static int[] dx = { -1, 0, 1, 0 };
	private static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N + K][M + K]; // 최대로 커질 수 있는 배열의 크기
			list = new ArrayList<Node>();

			for (int i = K / 2; i < K / 2 + N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = K / 2; j < K / 2 + M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 0)
						list.add(new Node(i, j, 1, map[i][j], 0));
				}
			}

			process();
			int ans = 0;
			for (Node li : list) {
				if(li.status != 3) ans++;
			}
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
			
		} // end of testCase
		System.out.println(sb.toString());

	}// end of main

	private static void process() {
		int time = 0;

		while (time < K) {
			time++; // 시간 증가

			// list에서 deadTime 하나씩 증가
			for (Node li : list) {
				if(li.status != 3) {
					li.deadTime++;
				}
			}

			// 상태중에서 활성상태인것은 1시간 동안 상,하,좌,우 네방향으로 동시에 번식
			// 번식된 것은 비활성 상태
			// 번식할 수 있는 리스트를 addList에 저장
			LinkedList<Node> addList = new LinkedList<>(); // 삽입, 삭제는 LinkedList 더 빠름
			for (Node n : list) {
				// 한 시간만 번식 가능
				if (n.status == 2 && n.deadTime == 1) {
					for (int i = 0; i < dx.length; i++) {
						int nx = n.row + dx[i];
						int ny = n.column + dy[i];
						// 번식할 위치에 기존의 세포가 존재하면 추가적으로 번식 X
						if (map[nx][ny] == 0) {							
							addList.add(new Node(nx, ny, 1, n.liveTime, 0));
						}
					}
				}
			}

			// 동시에 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포 해당 그리드 셀 차지
			if(addList.size() > 0) {
				Collections.sort(addList);
				LinkedList<Node> deleteList = new LinkedList<>();
				Iterator<Node> it = addList.iterator();
				Node no = null;
				while (it.hasNext()) {

					if (no == null) {
						no = it.next();
						continue;
					}

					Node temp = it.next();
					if (no.row == temp.row && no.column == temp.column && no.liveTime >= temp.liveTime) {
						deleteList.add(temp);
					} else {
						no = temp;
					}

				}

				it = deleteList.iterator();
				// 삭제
				while (it.hasNext()) {
					addList.remove(it.next());
				}

				it = addList.iterator();
				//맵에 표시해주고 list에 저장
				while(it.hasNext()) {
					Node n = it.next();
					map[n.row][n.column] = n.liveTime;
					list.add(n);
					
					
				}
			}

			// 시간에 따른 상태 변화 시켜주기
			for (Node n : list) {
				if(n.status == 1 && n.deadTime == n.liveTime) {
					// 활성화 상태로 변화 해주고 deadTime을 0으로 리셋해주는 이유는 죽을 시간 체크해주기
					n.status =2;
					n.deadTime = 0;
				}else if(n.status == 2 && n.deadTime == n.liveTime) {
					n.status = 3; // 죽음
				}
			}
			
			

		}

	}// end of process();

	static class Node implements Comparable<Node> {
		int row, column, status, liveTime, deadTime;

		public Node(int row, int column, int status, int liveTime, int deadTime) {
			super();
			this.row = row;
			this.column = column;
			this.status = status;
			this.liveTime = liveTime;
			this.deadTime = deadTime;
		}

		@Override
		public String toString() {
			return "Node [row=" + row + ", column=" + column + ", status=" + status + ", liveTime=" + liveTime
					+ ", deadTime=" + deadTime + "]";
		}

		@Override
		public int compareTo(Node o) {
			if (this.row != o.row ) {
				return this.row - o.row;
			}else if(this.row == o.row && this.column != o.column) {
				return this.column - o.column;
			}else if (this.row == o.row && this.column == o.column && this.liveTime != o.liveTime) {
				return o.liveTime - this.liveTime;
			}

			return 1;
		}

	}
}// end of class

```

