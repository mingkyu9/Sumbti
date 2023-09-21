package app.sum.bti.community.service;

import app.sum.bti.community.mapper.BoardMapper;
import app.sum.bti.community.vo.BoardVO;
import app.sum.bti.community.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

	private final BoardMapper mapper;
	private final PagingVO pageVO;
	
	@Value("${server.stored.file.path}")
	private String filePath;
	
	@Value("${server.editor.img.path}")
	private String editorPath;
	
	/**
	 * 게시글 리스트 가져오기 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public BoardVO.Response getBoardList(Map<String, Object> param) throws Exception {
		int totalSize = 0;
		int nowPageNumber = (int)param.get("nowPageNumber");
		totalSize = mapper.getBoardTotal(param);
		List<BoardVO.BoardList> boardList = new ArrayList<>();
		pageVO.dataInit(nowPageNumber, totalSize);

		if(totalSize > 0) {
			param.put("start",  pageVO.getBeginPage());
			param.put("end",  pageVO.getEndPage());

			boardList = mapper.getBoardList(param);
		}


		return BoardVO.Response
				.builder()
				.list(boardList)
				.totalsize(totalSize)
				.pageHTML(pageVO.pageHTML())
				.nowPageNumber(nowPageNumber)
				.build();
	}
	
	

	
	

}



