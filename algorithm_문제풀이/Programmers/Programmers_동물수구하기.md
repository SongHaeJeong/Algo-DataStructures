## 동물 수 구하기

>__Oracle__ 문제 풀이
>
>ANIMAL_ID가 NULL이 아니면 카운터를 해줌



```oracle
-- 코드를 입력하세요
SELECT COUNT(ANIMAL_ID) count FROM ANIMAL_INS WHERE ANIMAL_ID IS NOT NULL;
```

