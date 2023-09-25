package app.sum.bti.couple.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.couple.vo.CoupleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoupleService {

    private final CoupleMapper mapper;

    public List<CoupleVO.CoupleList> getCoupleList() throws SQLException {
        return mapper.getCoupleList();
    }

    public List<CoupleVO.CoupleList> getCoupleListPick() throws SQLException {
        return mapper.getCoupleList();
    }

    public List<CoupleVO.CoupleList> getCoupleListSelect(List<String> selectedMBTI) throws SQLException {
        List<CoupleVO.CoupleList> list = mapper.getCoupleList();
        if (selectedMBTI == null) {
            return list;
        }

        for (int i = 0; i < selectedMBTI.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (selectedMBTI.get(i).contains(list.get(j).getUserMbti())) {
                    list.remove(j);
                }
            }

        }
        return list;
    }
}
