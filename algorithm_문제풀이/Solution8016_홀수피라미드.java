package Algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
public class Solution8016_홀수피라미드 {
     
    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); 
        for(int tc = 1; tc <= T; ++tc){
            long N = Long.parseLong(br.readLine());
             
            long firstN = 2*(N-1)*(N-1)+1;
            long lastN = 2*N*N-1;
            System.out.println(lastN);
            sb.append("#").append(tc).append(" ").append(firstN).append(" ").append(lastN).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }
 
}