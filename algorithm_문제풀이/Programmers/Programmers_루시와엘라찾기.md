## Programmers_루시와엘라찾기

__문제 풀이__
여러가지 데이터를 받을 때는 IN을 사용해야된다.

```MySQL
-- 코드를 입력하세요
SELECT ANIMAL_ID, NAME, SEX_UPON_INTAKE
FROM ANIMAL_INS as A
WHERE A.NAME IN ("LUCY", "ELLA", "PICKLE", "ROGAN", "SABRINA","MITTY")
```

