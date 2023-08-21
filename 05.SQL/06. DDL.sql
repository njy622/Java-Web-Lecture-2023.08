/*
    3. DDL(Data Definition Language)
*/

/*
    3.1 CREATE TABLE문
 다음과 같은 속성을 가진 NewBook 테이블을 생성하시오, 
 정수형은 NUMBER를, 문자형은 가변형 문자타입인 VARCHAR2를 사용
    
    bookid(도서번호) – NUMBER
    bookname(도서이름) – VARCHAR2(20)
    publisher(출판사) – VARCHAR2(20)
    price(가격) – NUMBER
*/
create table NewBook (       --exist if not은 해당버전에선 사용 못함
    bookid number primary key,
    bookname varchar2(20),
    publisher varchar2(20),
    price number
);
drop table NewBook;   -- NewBook 테이블 삭제
/*  기본키 - 복합키(도서이름, 출판사)
    도서이름 - 같은 값을 가지면 안됨
    출판사 - Null이 아니어야 함
    가격 - 기본 10000원, 1000원 이상이어야함 
*/
create table NewBook (
    bookname varchar2(40) unique,    -- unique 같은값을 가지면 안됨
    publisher varchar2(20) not null,
    price number default 10000  check(price >=1000), -- default 선언하고 꼭 안써도 상관없음
    primary key (bookname, publisher)
);
/* (3장 강의 SQL기초 _ P.67)
질의 3-35 다음과 같은 속성을 가진 NewCustomer 테이블을 생성하시오
    custid(고객번호) - NUMBER, 기본키
    name(이름) – VARCHAR2(40)
    address(주소) – VARCHAR2(40)
    phone(전화번호) – VARCHAR2(30)
*/

create table NewCustomer (
    custid number primary key,
    name varchar2(40),      -- name (파란색)키워드 안나오게 하려면? uname이나 앞에 문자를 붙여서 정의하는게 좋음
    address varchar2(40),
    phone varchar2(30)
 );
 
 /*
    VARCHAR(10)는 글자 수 사용하고 나머지 남은 공백을 버림으로 사용하지 않음(가변길이 사용)
    CHAR(10)는 글자 수를 사용하고도 나머지 자리수를 공백으로 남겨둠(고정길이 사용)
 */
 
 /*
    3.2 ALTER TABLE    
 */
-- NewCustomer 테이블에 email varchar2(40) 추가
alter table newcustomer add email varchar2(40);
desc newcustomer;   -- 해당 테이블의 구성을 보고싶으면 "desc + 테이블명"

-- phone 필드를 제거
alter table newcustomer drop column phone;
desc newcustomer;
-- name 필드를 cname 으로 이름을 변경하고, not null 속성 추가
alter table newcustomer modify name varchar2(40) not null;
alter table newcustomer rename column name to cname;
/*
    3.3 DROP TABLE
*/
drop table newcustomer;
drop table newbook;
commit;