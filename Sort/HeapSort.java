package Sort;


import java.util.Arrays;

public class HeapSort {
   public static void main(String[] args) {
      int[] arr = { 7, 6, 5, 8, 3, 5, 9, 1, 6 };

      for (int i = 1; i < arr.length; i++) {
         int c = i;
         do {
            int root = (c-1)/2;
            if(arr[root] < arr[c]) {
               int temp = arr[root];
               arr[root] = arr[c];
               arr[c] = temp;
            }
            
            c = root;
         }while(c !=0);
      }
      
      for (int i = arr.length-1; i >=0 ; i--) {
         int temp = arr[0];
         arr[0] = arr[i];
         arr[i] = temp;
         
         int root = 0;
         int c= 1;
         
         do {
            c = 2 * root +1;
            if(c < i-1  && arr[c] < arr[c+1]) {
               c++;
            }
            
            if(c < i && arr[root] < arr[c]) {
               temp = arr[root];
               arr[root] = arr[c];
               arr[c] = temp;
            }
            
            root = c;
         }while(c < i);
      }
      
      
      System.out.println(Arrays.toString(arr));
   }// end of main
}// end of class