## Main15663_N과M

```java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main{
	private static int[] arr;
	private static LinkedHashSet<String> hs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		hs = new LinkedHashSet<String>();
		
		arr = new int[N];
		int[] a = new int[N]; // 인덱스를 관리하는 배열
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		backtrack(a, 0, M);
		  for(String s : hs) {
	            sb.append(s.substring(0, s.length()-1)).append("\n");
	        }
	        System.out.print(sb.toString());
	}

	public static void backtrack(int[] a, int k, int r) {
		int[] c = new int[a.length]; // 후보군을 담는 배열
		if (k == r) {
			print(a, k);

		} else {
			int cands = make_candidates(a, k, r, c);
			for (int i = 0; i < cands; i++) {
				a[k] = c[i];
				backtrack(a, k + 1, r);
			}
		}

	}

	public static int make_candidates(int[] a, int k, int r, int[] c) {
		boolean[] flag = new boolean[a.length];
		for (int i = 0; i < k; i++) {

			flag[a[i]] = true;

		}

		int cnt = 0;
		for (int i = 0; i < flag.length; i++) {
			if (!flag[i]) {
				c[cnt] = i;
				cnt++;
			}
		}
		return cnt;
	}

	public static void print(int[] a, int r) {
		String s = "";
		for (int i = 0; i < r; i++) {
			s=s + arr[a[i]] + " ";
		}
			hs.add(s);

	}

}

```

