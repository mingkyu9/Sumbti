package app.sum.bti.login.service;

import app.sum.bti.login.mapper.LoginMapper;
import app.sum.bti.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper mapper;

    // DB에 있는 유저 아이디 비밀번호 확인하기
    public int checkUserInfo(LoginVO.LoginInfo params) throws SQLException{
        return mapper.checkUserInfo(params);
    }

    public LoginVO.LoginUserInfo getLoginUserInfo(LoginVO.LoginInfo params) throws SQLException{
        return mapper.getLoginUserInfo(params);
    }


}
