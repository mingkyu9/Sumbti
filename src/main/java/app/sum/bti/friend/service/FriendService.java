package app.sum.bti.friend.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.friend.mapper.FriendMapper;
import app.sum.bti.friend.vo.FriendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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


    public List<FriendVO.FriendList> getFriendList() throws SQLException {
        return mapper.getFriendList();
    }

    public List<FriendVO.FriendList> getFriendListPick() throws SQLException {
        return mapper.getFriendList();
    }

    public List<FriendVO.FriendList> getFriendSelectedList(List<String> selectedMBTI) throws SQLException {
        List<FriendVO.FriendList> list = mapper.getFriendSelectedList();
        List<FriendVO.FriendList> listTemp = new ArrayList<>();
        if (selectedMBTI == null) {
            return list;
        }

        for (int i = 0; i < list.size(); i++) {
            boolean findMbti = true;
            log.info("=======adfaffafasasfsafsdfdasfasdfsadfsadfsadfsafasfasfadsfadsf====");
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



    public int saveDetail(FriendVO.FriendDetailInfo detailRequest) throws SQLException {
        return  mapper.saveDetail(detailRequest);
    }

    public List<FriendVO.FriendDetailInfo> showDetailInfo(String userId) throws SQLException{
            List<FriendVO.FriendDetailInfo> detailsInfo = new ArrayList<>();
            Map<String, Object> param  = new HashMap<>();
                 param.put("userId", userId);
                 detailsInfo = mapper.showDetailInfo(param);
        return detailsInfo;
    }
}
