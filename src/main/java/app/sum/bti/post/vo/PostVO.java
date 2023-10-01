package app.sum.bti.post.vo;

import lombok.Data;


public class PostVO {

    @Data
    public static class PostList {
        private String postNum;
        private String postTitle;
        private String postTime;
        private String postSender;
        private String postReceiver;
        private String postZone;
        private String userNick;
        private String userGender;
        private String userMbti;
        private String images;
    }

}
