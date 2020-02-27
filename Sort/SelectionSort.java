package test;

import java.util.Arrays;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 3, 7, 8, 2, 5, 6, 1, 9, 4 };

		for (int i = 0; i < arr.length; i++) {
			int index = 0;
			int maxNum = 9999;

			for (int j = i; j < arr.length; j++) {
				if (maxNum > arr[j]) {
					maxNum = arr[j];
					index = j;
				}
			}

			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;

		}
		
		System.out.println(Arrays.toString(arr));

	}
}
