package app.sum.bti.friend.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.friend.vo.FriendVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface FriendMapper {

    // friendZone 추천 List
    public List<FriendVO.FriendList> getFriendList() throws SQLException;

    // friendZone 선택 List
    public List<FriendVO.FriendList> getFriendSelectedList() throws SQLException;


    // 상세정보 입력하기
    public int saveDetail(FriendVO.FriendDetailInfo request) throws SQLException;


    // 해당유저의 상세정보 가져오기
    public List<FriendVO.FriendDetailInfo> showDetailInfo(Map<String, Object> param) throws SQLException;
}
