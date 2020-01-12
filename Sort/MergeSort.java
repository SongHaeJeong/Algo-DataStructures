package Sort;

import java.util.Arrays;

public class MergeSort {
	static int[] sorted;

	public static void main(String[] args) {
		// O(N * log N)
		sorted = new int[8]; // 정렬 배열은 반드시 전역 변수로 선언 => 불필요한 메모리 생성 방지
		int[] array = {7,6,5,8,3,5,9,1};
		mergeSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}

	static void merge(int a[], int m, int middle, int n) {
		int i = m;
		int j = middle +1;
		int k = m;
		//작은 순서대로 배열에 삽입
		while(i <= middle && j <= n) {
			if(a[i] <= a[j]) {
				sorted[k] = a[i];
				i++;
			}else {
				sorted[k] = a[j];
				j++;
			}
			k++;			
		}
		//남은 데이터도 삽입
		if(i>middle) {
			for (int t = j; t <= n; t++) {
				sorted[k] = a[t];
				k++;
				
			}
		}else {
			for (int t = i; t <=middle; t++) {
				sorted[k] = a[t];
				k++;
			}
		}
		
		for (int t = m; t <=n; t++) {
			a[t] = sorted[t];
		}
		
	}

	static void mergeSort(int a[], int m, int n) {
		// 크기가 1보다 큰 경우
		if(m < n) {
			int middle = (m + n)/2;
			mergeSort(a, m, middle);
			mergeSort(a, middle+1,n);
			merge(a, m, middle, n);
		}
	}
}
