package app.sum.bti.mypage.mapper;

import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.mypage.vo.LikeVO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface LikeMapper {
    public void saveLike(LikeVO.LikeList likeList) throws SQLException;

}
