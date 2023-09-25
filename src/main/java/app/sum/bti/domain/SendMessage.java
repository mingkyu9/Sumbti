package app.sum.bti.domain;

import app.sum.bti.service.MessageVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class SendMessage {

    @Autowired
    private SqlSession sqlSession;

    public ArrayList<MessageVO> messageList(MessageVO vo) {

        String nick = vo.getNick();

        ArrayList<MessageVO> list = (ArrayList) sqlSession.selectList("message_list", vo);

        for (MessageVO messageVO : list) {
            messageVO.setNick(nick);

            int unread = sqlSession.selectOne("count_unread", messageVO);

            String profile = sqlSession.selectOne("get_other_profile", messageVO);

            messageVO.setUnread(unread);

            messageVO.setProfile(profile);

            if (nick.equals(messageVO.getSendNick())) {
                messageVO.setOtherNick(messageVO.getRecNick());
            } else {
                messageVO.setOtherNick(messageVO.getSendNick());
            }

        }
        return list;
    }

    public ArrayList<MessageVO> roomContentlist(MessageVO vo) {

        System.out.println("room : " + vo.getRoom());
        System.out.println("recNick : " + vo.getRecNick());
        System.out.println("nick : " + vo.getNick());


        ArrayList<MessageVO> cList = (ArrayList) sqlSession.selectList("room_content_list", vo);

        sqlSession.update("message_read_chk", vo);

        return cList;
    }


    public int messageSendInlist(MessageVO vo) {

        if (vo.getRoom() == 0) {

            int existChat = sqlSession.selectOne("existChat", vo);

            if(existChat == 0) {
                int MaxRoom = sqlSession.selectOne("MaxRoom", vo);
                vo.setRoom(MaxRoom+1);
            }else {
                int room = Integer.parseInt(sqlSession.selectOne("selectRoom",vo));
                vo.setRoom(room);
            }

        }

        int flag = sqlSession.insert("messageSendInlist",vo);
        return flag;

    }

}