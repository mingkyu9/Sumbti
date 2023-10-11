package app.sum.bti.sign.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.sign.mapper.SignMapper;
import app.sum.bti.sign.vo.SignVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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

    // 닉네임 중복 확인
    public int checkEqualNick(Map<String,Object> checkNick) throws SQLException {
        return mapper.checkEqualNick(checkNick);
    }

    public int signCheck(SignVO.SignInfo singInfo) throws SQLException {
        Map<String,Object> param = new HashMap<String,Object>();

        List<String> lMbti = singInfo.getLoveChooseMBTI();
        List<String> hMbti = singInfo.getHateChooseMBTI();
        String defaultValue ="";
        if(lMbti != null) {
            // StringBuilder를 사용하여 문자열을 연결
            StringBuilder result = new StringBuilder();
            for (String str : lMbti) {
                result.append(str);
            }
            singInfo.setLoveMbti(result.toString());
        }

        if(hMbti != null) {
            StringBuilder result2 = new StringBuilder();
            for (String str : hMbti) {
                result2.append(str);
            }
            singInfo.setHateMbti(result2.toString());
        }

        return mapper.signInsert(singInfo);
    }
}