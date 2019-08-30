package bit.com.a.model;

import java.io.Serializable;
 
 
public class BbsOrderDto implements Serializable{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /** 검색조건. 0=제목, 1=내용 2=id*/
   private int cond;   
   
   private String searchType = "";
   
   /** 검색어 */
   private String keyword;
   
   /** 현재페이지 */
   private int pageNum = 1;
   
   /** 페이지에 보여줄 데이터 갯수 */
   private int recordCountPerPage = 10;
      
   /** 현재 페이지의 시작 seq    */
   private int startSeq = 1;
   
   /** 현재 페이지의 끝 seq */
   private int endSeq = 10;
      
   /**총 페이지 수 */
   private int totalPage = 1;

   /** 현재 페이지 네비게이션 사이즈 */
   private int pageNavSize = 5;
   
   /** 최대 페이지 네비게이션 사이즈 */
   private int maxNavSize = 10;

   /** 페이지 네비게이션 첫 인덱스 */
   private int firstNavIndex = 1;

   /** 페이지 네비게이션 마지막 인덱스 */
   private int lastNavIndex = 1;   

   /** 검색 제한 레코드 시작 위치 */
   // private int limitOffSet = 1;
   
   /**현재 페이지 번호와 총 게시글의 수를 매개변수로 받아서 페이징 객체 생성
    * @param pageNum
    * @param totalSize
    */
   public BbsOrderDto() {
   
   }
   
   public BbsOrderDto(int pageNum, int totalSize) {
      super();
      this.pageNum = pageNum;
      this.setRecordCountPerPage(10);
      this.startSeq = ( ( pageNum - 1 ) * this.getRecordCountPerPage() ) + 1;
      this.endSeq = startSeq + this.getRecordCountPerPage() - 1;
      //페이지 네비게이션의 최대 사이즈
      this.maxNavSize = (totalSize % recordCountPerPage == 0) ? (totalSize / recordCountPerPage) : (totalSize / recordCountPerPage) + 1;
      this.setNav(totalSize);
   }
   
     public BbsOrderDto(int cond, String keyword, int pageNum, int totalSize) {
      this.pageNum = pageNum;
      this.setRecordCountPerPage(10);
      this.startSeq = ( ( pageNum - 1 ) * this.getRecordCountPerPage() ) + 1;
      this.endSeq = startSeq + this.getRecordCountPerPage() - 1;      
      //페이지 네비게이션의 최대 사이즈
      this.maxNavSize = (totalSize % recordCountPerPage == 0) ? (totalSize / recordCountPerPage) : (totalSize / recordCountPerPage) + 1;
      this.setNav(totalSize);
      this.cond = cond;
      this.keyword = keyword;
      switch( cond ) {
         case 0: this.searchType="title"; break;
         case 1: this.searchType="content"; break;
         case 2: this.searchType="id"; break;
         default: this.searchType= "all"; break;
      }      
      //System.out.println("[BbsOrderDto] searchType:" + searchType + " , keyword:" + keyword);
   }
   
   /** All Args Constructor
    * @param pageIndex
    * @param recordCountPerPage
    * @param startSeq
    * @param endSeq
    * @param totalPage
    * @param pageSize
    * @param firstNavIndex
    * @param lastNavIndex
    */
     public BbsOrderDto(int pageNum, int recordCountPerPage, int startSeq, int endSeq, int totalPage, int pageSize,
         int firstNavIndex, int lastNavIndex) {
      super();
      this.pageNum = pageNum;
      this.recordCountPerPage = recordCountPerPage;
      this.startSeq = startSeq;
      this.endSeq = endSeq;
      this.totalPage = totalPage;
      this.pageNavSize = pageSize;
      this.firstNavIndex = firstNavIndex;
      this.lastNavIndex = lastNavIndex;
   }
   
   /**네비게이션 바 설정
    * 12345 : 1 ~ 5
    * 678910 : 6 ~ 10
    * (7/5)*5 +1
    * (3/5)*5 +1
    */
   public void setNav(int totalSize) {
      //페이지 네비게이션의 최대 사이즈
      this.maxNavSize = (totalSize % recordCountPerPage == 0) ? (totalSize / recordCountPerPage) : (totalSize / recordCountPerPage) + 1;
      this.firstNavIndex = (this.pageNum / pageNavSize) * pageNavSize + 1;
      //마지막 네비게이션 바 인덱스. 전체 글 수가 한 페이지 당 글 수로 나누어 떨어지면 -0 아니면 -1
      int temp = this.firstNavIndex + pageNavSize - 1 ;
      this.lastNavIndex = (temp > maxNavSize) ? maxNavSize : temp ;
      //System.out.println("totalSize : " + totalSize + " , recordCountPerPage : " + recordCountPerPage + " , temp : " + temp + " , ddd " +  (( (totalSize % recordCountPerPage) == 0 )?3:2 ) );
      //System.out.println("firstNavIndex : " + firstNavIndex + " , lastNavIndex : " + lastNavIndex + " , maxNavSize : " + maxNavSize);
   }

public int getCond() {
	return cond;
}

public void setCond(int cond) {
	this.cond = cond;
}

public String getsearchType() {
	return searchType;
}

public void setsearchType(String searchType) {
	this.searchType = searchType;
}

public String getKeyword() {
	return keyword;
}

public void setKeyword(String keyword) {
	this.keyword = keyword;
}

public int getPageNum() {
	return pageNum;
}

public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}

public int getRecordCountPerPage() {
	return recordCountPerPage;
}

public void setRecordCountPerPage(int recordCountPerPage) {
	this.recordCountPerPage = recordCountPerPage;
}

public int getStartSeq() {
	return startSeq;
}

public void setStartSeq(int startSeq) {
	this.startSeq = startSeq;
}

public int getEndSeq() {
	return endSeq;
}

public void setEndSeq(int endSeq) {
	this.endSeq = endSeq;
}

public int getTotalPage() {
	return totalPage;
}

public void setTotalPage(int totalPage) {
	this.totalPage = totalPage;
}

public int getPageNavSize() {
	return pageNavSize;
}

public void setPageNavSize(int pageNavSize) {
	this.pageNavSize = pageNavSize;
}

public int getMaxNavSize() {
	return maxNavSize;
}

public void setMaxNavSize(int maxNavSize) {
	this.maxNavSize = maxNavSize;
}

public int getFirstNavIndex() {
	return firstNavIndex;
}

public void setFirstNavIndex(int firstNavIndex) {
	this.firstNavIndex = firstNavIndex;
}

public int getLastNavIndex() {
	return lastNavIndex;
}

public void setLastNavIndex(int lastNavIndex) {
	this.lastNavIndex = lastNavIndex;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

@Override
public String toString() {
	return "BbsOrderDto [cond=" + cond + ", searchType=" + searchType + ", keyword=" + keyword + ", pageNum=" + pageNum
			+ ", recordCountPerPage=" + recordCountPerPage + ", startSeq=" + startSeq + ", endSeq=" + endSeq
			+ ", totalPage=" + totalPage + ", pageNavSize=" + pageNavSize + ", maxNavSize=" + maxNavSize
			+ ", firstNavIndex=" + firstNavIndex + ", lastNavIndex=" + lastNavIndex + "]";
}  
   
   
}