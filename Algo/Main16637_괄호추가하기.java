package Algo;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main16637_°ýÈ£Ãß°¡ÇÏ±â {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
        
        dfs(1, input[0]-'0', 0, false);
        System.out.println(max);
    }
    static int N;
    static char[] input;
    static int max = Integer.MIN_VALUE;
    static void dfs(int op_i, int val, int pre_val, boolean wasBrace) {
        
        if(op_i >= N) {
            if(max < val)
                max = val;
            return;
        }
        // ÇöÀç °ýÈ£ x
        dfs(op_i+2, cal(val, input[op_i], input[op_i+1]-'0'), val, false);
        
        // ÇöÀç °ýÈ£ Ä¥ ¼ö ÀÖ³ª? µÇ¸é Ä§ 
        if(op_i > 1 && !wasBrace) {
            dfs(op_i+2, cal(pre_val, input[op_i-2], cal(input[op_i-1]-'0', input[op_i], input[op_i+1]-'0')),
                    0, true);
        }
        
    }
    static int cal(int a, char op, int b) {
        if(op == '+') return a + b;
        else if(op == '-') return a - b;
        else return a * b;
    }
}
