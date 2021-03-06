DROP TABLE VOTER
CASCADE CONSTRAINTS;

DROP SEQUENCE VOTER_SEQ;

CREATE TABLE VOTER(
	VOTERID NUMBER NOT NULL,
	POLLID NUMBER NOT NULL,
	POLLSUBID NUMBER NOT NULL,
	ID VARCHAR2(50) NOT NULL,
	REGDATE DATE NOT NULL
	CONSTRAINT VOTER_PK PRIMARY KEY(VOTERID)
);

CREATE SEQUENCE VOTER_SEQ
START WITH 1
INCREMENT BY 1;

--외래키 연결 -- 
ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK
FOREIGN KEY(POLLID)
REFERENCES POLL(POLLID);

ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK2
FOREIGN KEY(POLLSUBID)
REFERENCES POLLSUB(POLLSUBID);

ALTER TABLE VOTER ADD CONSTRAINT VOTER_FK3
FOREIGN KEY(ID)
REFERENCES MEMBER(ID)
