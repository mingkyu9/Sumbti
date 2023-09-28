package app.sum.bti.message.vo;

import lombok.Data;

@Data
public class MessageVO {

    private String no; // 메세지번호
    private int room; //쪽지함
    private String sendNick; //보내는 사람
    private String recNick; // 메세지 받는 사람
    private String sendTime; // 메세지 보낸 시간
    private String readTime; // 메세지 읽은 시간
    private String content; // 메세지 내용
    private String readChk; // 읽은 사람

    private String otherNick; // 상대방닉네임
    private String profile; // 메세지 프로파일
    private String Nick; // 사용자
    private int unread; // 안읽은 메세지

}
