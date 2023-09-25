package app.sum.bti.couple.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CoupleMapper {
    public List<CoupleVO.CoupleList> getCoupleList() throws SQLException;

    public List<CoupleVO.CoupleList> getCoupleListSelect() throws SQLException;
}
