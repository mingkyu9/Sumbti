package app.sum.bti.contoroller;

import app.sum.bti.domain.SendMessage;
import app.sum.bti.service.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
public class MessageController {

    @Autowired
    private SendMessage sendMessage;

    @RequestMapping(value = "/message_list")
    public String message_list(HttpServletRequest request, HttpSession session) {

        String nick = (String) session.getAttribute("nick");

        MessageVO vo = new MessageVO();
        vo.setNick(nick);

        ArrayList<MessageVO> list = sendMessage.messageList(vo);

        request.setAttribute("list", list);

        return "message/message_list";

    }

    @RequestMapping(value = "/message_ajax_list")
    public String message_ajax_list(HttpServletRequest request, HttpSession session) {

        String nick = (String) session.getAttribute("nick");

        MessageVO vo = new MessageVO();
        vo.setNick(nick);

        ArrayList<MessageVO> list = sendMessage.messageList(vo);

        request.setAttribute("list", list);

        return  "message/message_ajax_list";

    }

    @RequestMapping(value = "/message_content_list")
    public String message_content_list(HttpServletRequest request, HttpSession session) {

        int room = Integer.parseInt(request.getParameter("room"));

        MessageVO vo = new MessageVO();
        vo.setRoom(room);
        vo.setNick((String) session.getAttribute("nick"));

        ArrayList<MessageVO> clist = sendMessage.roomContentlist(vo);

        request.setAttribute("clist", clist);

        return "message/message_content_list";
    }


    @ResponseBody
    @RequestMapping(value = "/message_send_inlist")
    public int message_send_inlist(@RequestParam int room, @RequestParam String other_nick,
                                   @RequestParam String content, HttpSession session) {

        MessageVO vo  = new MessageVO();
        vo.setRoom(room);
        vo.setSendNick((String) session.getAttribute("nick"));
        vo.setRecNick(other_nick);
        vo.setContent(content);

        int flag = sendMessage.messageSendInlist(vo);

        return flag;

    }



}
