package app.sum.bti.mypage.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.mypage.service.LikeService;
import app.sum.bti.mypage.vo.LikeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like/list")
    @ResponseBody
    public String saveLike(@RequestBody LikeVO.LikeList likeList) {

        try {

             likeService.saveLike(likeList);
            return "좋아요 목록에 추가되었습니다.";

        } catch (Exception e) {
            e.printStackTrace();
            return "좋아요 목록에 추가하지 못했습니다.";
        }

    }
}




