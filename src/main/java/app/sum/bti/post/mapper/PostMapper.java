package app.sum.bti.post.mapper;

import app.sum.bti.post.vo.PostVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface PostMapper {

   public List<PostVO.PostList> getPostList(Map<String,Object> userId) throws SQLException;
}
