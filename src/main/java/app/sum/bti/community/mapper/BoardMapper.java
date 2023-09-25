package app.sum.bti.community.mapper;

import app.sum.bti.community.vo.BoardFileVO;
import app.sum.bti.community.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

	/**
	 * 게시글 전체 개수 가져오기 
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public int getBoardTotal(Map<String, Object> param ) throws SQLException;
	
	/**
	 * 게시글 가져오기
	 * @param param
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO.BoardList> getBoardList(Map<String, Object> param) throws SQLException;

/**
 * 게시글 쓰기 
 * @param request
 * @return
 * @throws SQLException
 */
	public int writeBoard(BoardVO.Request request) throws SQLException;


	
	/**
	 * 게시글 상세보기 
	 * @param boardNum
	 * @return
	 * @throws SQLException
	 */
	public BoardVO.Detail getBoardDetail(@Param("boardNum") int boardNum ) throws SQLException;
	
	/**
	 * 조회수 증가 
	 * @param boardNum
	 * @return
	 * @throws SQLException
	 */
	public int updateBoardCount(@Param("boardNum") int boardNum ) throws SQLException;
	

	
	/**
	 * 게시글 삭제
	 * @param boardNum
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(@Param("boardNum") int boardNum ) throws SQLException;
	
	/**
	 * 게시글 수정하기
	 * @param updateBoard
	 * @return
	 * @throws SQLException
	 */
	public int updateBoard(BoardVO.UpdateRequest updateBoard) throws SQLException;

}
