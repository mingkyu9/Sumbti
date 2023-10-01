package app.sum.bti.message.service;

import app.sum.bti.message.mapper.MessageMapper;
import app.sum.bti.message.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    @Autowired
    private final MessageMapper mapper;

    public List<MessageVO> messageList(String userName) throws Exception{
        return mapper.getMessageList(userName);
    }







}