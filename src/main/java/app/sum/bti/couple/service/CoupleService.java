package app.sum.bti.couple.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.friend.vo.FriendVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoupleService {

    private final CoupleMapper mapper;


    public List<CoupleVO.CoupleList> getCoupleList(Map<String,Object> param) throws SQLException {

        List<CoupleVO.CoupleList> list = mapper.getCoupleList(param);
        List<CoupleVO.CoupleList> checkList = new ArrayList<>();
        String hateMbti = (String)param.get("userHateMbti");

        if( hateMbti != null) {
            String[] hateUser = hateMbti.split("");
            if (hateUser.length == 0 || hateUser == null) {
                Collections.sort(list, new Comparator<CoupleVO.CoupleList>() {
                    @Override
                    public int compare(CoupleVO.CoupleList couple1, CoupleVO.CoupleList couple2) {
                        return Integer.compare(couple2.getLikeCount(), couple1.getLikeCount());// 내림차순으로 변경
                    }// 내림차순으로 변경

                });
                return list;
            } else {
                for (CoupleVO.CoupleList coupleList : list) {
                    boolean flag = true;
                    for (String mbti : hateUser) {
                        if (coupleList.getUserMbti().contains(mbti)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        checkList.add(coupleList);
                    }
                }
                return checkList;
            }
        }else{
            return list;
        }
    }


    public List<CoupleVO.CoupleList> getCoupleListSelect(List<String> selectedMBTI, Map<String,Object> param) throws SQLException {
        List<CoupleVO.CoupleList> list = mapper.getCoupleList(param);
        List<CoupleVO.CoupleList> listTemp = new ArrayList<>();
        if (selectedMBTI == null) {
            return list;
        }

        for (int i = 0; i < list.size(); i++) {
            boolean findMbti = true;

            for (int j = 0; j < selectedMBTI.size(); j++) {
                if (list.get(i).getUserMbti().toLowerCase().contains(selectedMBTI.get(j).toLowerCase())) {
                    findMbti = false;
                    break;
                }
            }

            if (findMbti) {
                listTemp.add(list.get(i));
            }

        }
        return listTemp;
    }

    public int saveDetail(CoupleVO.CoupleDetailInfo detailRequest) throws SQLException {
        return mapper.saveDetail(detailRequest);
    }

    public List<CoupleVO.CoupleDetailInfo> showDetailInfo(String userId) throws SQLException {
        List<CoupleVO.CoupleDetailInfo> detailsInfo = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
        detailsInfo = mapper.showDetailInfo(param);
        return detailsInfo;
    }

    //  접속유저가 버튼유저를 좋아하고 있는지 확인
    @Transactional
    public int checkExistList(Map<String, Object> param) throws SQLException {
        return mapper.CoupleCheckExistList(param);
    }

    //  접속유저가 버튼유저를 좋아요 목록에 추가
    @Transactional
    public int addLikeList(Map<String, Object> param) throws SQLException {
         return mapper.coupleAddLikeList(param);
    }


    //  접속유저가 버튼유저를 좋아요 목록에서 삭제
    @Transactional
    public int deleteLikeList(Map<String, Object> param) throws SQLException {
        return  mapper.coupleDeleteLikeList(param);
    }
}
