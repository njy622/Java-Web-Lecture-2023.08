/*
    1. 마당서점의 고객이 요구하는 다음 질문에 대해 SQL문을 작성하시오.
 */
--(1)도서번호가 1인 도서의 이름
select bookname from book where bookid='1';

--(2)가격이 20,000원 이상인 도서의 이름 (검증을 위해 price도 넣기)
select bookname, price from book where price >= '20000';
--(3)박지성의 총 구매액(박지성의 고객번호는 1번으로 놓고 작성)
select sum(saleprice) from orders where custid = '1';
--(4)박지성이 구매한 도서의 수(박지성의 고객번호는 1번으로 놓고 작성)
select count(*) from orders where custid= '1';
select count(bookid) from orders where custid= '1';
select count(orderdate) from orders where custid = '1';



/*
    2. 마당서점의 운영자와 경영자가 요구하는 다음 질문에 대해 SQL문을 작성하시오.
*/
--(1)마당서점 도서의 총 개수
select count(*) from book;
--(2)마당서점에 도서를 출고하는 출판사의 총 개수    distinct 중복제거
select count(distinct publisher) from book;
--(3)모든 고객의 이름,주소
select name, address from customer;
--(4)2014년 7월 4일~7월 7일 사이에 주문받은 도서의 주문번호
select orderid from orders where orderdate between '2014-07-04' and '2014-07-07';
--(5)2014년 7월 4일~7월 7일 사이에 주문받은 도서를 제외한 도서의 주문번호
select orderid from orders where orderdate not between '2014-07-04' and '2014-07-07'; 
--(6)성이 '김'씨인 고객의 이름과 주소
select name, address from customer where name like '김%%';
--(7)성이 '김'씨이고 이름이 '아'로 끝나는 고객의 이름과 주소
select name, address from customer where name like '김%아';


/*
Q1. 마당서점에서 다음의 심화된 질문에 대해 SQL문을 작성하시오
*/
--(1) 박지성이 구매한 도서의 출판사와 같은 출판사에서 도서를 구매한 고객의 이름
-- 박지성이 구매한 도서의 출판사이름
select b.publisher from orders o
    join customer c on c.custid = o.custid
    join book b on b.bookid = o.bookid
    group by b.publisher, c.name, b.bookid
    having (c.name = '박지성');   

-- 박지성이 구매한 도서의 출판사의 bookid
select b.bookid from orders o
    join customer c on c.custid = o.custid
    join book b on b.bookid = o.bookid
    group by b.publisher, c.name, b.bookid
    having (c.name = '박지성'); 
    
--  박지성이 구매한 bookid와 동일한  출력
select c.name from orders o
    join customer c on c.custid = o.custid
    join book b on b.bookid = o.bookid
    where bookid like (
    select b1.bookid from orders o1 
    join customer c1 on c1.custid = o1.custid
    join book b1 on b1.bookid = o1.bookid
    group by b1.publisher, c1.name, b1.bookid
    having c1.name like '박지성');
    
    


--(2) 두 개 이상의 서로 다른 출판사에서 도서를 구매한 고객의 이름


    

--(3) (생략) 전체 고객의 30% 이상이 구매한 도서



/*
    다음질의에 대해 DML 문을 작성하시오.
*/
-- (1)새로운 도서('스포츠 세계', '대한미디어', 10000원)이 마당서점에 입고되었다.
-- 삽입이 안 될 경우 필요한 데이터가 더 있는지 찾아보자.
insert into book  --(bookname, publisher,price) 이렇게 하면 bookid가 primary라서 무조건 추가해줘야함.
    values(13, '스포츠 세계', '대한미디어', '10000');  --따옴표방향이 문제 있을시에도 오류남
select * from book;

--(2)'삼성당'에서 출판한 도서를 삭제해야 한다.
delete book where publisher like '삼성당';
select * from book;
--(3)'이상미디어'에서 출판한 도서를 삭제해야 한다. 삭제가 안 될 경우 원인을 생각해보자.
delete book where publisher like '이상미디어'; 
-- 포린키리스트렉션으로 인해, Ordes테이블에 해당 도서 구매데이터가 있기때문에 오류남
-- 삭제하려면, Order 테이블에서 먼저 삭제 후, Book 테이블에서 삭제해야 함
select * from book;
select * from orders
    where bookid in (select bookid from book where publisher like '이상미디어');   
--(4) 출판사 ' 대한미디어'가 '대한출판사로 이름을 바꾸었다.
update book set publisher='대한출판사' where publisher='대한미디어';


/*
 3장 강의 SQL 기초 p61
*/
/*
Q1. 마당서점의 고객이 요구하는 다음 질문에 대해 SQL 문을 작성하시오.
*/
--(1) 박지성이 구매한 도서의 출판사 수
select count(b.publisher)
    from orders o
    join book b on o.bookid = b.bookid
    join customer c on c.custid= o.custid
    where c.name like '박지성';
--(2) 박지성이 구매한 도서의 이름, 가격, 정가와 판매가격의 차이
select b.bookname, b.price 정가, o.saleprice 세일가, (b.price-o.saleprice) 차액
    from orders o
    join book b on o.bookid = b.bookid
    join customer c on c.custid= o.custid
    where c.name like '박지성';
--(3) 박지성이 구매하지 않은 도서의 이름
select distinct b.bookname
    from orders o
    join book b on o.bookid = b.bookid
    join customer c on c.custid= o.custid
    where c.name not like '박지성'; 

/*Q2. 마당서점의 운영자와 경영자가 요구하는 다음 질문에 대해 SQL 문을 작성하시오.
*/
--(1) 주문하지 않은 고객의 이름(부속질의 사용)
-- ordes 테이블에서 custid 추출 > name으로 바꾸고 not 붙이기
select * from orders;

select o.custid from orders o;

select c.name from customer c
    where custid not in(select o.custid from orders o);

--(2) 주문 금액의 총액과 주문의 평균 금액
select sum(saleprice), avg(saleprice) from orders;

--(3) 고객의 이름과 고객별 구매액
select c.name, sum(o.saleprice) from orders o
    join customer c on c.custid= o.custid
    group by c.name;

--(4) 고객의 이름과 고객이 구매한 도서 목록
select c.name, b.bookname from orders o
    join book b on o.bookid = b.bookid
    join customer c on c.custid= o.custid
    order by c.name;

--(5) 도서의 가격(Book 테이블)과 판매가격(Orders 테이블)의 차이가 가장 많은 주문
select distinct b.bookname, b.price, o.saleprice, (b.price-o.saleprice)
    from orders o
    join book b on o.bookid = b.bookid
    join customer c on c.custid= o.custid;
    
-- 차액
select bookname
    from orders o
    join book b on o.bookid = b.bookid
    where (b.price-o.saleprice) = (select max (b.price-o.saleprice) from orders o
        join book b on o.bookid = b.bookid
        join customer c on c.custid= o.custid);
    -- where => and로 바꿔서 써도 됨    
    

    
--(6) 도서의 판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
-- 도서의 판매액 평균
select avg(o.saleprice) from orders o;
-- 개인별 도서 구입액 평균
select  avg(saleprice)  from orders o  group by custid;
    
-- 도서판매액 평균보다 자신의 구매액 평균이 더 높은 고객의 이름
select c.name, avg(saleprice) from orders o
    join customer c on c.custid=o.custid
    group by c.name
    having avg(saleprice) - (select  avg(saleprice)  from orders o)>0;
