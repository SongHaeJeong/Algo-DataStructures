package Algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * 맨처음에 가로를 선택하지 않고 계산
 * 가로 설치해서 계산
 * 
 * ----------
 * 계산하는 부분에서 처음에 Queue로 담아서 계산헀더니 답은나오는데
 * 시간초과떠서 다른사람의 풀이 참고해서 품
 *
 */
public class Main16637_괄호추가하기 {
	private static int N,result;
	private static char[] oper;
	private static int[] number;
	private static boolean[] visited;
    public static void main(String[] args) throws Exception{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	
    	number = new int[N/2 +1];
    	oper = new char[N/2];
    	visited = new boolean[N/2];
    	String str = br.readLine();
    	
    	int numberIdx = 0;
    	int operIdx = 0;    	
    	for (int i = 0; i < str.length(); i++) {
			if(i % 2 == 0) number[numberIdx++] = str.charAt(i) -'0';
			else oper[operIdx++] = str.charAt(i);
		}
    	
    	//괄호 선택    	
    	result = Integer.MIN_VALUE;
    	select(0);
    	System.out.println(result);
    	
    }//end of main	
	private static void select(int idx) {
		if(idx >= oper.length) {
			process();
			return;
		}else {
			
			select(idx+1); // 괄호없이 계산
			
			for (int i = idx; i < oper.length; i++) {
				if(i ==0 || (i -1 >= 0 && !visited[i-1])) {
					if(i== oper.length-1 || (i+1 < oper.length && !visited[i+1])) {
						visited[i] = true;
						select(idx+1);
						visited[i] = false;	
				}
					
				}
			}
			
			
		}
	}
	private static void process() {
		int[] newArr = number.clone();
		// 괄호 먼저 계산
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				continue;

			int a = number[i];
			int b = number[i + 1];
			char c = oper[i];
			int num = cal(a, b, c);
			newArr[i] = num;
			newArr[i + 1] = num;
		}
		// 괄호 계산 후 순서대로 계산
		for (int i = 0; i < visited.length; i++) {
			if (visited[i])
				newArr[i + 1] = newArr[i];
			else {
				int num = cal(newArr[i], newArr[i + 1], oper[i]);
				newArr[i + 1] = num;
			}

		}
		result = Math.max(result, newArr[newArr.length - 1]);
		
		//시간초과뜬 코드
		/*Queue<Integer> queue = new LinkedList<Integer>();
		int[] temp = new int[N/2];
		temp = number.clone();
		
		for (int i = 0; i < visited.length; i++) {
			if(visited[i]) {
				int num = cal(number[i], number[i+1], i);
				temp[i] = num;
				temp[i+1] = -1;
				queue.add(num);
			}else {
				if(temp[i] > 0)
				queue.add(temp[i]);
			}
		}
		if(temp[temp.length-1] >= 0) {
			queue.add(temp[temp.length-1]);
			
		}
		int tempResult=0;
		
		int num = queue.poll();
		while(!queue.isEmpty()) {
			
			for (int i = 0; i < visited.length; i++) {
				if(!visited[i]) {
					int reNum = cal(num, queue.poll(),i);
					num = reNum;
				}
			}
			
		}
		
		result = result < num ? num : result;*/
		
	}
	private static int cal(int a, int b, int c) {
		int result = 0;
		switch (c) {
		case '+':
			result = a + b;
			break;
		case '-':
			result = a - b;
			break;
		case '*':
			result = a * b;
			break;
		}
		return result;
	}
}//end of class