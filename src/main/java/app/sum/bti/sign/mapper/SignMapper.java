package app.sum.bti.sign.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface SignMapper {

    // 아이디 중복 확인하기
    public int checkEqualId(Map<String, Object> checkId) throws SQLException;
}
