package app.sum.bti.couple.vo;


import lombok.Data;

public class CoupleVO {

    @Data
    public static class CoupleList{

        private String userId;
        private String userMbti;
        private String userGender;
        private String loveMbti;
        private String hateMbti;
        private String userNick;

    }

}
