package app.sum.bti.post.controller;

import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.post.service.PostService;
import app.sum.bti.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //메세지 내용 가져오기
    @GetMapping("/postBoxList")
    public ModelAndView getPostList() {
        ModelAndView view  = new ModelAndView();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("userId","tes10");
            try {
                List<PostVO.PostList> postList = postService.getPostList(param);
                // 반복 돌리지 않고 스트림으로 처리?
                postList = postList.stream().map( obj-> {
                    String mbti = obj.getUserMbti();
                    String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                    obj.setImages(img);
                    return obj;
                }).toList();
                view.addObject("postList",postList);
            } catch (Exception e) {
                e.printStackTrace();
            }
            view.setViewName("views/postBox/postInboxList");
            return view;
        }





}