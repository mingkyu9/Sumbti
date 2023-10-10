package app.sum.bti.sign.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.sign.mapper.SignMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SignService {

    private final SignMapper mapper;

    // 아이디 중복 확인
    public int checkEqualId(Map<String,Object> checkId) throws SQLException {
        return mapper.checkEqualId(checkId);
    }
}
