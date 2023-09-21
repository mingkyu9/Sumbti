package app.sum.bti.community.vo;

import org.springframework.stereotype.Component;

/**
 * Component Annotation 을 붙이면
 * 스프링이 제어를 관리(IOC) 
 * 그래서 의존성 주입(DI)를 통해서 객체 생성 가능 
 */
@Component
public class PagingVO {

	private int totalPage; // 전체 페이지 수
	private int nowPageNumber; // 현재 페이지 번호
	private int blockPageCount = 3; // 한블럭당 부여할 페이지 개수
	private int pagePerRows ; // 한페이지당 보여줄 데이터의 개수
	private int totalRows; // 전제 데이터 수 
	private int nowBlock; // 현재 블럭 위치
	private int totalBlock; // 전체 블럭 개수 
	
	
	public void  dataInit(int nowPageNumber, int totalRows) {
		this.nowPageNumber = nowPageNumber;
		this.totalRows = totalRows;
		this.pagePerRows = 3;
	}
	

	public void dataInit(int nowPageNumber, int totalRows, int pagePerRows) {
		this.nowPageNumber = nowPageNumber;
		this.totalRows = totalRows;
		this.pagePerRows = pagePerRows;
	}
	
	
	//SQL에서 조건값으로 사용한 메서드
	public int getBeginPage() {
		//시작위치
		return this.nowPageNumber * pagePerRows  ;
	}
	
	//SQL에서 조건값으로 사용한 메서드
	public int getEndPage() {
		return  this.pagePerRows ;
	}
	
	//전체 페이지수 계산
	public int getTotalPage( ) {
		 //전체 데이터를 실수로 변경해서 나누기 한다.
		// 소수점도 페이지에 추가해야하기 때문에 실수로 계산 
		double total = (double)this.totalRows / this.pagePerRows;
		//소수점이 나오면 올림처리하여 페이지 번호 증가 시킴
		int result =  (int)Math.ceil(total);
		//리턴 
		return result;
	}

	//현재 블럭 위치 
	public int getNowBlock() {
		 double retVal = (double)this.nowPageNumber  / this.blockPageCount;
		 return (int)Math.floor(retVal);
	}
	
	//전체 블럭 개수
	public int getTotalBlock(int totalPage) {
		//전체 페이지 수  /  블럭당 페이지 수
		 double retVal = Math.ceil((double)this.getTotalPage() / this.blockPageCount);
		 return (int)Math.floor(retVal);
		
	}
	
	
	// 페이징 html 을 자바로 그리기 
	 public String pageHTML() {
		 
		 this.totalPage = this.getTotalPage();
		 this.nowBlock = this.getNowBlock();
		 this.totalBlock = this.getTotalBlock(totalPage);
		 
		 int pageNum = 0;
		 
		 //html 코드를 넣을 빌더
		 StringBuilder sb = new StringBuilder();
		 //페이지 처음으로 돌아가기 
		 if(nowPageNumber  >  0) {
			 sb.append("<li class='page-item'>");
  			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"movePage(0);\" >");
  			  sb.append("First</a></li>");
		 }
		 
		 if(this.nowBlock > 0) {
			 //이전블럭으로 가기 작성 하기 위함 
			  pageNum =  ( nowBlock  * blockPageCount) -1;
			  sb.append("<li class='page-item'>");
			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"movePage(" + pageNum +");\" >");
			  sb.append("Previous</a></li>");
		 }
		 
		 //페이지 그리기 
		 String isActive = "";
		 for(int i = 0;  i < this.blockPageCount;  i++) {
			 isActive = "";
			 pageNum = (nowBlock * blockPageCount) + i;
			 
			 //그리는 페이지번호와 현재 위치가 같은경우 active;
			 if(pageNum == nowPageNumber) {
				 isActive = "active";
			 }
			 
			  sb.append("<li class='page-item " + isActive+"'>");
			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"movePage(" + pageNum +");\" >");
			  sb.append((pageNum+1)+"</a></li>");
			  
			  
			  //페이지가 풀로 차지 않을 경우, 빈페이지 만들지 말고 있는 페이지까지만 그리도록 멈춘다 
			  if ( ((nowBlock * blockPageCount) + i + 1) == totalPage) {
				  break;
			  }
			  	  
		 }
		 
		 //다음블록으로 이동
		 if(nowBlock +1 <  totalBlock) {
			  pageNum =  (nowBlock + 1) * blockPageCount;
			  sb.append("<li class='page-item'>");
			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"movePage(" + pageNum +");\" >");
			  sb.append("Next</a></li>");
         }
		 
		 if(this.nowPageNumber +1  < totalPage) {
			  pageNum =  this.totalPage - 1;
			  sb.append("<li class='page-item'>");
			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"movePage(" + pageNum +");\" >");
			  sb.append("Last</a></li>");
		 }
		 
		 return sb.toString();
	 }
	
}
