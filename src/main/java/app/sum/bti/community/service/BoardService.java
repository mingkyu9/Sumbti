package app.sum.bti.community.service;

import app.sum.bti.community.mapper.BoardMapper;
import app.sum.bti.community.vo.BoardVO;
import app.sum.bti.community.vo.PagingVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardService {

	private final BoardMapper mapper;
	private final PagingVO pageVO;



	/**
	 * 게시글 리스트 가져오기
	 *
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public BoardVO.Response getBoardList(Map<String, Object> param) throws Exception {
		int totalSize = 0;
		int nowPageNumber = (int) param.get("nowPageNumber");
		String categoryId= (String) param.get("categoryId");
		totalSize = mapper.getBoardTotal(param);
		List<BoardVO.BoardList> boardList = new ArrayList<>();
		pageVO.dataInit(nowPageNumber, totalSize);

		if (totalSize > 0) {
			param.put("start", pageVO.getBeginPage());
			param.put("end", pageVO.getEndPage());

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

	@Transactional
	public int writeBoard(BoardVO.Request boardRequest) throws Exception {
		boardRequest.setUserId("amin");
		int result = mapper.writeBoard(boardRequest);

		return result;
	}

	public BoardVO.Detail getBoardDetail(int boardNum) throws Exception {

		return mapper.getBoardDetail(boardNum);
	}
	@Transactional
	public int updateBoard(BoardVO.UpdateRequest updateBoard) throws Exception {
		BoardVO.Detail detail = mapper.getBoardDetail(updateBoard.getBoardNum());
		//기존의 저장된 파일 리스트

		int result = 0;

		//현재 시간을 가져온다 -서버 시간 기준
		LocalDateTime nowTime = LocalDateTime.now();
		updateBoard.setUpdateDate(nowTime);

		// 게시글을 업데이트
		result = mapper.updateBoard(updateBoard);


		return result;
	}
	@Transactional
	public void deleteBoard(int boardNum) throws Exception {
//		BoardVO.Detail detail = mapper.getBoardDetail(boardNum);
		mapper.deleteBoard(boardNum);
	}
}
