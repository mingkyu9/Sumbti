package app.sum.bti.couple.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface CoupleMapper {

    // coupleZone 추천 List
    public List<CoupleVO.CoupleList> getCoupleList() throws SQLException;

    // coupleZone 선택 List
    public List<CoupleVO.CoupleList> getCoupleListSelect() throws SQLException;


    // 상세정보 입력하기
    public int saveDetail(CoupleVO.DetailsInfo request) throws SQLException;


    public List<CoupleVO.DetailsInfo> showDetailInfo(Map<String, Object> param) throws SQLException;
}
