package app.sum.bti.message.controller;

import app.sum.bti.message.service.MessageService;
import app.sum.bti.message.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    //메세지 내용 가져오기
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


    //메세지 내용 보내오기



    //메세지 전송하기




}
