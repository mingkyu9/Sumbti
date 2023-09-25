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
        private int likeCount; //  현재 리스트 인물을 조아요한 카운트
        private String images;

    }

}
