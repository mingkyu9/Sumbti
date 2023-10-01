package app.sum.bti.post.service;

import app.sum.bti.post.mapper.PostMapper;
import app.sum.bti.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper mapper;

    public List<PostVO.PostList> getPostList(Map<String,Object> userId) throws Exception{
        return mapper.getPostList(userId);
    }
}
