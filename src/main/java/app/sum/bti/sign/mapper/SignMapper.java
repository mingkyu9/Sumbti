package app.sum.bti.sign.mapper;

import app.sum.bti.sign.vo.SignVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface SignMapper {

    // 아이디 중복 확인하기
    public int checkEqualId(Map<String, Object> checkId) throws SQLException;

    // 닉네임 중복확인
    public int checkEqualNick(Map<String, Object> checkNick) throws SQLException;

    public int signInsert(SignVO.SignInfo singInfo) throws SQLException;
}
