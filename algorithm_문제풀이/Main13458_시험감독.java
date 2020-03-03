package Algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main13458_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] room = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < room.length; i++) {
			room[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int totalDirector = Integer.parseInt(st.nextToken());
		int subDirector = Integer.parseInt(st.nextToken());
		long result = 0;
		for (int i = 0; i < room.length; i++) {
			result++; // 각각의 시험장에 총감독관은 오직 1명
			int value = room[i] - totalDirector;
			if (value <= 0) { // 0보다 작으면 모두 감시할 수 있음
				continue;
			} else { // 모두 감시할 수 없을 때 부감독관으로 대체

				if (value / subDirector == 0) {
					result++;
				} else if (value / subDirector >= 1) {					

					if (value % subDirector != 0) {
						result++;
						result += (value / subDirector);
					} else {
						result += (value / subDirector);
					}

				}

			}
		}
		System.out.println(result);
	}// end of main
}// end of class
