package app.sum.bti.post.service;

import app.sum.bti.message.mapper.MessageMapper;
import app.sum.bti.message.vo.MessageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final MessageMapper mapper;

    public List<MessageVO> messageList(String userName) throws Exception{
        return mapper.getMessageList(userName);
    }
}
