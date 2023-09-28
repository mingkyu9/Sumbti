package app.sum.bti.friend.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.friend.vo.FriendVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FriendMapper {

    // FriendZone 유저 목록
    public List<FriendVO.FriendList> getFriendList(Map<String,Object> param) throws SQLException;

    // 상세정보 입력하기
    public int saveDetail(FriendVO.FriendDetailInfo request) throws SQLException;


    // 해당유저의 상세정보 가져오기
    public List<FriendVO.FriendDetailInfo> showDetailInfo(Map<String, Object> param) throws SQLException;

    // 접속유저가 버튼유저를 좋아하고 있는지 확인
    public int FriendCheckExistList (Map<String,Object> param) throws SQLException;


    // 접속유저가 버튼유저를 좋아요 목록에 추가
    public int FriendAddLikeList (Map<String,Object> param) throws SQLException;

    // 접속유저가 버튼유저를 좋아요 목록에서 삭제
    public int FriendDeleteLikeList (Map<String,Object> param) throws SQLException;


}
