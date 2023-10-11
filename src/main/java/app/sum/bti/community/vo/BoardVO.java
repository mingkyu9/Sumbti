package app.sum.bti.community.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public class BoardVO {


	@Data
	@Builder  // 빌더패턴 등록 annotation
	@AllArgsConstructor  // 모든 맴버변수를 파라메터로 가지는 생성자 만들기
	@NoArgsConstructor //기본생생자 만들기
	public static class Response{
		private List<BoardList> list;
		private int nowPageNumber;
		private String pageHTML;
		private int totalsize;
	}

	@Data
	public static class BoardList{

		private int boardNum;
		private String userId;
		private String boardTitle;
		private String userMbti;
		private String userNick;
		private int boardLike;
		private String boardTime;
		private String categoryId;

	}

	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Detail{

		private int boardNum;
		private String boardTitle;
		private String boardMbti;
		private String userId;
		private int boardLike;
		private String boardTime;
		private String boardContents;
		private String updateDate;
	}


	@Data
	public static class UpdateRequest{

		private int boardNum;
		private String boardTitle;
		private String boardContents;
		//private MultipartFile[] attachFile;
		//java 8 이후부터 사용가능
		// Date 보다 정확한 표현
		private String updateDate;

	}

	@Data
	public static class Request{
		private String boardTitle;
		private String category;
		private String userId;
		private String boardContents;


	}


}
