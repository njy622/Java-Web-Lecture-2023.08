
/* Drop Tables */

DROP TABLE Food CASCADE CONSTRAINTS;




/* Create Tables */

CREATE TABLE Food
(
	id number NOT NULL,
	nickname varchar2(20),
	foodType varchar2(20) NOT NULL,
	content varchar2(4000),
	taste varchar2(20) DEFAULT '0',
	modTime timestamp DEFAULT CURRENT_TIMESTAMP,
	viewCount number DEFAULT 0 NOT NULL,
	isDeleted number DEFAULT 0 NOT NULL,
	PRIMARY KEY (id)
);



