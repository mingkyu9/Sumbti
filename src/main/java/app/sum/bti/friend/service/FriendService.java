package app.sum.bti.friend.service;

import app.sum.bti.friend.mapper.FriendMapper;
import app.sum.bti.friend.vo.FriendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class FriendService {

    private final FriendMapper mapper;

    // FriendZoneList 유저 목록
    public List<FriendVO.FriendList> getFriendList(Map<String,Object> param) throws SQLException {
        return mapper.getFriendList(param);
    }


    // FriendZoneSelectedList 유저 목록
    public List<FriendVO.FriendList> getFriendSelectedList(List<String> selectedMBTI, Map<String,Object> param) throws SQLException {

         List<FriendVO.FriendList> list = mapper.getFriendList(param);
         List<FriendVO.FriendList> listTemp = new ArrayList<>();

         if (selectedMBTI == null) {
            return list;
         }

        for (int i = 0; i < list.size(); i++) {
            boolean findMbti = true;

            for (int j = 0; j < selectedMBTI.size(); j++) {
                if(list.get(i).getUserMbti().toLowerCase().contains(selectedMBTI.get(j).toLowerCase())){
                    findMbti = false;
                    break;
                }
            }

            if(findMbti){
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }


    // 상세 정보 저장하기
    public int saveDetail(FriendVO.FriendDetailInfo detailRequest) throws SQLException {
        return  mapper.saveDetail(detailRequest);
    }

    // 해당유저의 상세정보 가져오기
    public List<FriendVO.FriendDetailInfo> showDetailInfo(String userId) throws SQLException{
            List<FriendVO.FriendDetailInfo> detailsInfo = new ArrayList<>();
            Map<String, Object> param  = new HashMap<>();
                 param.put("userId", userId);
                 detailsInfo = mapper.showDetailInfo(param);
        return detailsInfo;
    }

    //  접속유저가 버튼유저를 좋아하고 있는지 확인
    @Transactional
    public int checkExistListFriend(Map<String,Object> param) throws  SQLException{
        return mapper.FriendCheckExistList(param);
    }

    //  접속유저가 버튼유저를 좋아요 목록에 추가
    @Transactional
    public int addLikeListFriend(Map<String,Object> param) throws  SQLException{
        return mapper.FriendAddLikeList(param);
    }


    //  접속유저가 버튼유저를 좋아요 목록에서 삭제
    @Transactional
    public int deleteLikeListFriend(Map<String,Object> param) throws  SQLException{
        return mapper.FriendDeleteLikeList(param);
    }


}
