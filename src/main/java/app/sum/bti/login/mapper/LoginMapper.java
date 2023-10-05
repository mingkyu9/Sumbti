package app.sum.bti.login.mapper;

import app.sum.bti.login.vo.LoginVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface LoginMapper {

    public int checkUserInfo(LoginVO.LoginInfo params) throws SQLException;

    public LoginVO.LoginUserInfo getLoginUserInfo(LoginVO.LoginInfo params) throws SQLException;

}
