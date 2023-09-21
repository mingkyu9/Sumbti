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

    public List<CoupleVO.CoupleList> getCoupleListSelect(List<String> selectedMBTI) throws SQLException {
        List<CoupleVO.CoupleList> list = mapper.getCoupleList();
        for(int i=0; i<list.size(); i++) {
            if(selectedMBTI.get(i).contains(list.get(i).getUserMbti())) {
                list.remove(i);
            }
        }
        return list;
    }
}
