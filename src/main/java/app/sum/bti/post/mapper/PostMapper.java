package app.sum.bti.post.mapper;

import app.sum.bti.message.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface PostMapper {

    List<MessageVO> getMessageList(@Param("userName") String userName) throws SQLException;
}
