package app.sum.bti.post.service;

import app.sum.bti.post.mapper.PostMapper;
import app.sum.bti.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper mapper;

    public List<PostVO.PostList> getPostList(Map<String,Object> userId) throws Exception{
        return mapper.getPostList(userId);
    }



    public PostVO.PostDetail getPostDetail(Map<String,Object> postNum) throws  Exception{
        return  mapper.getPostDetail(postNum);
    }

    public void postSend(PostVO.SendPost param) throws SQLException{
           mapper.postSend(param);
    }


    public void delPost(Map<String, Object> param) throws SQLException {
          mapper.delPost(param);
    }


    public List<PostVO.PostList> sentPostList(Map<String, Object> userId) throws Exception {
        return mapper.sentPostList(userId);
    }




    // 커플 좋아요 유저 리스트 가져오기
    public List<PostVO.LikeUserList> coList(Map<String,Object> userId) throws SQLException{
         return mapper.coList(userId);
    }

    // 프렌드 좋아요 유저 리스트 가져오기
    public List<PostVO.LikeUserList> frList(Map<String,Object> userId) throws SQLException {
        return mapper.frList(userId);
    }
}
