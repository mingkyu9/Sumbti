package app.sum.bti.couple.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class CoupleVO {


    @Data
    public static class CoupleList{

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
    public static class CoupleDetailInfo{

        private String userId;
        private String detailIntro;
        private String detailHobby;
        private String detailFood;
        private String detailPat;

    }


}
