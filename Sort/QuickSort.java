package Sort;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 7, 8, 9, 1, 2, 3, 4, 5, 6 };
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}

	static void quickSort(int[] arr, int start, int end) {
		if (start >= end) { // 배열의 원소가 한개
			return;
		}

		int i = start + 1;
		int j = end;
		int pivot = start;
		int temp;

		while (i <= j) { // 엇갈릴 때 까지 반복
			while (i <= end && arr[i] <= arr[pivot]) { // 피봇값보다 작으면 계속 i++
				i++;
			}

			while (j > start && arr[j] >= arr[pivot]) { // 피봇값보다 크면 계속 j--
				j--;
			}

			if (i > j) { // 엇갈리면
				temp = arr[j];
				arr[j] = arr[pivot];
				arr[pivot] = temp;

			} else { //엇갈리지 않으면

				temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;

			}

		}
		
		quickSort(arr, start, j-1);
		quickSort(arr, j+1, end);

	}
}
