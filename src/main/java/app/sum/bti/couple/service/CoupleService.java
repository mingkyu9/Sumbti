package app.sum.bti.couple.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.couple.vo.CoupleVO;
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
public class CoupleService {

    private final CoupleMapper mapper;


    public List<CoupleVO.CoupleList> getCoupleList() throws SQLException {
        return mapper.getCoupleList();
    }

    public List<CoupleVO.CoupleList> getCoupleListPick() throws SQLException {
        return mapper.getCoupleList();
    }

    public List<CoupleVO.CoupleList> getCoupleListSelect(List<String> selectedMBTI) throws SQLException {
        List<CoupleVO.CoupleList> list = mapper.getCoupleListSelect();
        List<CoupleVO.CoupleList> listTemp = new ArrayList<>();
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

    public int saveDetail(CoupleVO.DetailsInfo detailRequest) throws SQLException {
        return  mapper.saveDetail(detailRequest);
    }

    public List<CoupleVO.DetailsInfo> showDetailInfo(String userId) throws SQLException{
            List<CoupleVO.DetailsInfo> detailsInfo = new ArrayList<>();
            Map<String, Object> param  = new HashMap<>();
                 param.put("userId", userId);
                 detailsInfo = mapper.showDetailInfo(param);
        return detailsInfo;
    }
}
