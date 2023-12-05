/*
    2. 데이터 조작 언어(DML Data : Manipulation Language)
*/
/*
    2.1 삽입(Insert)
    
INSERT INTO 테이블명[(필드명1, 필드명2, ..., 필드명 n)] 
    VALUES (값1, 값2, ..., 값n);
    
*/
-- 북 테이블에 새로운 레코드 추가
select * from book;   --목록 총 10개 !

insert into book values(11, '스포츠 의학', '한솔의학서적', 90000);  
-- 테이블 추가 / 테이블에 추가할 값의 갯수와 필드명을 맞춰 입력하면, 테이블 명 뒤에 필드명은 붙여주지 않아도 됨 
select * from book;  --추가된 데이터 포함된 
desc book;
-- 스포츠 심리, 24,000원, 출판사 모름 
insert into book(bookid, bookname, price)
    values (12, '스포츠 심리', 24000);
    
-- 박세리 고객이 스포츠 의학을 오늘 구매한 사실을 orders 테이블에 기록
insert into orders
    values (11, 90000, default,5, 11);  
    --01.madang_table에서 orderdate를 이렇게 date DEFAULT SYSDATE 정의 하였기때문에 오늘날짜 자동으로 삽입됨
    -- default 자리에 '2023-08-21'이렇게 입력해도 됨
select * from orders;

-- 박찬호 고객이 스포츠 심리라는 책을 오늘 구매한 사실을 orders 테이블에 기록
insert into orders(orderid, saleprice, custid, bookid)  
-- default 대신 orderdate 필드명을 제외하고 입력하면 알아서 default 값이 들어감
    values(12, 24000, 1, 12);
    
-- 고객 테이블에 신유빈 선수를 등록
insert into customer(custid, name) values(6, '신유빈');
commit; 
    
select *from orders;

/*
    2.2 갱신 (Update)
UPDATE 테이블명
    SET 필드명1 = 값1, ..., 필드명 n = 값n
    WHERE 조건;
*/
-- 스포츠 심리 책의 출판사를 한솔의학서적으로 변경
update book set publisher = '한솔의학서적' where bookid=12;
select * from book;
-- 신유빈 선수의 주소를 강원도 영월, 전화번호를 010-2345-6789번으로 변경
update customer set address='강원도 영월', phone='010-2345-6789'
    where custid=6;
    
-- 전화번호가 null인 고객을 070-2345-6789로 변경
update customer set phone='070-2345-6789' where phone is null; -- 조건 안달아주면 모든 폰 데이터 변경됨

update customer set phone='000-5000-0001' where custid = 1;
update customer set phone='000-6000-0001' where custid = 2;
update customer set phone='000-7000-0001' where custid = 3;
update customer set phone='000-8000-0001' where custid = 4;
select * from customer;
commit;

/*
    2.3 삭제 (DELETE)
    
DELETE FROM cpdlqmfaud WHERE 조건;
*/
-- 고객 테이블에 테스트 데이터 입력 후 삭제
insert into customer(custid, name) values(7, '테스트');
insert all
    into customer(custid, name) values(8, '류현진')
    into customer(custid, name) values(9, '손흥민')
    select*from dual;

select * from customer; 
-- custid가 7인 고객 정보 삭제
delete from customer where custid=7;   -- 조건 안달아주면 모든 데이터 삭제됨
-- 주소가 null인 고객 삭제
delete from customer where address is null;
select * from customer;    
commit;


