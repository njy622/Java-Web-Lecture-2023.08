/*
    4. 내장(Built-in) 함수
*/
/*
    4.1 Built-in Function
*/
-- 절댓값
select abs(-78) from dual;    --dual : 아무의미 없는것 / abs는 값을 구하기 위해서 from + 테이블명을 붙여줘야함 
-- 반올림  round(수 , 소숫점자릿수)
select round(3.14159,2), round(3.14159,4) from dual;
-- 문자열 연결
select concat('마당', '서점') from dual;
-- 문자열 대체
select replace('JACK and JUE', 'J', 'BL') from dual;
-- 도서제목에 야구가 포함된 도서를 농구로 변경한 후 도서 목록을 보여주기
select replace(bookname, '야구', '농구') from book
    where bookname like '%야구%';
-- 굿스포츠에서 출판한 도서의 제목과 제목의 글자 수(문자열의 길이)
select bookname, length(bookname) from book
    where publisher like '굿스포츠';
-- 마당서점의 고객 중에서 같은 성(姓)을 가진 사람이 몇 명이나 되는지 성별 인원수
select  substr(name,1,1), COUNT(name) from customer
    group by substr(name, 1, 1);

-- 마당서점은 주문일로부터 10일 후 매출을 확정한다. 각 주문의 확정일자는?
select orderid, orderdate "주문일자", orderdate+10 "확정일자" from orders;  -- 별칭지을때 ""붙여도됨
-- 마당서점이 2014년 7월 7일에 주문받은 도서의 주문번호, 주문일, 고객번호, 도서번호
select * from orders where orderdate = '2014-07-07';
select * from  orders where orderdate =to_date('20140707', 'yyyymmdd');
select * from  orders where orderdate =to_date('140707', 'yymmdd');
-- 마당서점이 7월7일에서 7월9일까지 판매한 책 이름과 판가는?
select b.bookname, o.saleprice, o.orderdate from orders o
    join book b on o.bookid = b.bookid
    where orderdate between '2014-07-07' and '2014-07-09'
    order by orderdate;
-- 오늘 현재
select sysdate, systimestamp, current_timestamp from dual;

-- MyBook 테이블 만들기
create table MyBook (
    bookid number primary key,
    price number
);
insert into mybook values(1, 10000);
insert into mybook values(2, 20000);
insert into mybook(bookid) values(3);
insert into mybook values(4, null);
delete from mybook where bookid=4;
commit;
select *from mybook;

-- Null 값에 대한 연산
select price+100 from mybook;  --null값에 더해도 null유지
select sum(price), avg(price), count(*), count(price) from mybook; --null값은 없는것으로 침(카운트에서만빼고)
select * from mybook where price is not null;
select bookid, nvl(price,0)  from mybook;  --nvl : null values가 0이다

/*
    4.3 ROWNUM
*/
-- 도서의 가격이 비싼 책 3권
select * from book order by price desc;
select rownum,bookid, bookname, price from book order by price desc;
select rownum, a.* from
    (select * from book order by price desc) a;
    
select rownum, a.* from
    (select * from book order by price desc) a  -- 원하는 데이터만 보고싶을때 
        where rownum <= 3;  -- rownum 비교는 LT, LE만 가능(LT : lt - little ( < )/LE: little or equal ( <= )))

-- 도서의 가격이 비싼 책 4~6위
select * from(
    select rownum as rnum, a.* from
        (select * from book order by price desc) a 
        where rownum <= 6)
    where rnum >= 4;
-- select * from book order by price desc limit 3 offset 3; #MYSQL 