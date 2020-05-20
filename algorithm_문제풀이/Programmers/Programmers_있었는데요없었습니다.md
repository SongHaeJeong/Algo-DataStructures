## Programmers_있었는데요없었습니다

>__문제 풀이__
>
>1. outer join을 사용하여 문제 해결함

```mysql
-- 코드를 입력하세요
SELECT A.Animal_ID , A.NAME
from ANIMAL_OUTS as B
left join ANIMAL_INS as A on (A.ANIMAL_ID = B.ANIMAL_ID)
where (B.DATETIME < A.DATETIME)
ORDER BY A.DATETIME




```

