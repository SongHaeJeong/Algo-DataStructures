## Solution5658_보물상자비밀번호

재풀이 시간 : 1시간

>__문제 풀이__
>
>1. 입력받은 문자열을 char 형태의 배열로 생성
>2. split() 함수를 통해서 char 형태의 배열을 인덱스 조절해서 문자열을 생성함
>   1. 이 때, 무조건 4개씩 생성시켰는데 그랬더니 런타임 에러가 났다. 모든 경우를 검사해봐야되니깐 i++ 해줘야됨
>3. rotate() 함수를 통해서 시계방향으로 회전시켜줌 
>   1. 4회전 할 때, 처음과 동일하기 때문에 3번만 회전시켜주면 됩니다.

```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Solution5658_보물상자비밀번호{
	private static ArrayList<Info> list ;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String password = br.readLine();
			char[] arr = new char[N];
			for (int i = 0; i < arr.length; i++) {
				arr[i] = password.charAt(i);
			}
			list = new ArrayList<>();
			for (int i = 0; i < 3; i++) {
				for (int j = i; j < password.length(); j += 3) {
					String str = "";
					for (int k = 0; k < password.length() / 4; k++) {
						str += arr[(j + k) % password.length()];

					}
					int value = changeValue(str);
					list.add(new Info(value));
				}

			}
			Collections.sort(list);
			delete();
			sb.append("#").append(testCase).append(" ").append(list.get(K - 1).a).append("\n");

		} // end of testCase
		System.out.println(sb.toString());
	}// end of main


	private static void delete() {
		Info info = null ;
		ArrayList<Info> deleted = new ArrayList<>();
		for (Info i : list) {
			if(info == null) {
				info = i;
				continue;
			}
			if(info.a == i.a) {
				deleted.add(i);
			}else {
				info = i;
			}
			
		}
		
		for (Info i : deleted) {
			list.remove(i);
		}
	}


	private static int changeValue(String str) {
		int[] value = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			value[i] = str.charAt(i) - '0';
		}

		int sum = 0;
		for (int i = 0; i < value.length; i++) {
			int a = value[i];

			if (a >= 10) {
				a = str.charAt(i) - 55;
				int b= 1;
				for (int j = i; j < value.length-1; j++) {
					b = b<<4;
				}
				a *= b;
			} else {
				int b = 1;
				for (int j = i; j < value.length-1; j++) {
					b = b<<4;
				}
				a *= b;
				
			}
			sum += a;

		}

		return sum;

	}

	static class Info implements Comparable<Info> {
		int a;

		public Info(int a) {
			super();
			this.a = a;
		}

		@Override
		public int compareTo(Info o) {
			return o.a - this.a;
		}

		@Override
		public String toString() {
			return "Info [a=" + a + "]";
		}
		
		

	}
}// end of class

```



```java
package test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Solution5658_보물상자비밀번호 {
	private static int T, N, K;
	private static HashSet<String> hs;
	private static char[] ch;
	private static ArrayList list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // N개의 숫자
			K = Integer.parseInt(st.nextToken()); // 몇번째 숫자 출력할 것인지

			String str = br.readLine(); // 숫자 입력
			ch = new char[N]; // 시계방향으로 돌면서 생성 시킬 배열 생성
			for (int i = 0; i < ch.length; i++) { // 숫자 입력
				ch[i] = str.charAt(i);
			}
			
			hs = new HashSet<String>(); //입력되는거 중복 없애기 위해서 HashSet 사용
			
			addString();
			// 0 : 3 회전은 동일하기 떄문에 두번만 돌리면됨
			for (int i = 0; i < 2; i++) {
				rotate(); // 배열 돌리기
				addString();
			}
			
			// hs에 담긴 String을 숫자로 변환해서 list에 담음
			list = new ArrayList<Integer>();
			chageStringtoInt();
			System.out.println(list.size());

		}
	}// end of main

	private static void chageStringtoInt() {
		// TODO Auto-generated method stub
		String[] strNumber = new String[hs.size()];
		hs.toArray(strNumber);
		//꺼내면서 숫자로 변환시키고 list에 담기
		for (int i = 0; i < strNumber.length; i++) {
			int a = Integer.parseInt(strNumber[i], 16);
			list.add(a);
		}
		
		Collections.sort(list);
		
	}

	// 배열 돌리기
	private static void rotate() {
		// TODO Auto-generated method stub
		char temp = ch[ch.length-1];
		for (int i = ch.length-1; i > 0; i--) {
			ch[i] = ch[i-1];
		}
		
		ch[0] = temp;
	}
	
	// 글자수 만들어서 HashSet에 입력하기
	private static void addString() {
		
		String addTemp = "";
		int stopIdx = 0;
		for (int i = 0; i < ch.length; i++) {
			addTemp += ch[i];
			stopIdx++;
			if (stopIdx % (N / 4) == 0) {
				stopIdx = 0;
				hs.add(addTemp);
				addTemp = "";
			}
		}
	}
}// end of class

```

```java
package solve;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Soltuion5658_보물상자비밀번호 {
	private static int N, K;
	private static char[] ch;
	private static HashMap<String, Integer> hm;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1 ; testCase <= T ; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); //숫자의 개수
			K = Integer.parseInt(st.nextToken()); //크기 순서
			ch = br.readLine().toCharArray(); // 16진수를 배열로 받음 
			hm = new HashMap<String, Integer>(); // 중복을 제거해주기 위해서 생성해줌
			for(int i = 0 ; i < 3; i++) { // 3번만 회전시키면됩니다. 4회전 시 처음과 동일
				split(); //배열을 나누어줌
				rotate(); // 회전
			}
			
			int ans = process(); // HashMap에 있는 중복을 제거한 값들을 10진수로 변환시킨 후 K번째의 값을 반환
			sb.append("#").append(testCase).append(" ").append(ans).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}//end of main
	private static int process() {
		// TODO Auto-generated method stub
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(String s : hm.keySet()) {
			int num = changeNum(s);
			list.add(num);
		}
		
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o2-o1;
			}
		});
		return list.get(K-1);
	}
	
	private static int changeNum(String s) {
		// TODO Auto-generated method stub
		int num = Integer.parseInt(s, 16);
		return num;
	}
	//시계방향으로 회전
	private static void rotate() {
		// TODO Auto-generated method stub
		char c = ch[ch.length-1];
		for(int i = ch.length-1; i > 0 ;i--) {
			ch[i] = ch[i-1];
		}
		ch[0] = c;
		
	}
	
	// ch배열에서 N/4만큼 문자열을 생성
	private static void split() {
		// TODO Auto-generated method stub
		int size = N/4; // 사이즈 만큼 만들어줘야됨
		for(int i = 0; i < ch.length; i++) {
			String str ="";
			for(int j =i; j <i+size; j++) {
				str += ch[j % ch.length];
			}
//			System.out.println(str);
			hm.put(str, 1);
		}
		
		
	}
}//end of class

```

