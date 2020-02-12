package test;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Main15684_사다리조작{
   private static int N;
   private static int M;
   private static int H;
   private static int[][] map;
   private static ArrayList<Ladder> list;
   private static int result;
   private static boolean flag;
   public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       N = Integer.parseInt(st.nextToken());
       M = Integer.parseInt(st.nextToken());
       H = Integer.parseInt(st.nextToken());
       map = new int[H + 1][N + 1];
       for (int i = 0; i < M; i++) {
           st = new StringTokenizer(br.readLine(), " ");
           map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
       }
       list = new ArrayList<Ladder>();
       // 사다리를 놓을 수 있는 위치 계산
       for (int i = 1; i < map.length; i++) {
           for (int j = 1; j <= N - 1; j++) {
               if (j == 1) {
                   if (map[i][j] == 0 && map[i][j + 1] == 0)
                       list.add(new Ladder(i, j));
               } else if (j != 1 && j != N - 1) {
                   if (map[i][j] == 0 && map[i][j - 1] == 0 && map[i][j + 1] == 0)
                       list.add(new Ladder(i, j));
               } else if (j == N - 1) {
                   if (map[i][j - 1] == 0 && map[i][j] == 0)
                       list.add(new Ladder(i, j));
               }
           }
       }
//        print();
       flag = false;
       result = Integer.MAX_VALUE; // 사다리 최종 갯수
       backTracking(0, 0); // idx, 사다리 갯수
       result = result == Integer.MAX_VALUE ? -1 : result;
       System.out.println(result);
   }
   public static void backTracking(int idx, int ladderNum) {
       if(result <= ladderNum ) return;
       if (ladderNum == 4)
           return;
       if (checkLadder(ladderNum))
           return;
       for (int i = idx; i < list.size(); i++) {
           Ladder ladder = list.get(i);
           if (map[ladder.r][ladder.c] == 0) {
        	   if(checkInstall(ladder)) {
        		   map[ladder.r][ladder.c] = 1;
        		   backTracking(i+1, ladderNum + 1);
        		   map[ladder.r][ladder.c] = 0;
        		   
        	   }
           }
       }
   }
   
	private static boolean checkInstall(Ladder ladder) {
		if (ladder.c - 1 >= 1 && map[ladder.r][ladder.c - 1] == 1)
			return false;
		if (ladder.c + 1 <= N && map[ladder.r][ladder.c + 1] == 1)
			return false;
		if (ladder.c - 1 >= 1 && ladder.c + 1 <= N
				&& (map[ladder.r][ladder.c - 1] == 1 || map[ladder.r][ladder.c + 1] == 1))
			return false;
		return true;
	}

   
   public static boolean checkLadder(int ladderNum) {
       for (int i = 1; i < N; i++) {
           int startColumIdx = i;
           int startNum = 1;
           while (startNum <= H) {
               if (map[startNum][startColumIdx] == 1) {
                   startColumIdx++;
                   startNum++;
               } else if (startColumIdx - 1 >= 1 && map[startNum][startColumIdx - 1] == 1) {
                   startColumIdx--;
                   startNum++;
               } else
                   startNum++;
           }
           if (i == startColumIdx)
               continue;
           else
               return false;
       }
       result = result > ladderNum ? ladderNum : result;
       return true;
   }
   public static class Ladder {
       int r;
       int c;
       public Ladder(int r, int c) {
           super();
           this.r = r;
           this.c = c;
       }
       @Override
       public String toString() {
           return "Ladder [r=" + r + ", c=" + c + "]";
       }
   }
}// end of class