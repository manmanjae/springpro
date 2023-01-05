-- tblBoard --
create table tblBoard(
	idx int not null,
	memID varchar(20) not null,
	title varchar(100) not null,
	content varchar(2000) not null,
	writer varchar(30) not null,
	indate datetime default now(),
	count int default 0,
	boardGroup int,
	boardSequence int,
	boardLevel int,
	boardAvailable int,
	primary key(idx)
);

select max(idx) from tblBoard;
select IFNULL(max(idx)+1, 1) from tblBoard;
select IFNULL(max(boardGroup)+1, 0) from tblBoard;

insert into tblBoard
select IFNULL(max(idx)+1, 1),'root','게시판연습','게시판연습','관리자',now(),0,IFNULL(max(boardGroup)+1, 0),0,0,1 
from tblBoard;

insert into tblBoard
select IFNULL(max(idx)+1, 1),'test','게시판연습','게시판연습','김민재',now(),0,IFNULL(max(boardGroup)+1, 0),0,0,1 
from tblBoard;

insert into tblBoard
select IFNULL(max(idx)+1, 1),'test','게시판연습','게시판연습','test',now(),0,IFNULL(max(boardGroup)+1, 0),0,0,1 
from tblBoard;

select * from tblBoard;

delete from tblBoard where idx=0;
delete from tblBoard where idx=1;
delete from tblBoard where idx=2;

create table tblMember(
	memID varchar(50) not null, -- 회원 id
	memPwd varchar(50) not null, -- 회원비번
	memName varchar(50) not null,-- 회원이름
	memPhone varchar(50) not null, -- 회원전화번호
	memAddr varchar(100), -- 회원주소
	latitude decimal(13,10), -- 현재위치위도
	longitude decimal(13,10), -- 현재위치경도
	primary key(memID)
);

insert into tblMember(memID, memPwd, memName, memPhone)
values('root','root','관리자','010-1111-1111');
insert into tblMember(memID, memPwd, memName, memPhone)
values('김민재','김민재','김민재','010-4816-9526');
insert into tblMember(memID, memPwd, memName, memPhone)
values('test','test','test','010-1234-5678');

select * from tblMember;