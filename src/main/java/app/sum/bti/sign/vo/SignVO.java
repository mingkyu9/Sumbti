package app.sum.bti.sign.vo;

import lombok.Data;

import java.util.List;

public class SignVO {

    @Data
    public static class SignInfo{

        private String idInput;              // 아이디
        private String passwdInput;          // 비밀번호
        private String emailInput;           // 이메일
        private String locationInput;        // 지역
        private String jobInput;             // 직업
        private String nameInput;            // 닉네임
        private String genderSelection;      // 성별
        private List<String> loveChooseMBTI; // 선호mbti 리스트
        private List<String> hateChooseMBTI; // 비선호mbti 리스트
        private String loveMbti;             // 선호mbti 하나로
        private String hateMbti;             // 비선호mbti 하나로
        private String myMbti;               // 가입자 MBTI
    }
}
