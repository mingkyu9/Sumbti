package app.sum.bti.community.vo;

import lombok.Data;

@Data
public class BoardFileVO {

	private int fileId;
	private int boardId;
	private String filePath;
	private String originFileName;
	private String storedFileName;
	private String createDate;
	
	
}
