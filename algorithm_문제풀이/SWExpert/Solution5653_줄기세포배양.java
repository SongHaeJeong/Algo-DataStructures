package Algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution5653_줄기세포배양 {
	static int[] dx = {-1, 0 , 1 ,0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			ArrayList<Pair> list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					int liveCount = Integer.parseInt(st.nextToken());
					if(liveCount != 0) {
						list.add(new Pair(i,j,liveCount, -1));
					}
				}
			}
			
			//-1 비활성 , -2 활성 , -3 죽음
			int count = 0;
			while(count <= K) {
				count++;
				
				//활성상태 퍼지게하기
				for (Pair p : list) {
					if(p.status == -2) {
						for (int i = 0; i < dx.length; i++) {
							int nx = p.x + dx[i];
							int ny = p.y + dy[i];							
							boolean flag = false;
							int value = 0;
							//겹쳐지는지 확인 겹쳐지면 생명주기가 더 큰 걸로 유지
							for (Pair pair : list) {
								if(nx != pair.x && ny != pair.y) continue;
								else {
									flag = true;
									if(p.live < pair.live) value = pair.live;
									else value = p.live;
								}
								
							}
							
							if(flag) {
								list.add(new Pair(nx, ny, value, -1));
								if()
								
							}else {
								list.add(new Pair(nx,ny,p.live, -1));
							}
							
							
							
							
						}
					}
				}
				
				
				
				
				
				
				
				for (Pair p : list) {
					if(p.live == count) p.status = -2;
				}
				
				
				
				
				
				
				
				
				
			}
			
			
		}//end of testCase
		
	}//end of main
	
	static class Pair{
		int x;
		int y;
		int live;
		int status;
		public Pair(int x, int y, int live, int status) {
			super();
			this.x = x;
			this.y = y;
			this.live = live;
			this.status = status;
		}
		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", live=" + live + ", status=" + status + "]";
		}
		
		
		
	}
}//end of class
