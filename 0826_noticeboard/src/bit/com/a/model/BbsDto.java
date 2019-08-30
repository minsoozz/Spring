package bit.com.a.model;

import java.io.Serializable;
/*
DROP TABLE BBS
CASCADE CONSTRAINTS;

DROP SEQUENCE SEQ_BBS;

CREATE TABLE BBS(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	
	REF NUMBER(8) NOT NULL,
	STEP NUMBER(8) NOT NULL,
	DEPTH NUMBER(8) NOT NULL,
	
	TITLE VARCHAR2(200) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	WDATE DATE NOT NULL,
	PARENT NUMBER(8) NOT NULL,
	
	DEL NUMBER(1) NOT NULL,
	READCOUNT NUMBER(8) NOT NULL
);

CREATE SEQUENCE SEQ_BBS
START WITH 1
INCREMENT BY 1;
-- ID는 외래키이기 때문에 MEMBER TABLE에서 연결을 해주어야한다
ALTER TABLE BBS
ADD CONSTRAINT FK_BBS_ID FOREIGN KEY(ID)
REFERENCES MEMBERLIST(ID);
*/
public class BbsDto implements Serializable {

	private int seq; // 시퀀스
	private String id; // 작성자

	// 달라지는 것
	private int ref; // 그룹 번호		글과 댓글은 같은번호이다
	private int step; // 행(row)번호	, step은 다음칸 depth은 안쪽으로 들어감
	private int depth; // 깊이
	// - 3가지 답변 게시판을 하기 위한 요소

	private String title; // 제목
	private String content; // 내용
	private String wdate; // 날짜
	private int parent; // 부모글 번호

	private int del; // 삭제여부 확인
	private int readcount; // 조회수

	public BbsDto() {
	}

	public BbsDto(int seq, String id, int ref, int step, int depth, String title, String content, String wdate,
			int parent, int del, int readcount) {
		super();
		this.seq = seq;
		this.id = id;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.parent = parent;
		this.del = del;
		this.readcount = readcount;
	}

	public BbsDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWdate() {
		return wdate;
	}

	public void setWdate(String wdate) {
		this.wdate = wdate;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	@Override
	public String toString() {
		return "BbsDto [seq=" + seq + ", id=" + id + ", ref=" + ref + ", step=" + step + ", depth=" + depth + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate + ", parent=" + parent + ", del=" + del
				+ ", readcount=" + readcount + "]";
	}

}
