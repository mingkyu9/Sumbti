package app.sum.bti.couple.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface CoupleMapper {

    // coupleZone 추천 List
    public List<CoupleVO.CoupleList> getCoupleList(Map<String,Object> param) throws SQLException;


    // 상세정보 입력하기
    public int saveDetail(CoupleVO.CoupleDetailInfo request) throws SQLException;


    public List<CoupleVO.CoupleDetailInfo> showDetailInfo(Map<String, Object> param) throws SQLException;


    // 접속유저가 버튼유저를 좋아하고 있는지 확인
    public int CoupleCheckExistList (Map<String,Object> param) throws SQLException;


    // 접속유저가 버튼유저를 좋아요 목록에 추가
    public int coupleAddLikeList (Map<String,Object> param) throws SQLException;

    // 접속유저가 버튼유저를 좋아요 목록에서 삭제
    public int coupleDeleteLikeList (Map<String,Object> param) throws SQLException;
}
