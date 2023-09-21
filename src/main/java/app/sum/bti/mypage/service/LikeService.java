package app.sum.bti.mypage.service;

import app.sum.bti.couple.mapper.CoupleMapper;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.mypage.mapper.LikeMapper;
import app.sum.bti.mypage.vo.LikeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeMapper mapper;

    public void saveLike(LikeVO.LikeList likeList) throws SQLException {
        mapper.saveLike(likeList);
    }
}
