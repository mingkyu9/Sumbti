package app.sum.bti.login.vo;

import lombok.Data;

public class LoginVO {

    @Data
    public static class LoginInfo{
        private String userId;  // 유저 아이디
        private String userPw;  // 유저 비밀번호
    }

    @Data
    public static class LoginUserInfo{
        private String userId;
        private String userName;
        private String userNick;
        private String userGender;
        private String userMbti;
        private String hateMbti;
    }

}
