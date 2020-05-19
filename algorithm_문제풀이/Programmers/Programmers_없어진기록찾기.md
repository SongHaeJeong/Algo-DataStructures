## Programmers_없어진기록찾기

>__문제 풀이__
>오랜만에 SQL 문을 작성하려니깐 어색했다.
>
>Outer Join을 이용해서 문제를 해결했고 두 테이블간의 ID가 모두 같은 데이터를 뽑고 a 테이블이 null값이면 ANIMAL_ID, NAME을 출력해줌



```mysql
SELECT a.ANIMAL_ID, a.NAME
FROM ANIMAL_OUTS a
LEFT JOIN ANIMAL_INS b ON( b.ANIMAL_ID = a.ANIMAL_ID)
WHERE b.ANIMAL_ID is null
```

