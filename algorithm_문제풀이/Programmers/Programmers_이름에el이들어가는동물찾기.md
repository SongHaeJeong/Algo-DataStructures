## Programmers_이름에el이들어가는동물찾기

__문제 풀이__
이름에 특정 문자열이 들어가는 데이터 검색할 때 LIKE 사용하면 됩니다.

```mysql
-- 코드를 입력하세요
SELECT ANIMAL_ID , NAME
FROM ANIMAL_INS as A
WHERE A.name like '%EL%' and A.ANIMAL_TYPE = 'DOG'
ORDER BY NAME
```

