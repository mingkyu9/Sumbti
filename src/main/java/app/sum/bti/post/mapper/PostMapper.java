package app.sum.bti.post.mapper;

import app.sum.bti.post.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

   //메세지 리스트 가져오기
   public List<PostVO.PostList> getPostList(Map<String,Object> userId) throws SQLException;
   
   //디테일 정보 가져오기
   public PostVO.PostDetail getPostDetail(Map<String, Object> postNum) throws  SQLException;
}
