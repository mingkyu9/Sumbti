package app.sum.bti.mainView.vo;

import lombok.Data;

public class mainViewVO {

    @Data
    public static class LankList{

        private String likeRank;
        private String userMbti;
        private String userNick;
        private String likeCount;
    }

}
