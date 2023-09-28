package app.sum.bti.message.controller;

import app.sum.bti.message.service.MessageService;
import app.sum.bti.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    //메세지 목록
    @RequestMapping(value = "/message/list")
    public ModelAndView messageList(HttpServletRequest request, HttpSession session) {
        ModelAndView view = new ModelAndView();
        view.setViewName("message/message_list");
        return view;

    }

    @GetMapping(value = "/message/list/data")
    @ResponseBody
    public Map<String, Object> getMessageListData() {
        Map<String, Object> resultMap = new HashMap<>();
        String userName = "aa";
        //메세지 리스트
        List<MessageVO> list =  new ArrayList<>();
        try {
            list =  messageService.messageList(userName);
        }catch (Exception e){
            e.printStackTrace();
        }
        resultMap.put("list", list);
        return  resultMap;

    }

    @RequestMapping(value = "/message_content_list")

    public String messageContentList(HttpServletRequest request, HttpSession session) {

        int room = Integer.parseInt(request.getParameter("room"));

        MessageVO vo = new MessageVO();
        vo.setRoom(room);
        vo.setNick((String) session.getAttribute("nick"));

        //메세지 내용을 가져온다.
        ArrayList<MessageVO> cList = messageService.roomContentlist(vo);

        request.setAttribute("cList", cList);

        return "message/messageContentList";
    }


    //메세지 보내기
    @ResponseBody
    @RequestMapping(value = "/message_send_inlist")
    public int messageSendInList(@RequestParam int room, @RequestParam String other_nick,
                                   @RequestParam String content, HttpSession session) {

        MessageVO vo  = new MessageVO();
        vo.setRoom(room);
        vo.setSendNick((String) session.getAttribute("nick"));
        vo.setRecNick(other_nick);
        vo.setContent(content);

        int flag = messageService.messageSendInlist(vo);

        return flag;

    }



}
