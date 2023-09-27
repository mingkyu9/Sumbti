package app.sum.bti.friend.vo;


import lombok.Data;

public class FriendVO {



    @Data
    public static class FriendList{

        private String userId;
        private String userMbti;
        private String userGender;
        private String userNick;
        private String loveMbti;
        private String hateMbti;
        private String detailIntro;
        private int likeCount; //  현재 리스트 인물을 좋아요한 카운트
        private String images;

    }
    @Data
    public static class FriendDetailInfo{

        private String userId;
        private String detailIntro;
        private String detailHobby;
        private String detailFood;
        private String detailPat;

    }
}
