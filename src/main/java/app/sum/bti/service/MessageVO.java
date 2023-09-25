package app.sum.bti.service;

public class MessageVO {

    private String no;
    private int room;
    private String sendNick;
    private String recNick;
    private String readTime;
    private String content;
    private String readChk;

    private String otherNick;
    private String profile;
    private String Nick;
    private int unread;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getOtherNick() {
        return otherNick;
    }

    public void setOtherNick(String otherNick) {
        this.otherNick = otherNick;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }


    public String getReadChk() {
        return readChk;
    }

    public void setReadChk(String readChk) {
        this.readChk = readChk;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }


    public String getRecNick() {
        return recNick;
    }

    public void setRecNick(String recNick) {
        this.recNick = recNick;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }


    public String getSendNick() {
        return sendNick;
    }

    public void setSendNick(String sendNick) {
        this.sendNick = sendNick;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }
}
