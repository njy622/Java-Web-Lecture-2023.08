/*
    1.4 그룹 분석(GROUP BT)
*/
-- 고객별로 주문한 도서의 총 수량과 총 판매액
select custid, count(*), sum(saleprice) from orders group by custid;

-- 출판사별로 출간한 책 수량과 평균 금액
select publisher, count(*), avg(price) from book group by publisher;

/* HAVING (group by에서의 조건문)*/
-- 책을 2권이상 출간한 출판사의 평균 책 가격
select publisher, count(*), avg(price) from book 
    group by publisher
    having count(*) >= 2;
-- 가격이 8,000원 이상인 도서를 구매한 고객에 대하여 고객별 주문 도서의 총 수량 및 평균금액(select의 조건)
-- 단, 두권 이상 구매한 고객만 구한다.
select custid, count(*), avg(saleprice) from orders 
    where saleprice >= 8000
    group by custid
    having count(*) >= 2;

-- 누가 책 매출액이 많은가?
select custid, sum(saleprice) from orders
    group by custid
    order by sum(saleprice) desc;
    
/*
    두개 이상의 테이블
*/
select * from orders, customer;
select * from orders, customer
    where orders.custid = customer.custid;   -- table명.fied명
    

-- orders 테이블에서 custid를 고객명으로 변경해서 출력
select * from orders o, customer c          --Aliasing(엘리어싱) 캄럼에 별명 짓기
    where o.custid = c.custid;          

select o.orderid, o.saleprice, o.orderdate, o.bookid, c.name
    from orders o, customer c 
    where o.custid = c.custid; 
    
-- orders 테이블에서 bookid와 custid를 이름으로 변경해서 출력
select o.orderid, o.saleprice, o.orderdate, b.bookname, c.name
    from orders o, book b,customer c
    where o.custid = c.custid and o.bookid = b.bookid;
    
-- 고객과 고객의 주문에 관한 데이터를 고객번호 순으로 정렬
select c.custid, c.name, c.address, c.phone, o.bookid, o.saleprice, o.orderdate
    from customer c, orders o
    where c.custid = o.custid
    order by c.custid;
    
/* Inner Join */        -- 위와 동일한 결과값이 나옴
select c.custid, c.name, c.address, c.phone, o.bookid, o.saleprice, o.orderdate
    from customer c
    inner join orders o
    on c.custid = o.custid
    order by c.custid;
    
-- 고객의 이름과 고객이 주문한 도서의 판매가격을 검색   
select c.name, o.saleprice from customer c      --c. o. 을 붙여 C와 O에만 있는 필드명 구분해주면 좋음
    join orders o
    on c.custid = o.custid;
-- 고객별로 주문한 도서의 총 판매액을 구하고, 고객별로 정렬
select c. custid, c.name, sum(o.saleprice) 
    from customer c, orders o
    where c.custid = o.custid
    Group by c.name, c.custid
    order by c.name;
    
select c. custid, c.name, sum(o.saleprice) 
    from customer c
    join orders o
    on c.custid = o.custid              --inner join 에서 inner 생략 가능
    Group by  c.custid, c.name
    order by c.name;
    
-- 가격이 20,000원인 도서를 주문한 고객의 이름과 도서의 이름
select c.name, b.bookname, o.saleprice
    from orders o, customer c, book b
    where o.custid = c.custid and o.bookid = b.bookid and o.saleprice=20000;
    
select c.name, b.bookname, o.saleprice
    from orders o
    join customer c on o.custid = c.custid
    join book b on o.bookid = b.bookid
    where o.saleprice=20000;
    
/* Outer join */
-- 도서를 구매하지 않은 고객을 포함하여 고객의 이름과 고객이 주문한 도서의 판매가격
select c.custid, c.name, o.saleprice
    from customer c
    left outer join orders o    -- left outer join에서 outer 생략가능
    on c.custid = o.custid;
    
/*
    1.6 부속 질의(Sub Querry)
*/
-- 가장 비싼 도서의 이름 (가장 비싼 가격을 먼저 알아야 함)
select max(price) from book;
select bookname, price from book
    where price = (select max(price) from book);
-- 도서를 구매한 적이 있는 고객의 이름을 검색
select distinct custid from orders;   --도서를 구매한 적이 있는 고객의 id
select custid, name from customer
    where custid in (select distinct custid from orders);
    
-- 대한미디어에서 출판한 도서를 구매한 고객의 이름
select bookid from book where publisher like '대한미디어'; -- 문자열은 eq안쓰고 like를 씀
select custid from orders
    where bookid in (select bookid from book where publisher like '대한미디어'); --where 앞에 별칭을 써서 부속질의도 가능
select custid, name from customer
    where custid in (select custid from orders
    where bookid in (select bookid from book where publisher like '대한미디어'));
