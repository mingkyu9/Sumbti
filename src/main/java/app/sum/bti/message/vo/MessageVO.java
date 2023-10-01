package app.sum.bti.message.vo;

import lombok.Data;

@Data
public class MessageVO {

    private String num;
    private int sendNick;
    private String title;
    private String content;
    private String sendTime;
    private String id;

}
