## Programmers_N진수게임



```java
public static String solution(int n, int t, int m, int p) {
    String retVal = "";
    
    int curTurn = 0;
    int curNum = 0;
    String strNum = "";
    
    while(retVal.length() < t) {
        strNum = conversion(n, curNum++); // 진수변환 결과 리턴
        for(char c : strNum.toCharArray()) {
            curTurn++;
            if(curTurn == p) {
                retVal += c;
                if(retVal.length() == t) {
                    break;
                }
            }
            if(curTurn == m) {
                curTurn = 0;
            }
        }
    }
    
    return retVal;
}


```

