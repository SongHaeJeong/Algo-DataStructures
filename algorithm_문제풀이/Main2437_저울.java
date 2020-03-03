package Algo;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2437_Àú¿ï {
	private static int N;
	private static int[] arr;
	private static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int number = 0;
		Arrays.sort(arr);
		int min = 1;
		for (int i = 0; i < N; i++) {
			if (min < arr[i]) {
				break;
			}
			min += arr[i];
		}
		System.out.println(min);

	}// end of main

}// end of class
