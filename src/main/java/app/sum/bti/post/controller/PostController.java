package app.sum.bti.post.controller;

import app.sum.bti.post.service.PostService;
import app.sum.bti.post.vo.PostVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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


    // 디테일 화면
    @GetMapping("/postDetail")
    public ModelAndView DetailPostBox(@RequestParam (value = "postNum") int postNum) {
        ModelAndView view  = new ModelAndView();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("postNum",postNum);
        try {
            PostVO.PostDetail postDetail = postService.getPostDetail(param);

            view.addObject("postDetail",postDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("views/postBox/postBoxDetail");
        return view;
    }

    @GetMapping("/sendPostList")
    public ModelAndView sendPostView() {
        ModelAndView view  = new ModelAndView();
        view.setViewName("views/postBox/postSentList");
        return view;
    }


    // 쪽지보내기 화면
    @GetMapping("/sendPostView")
    public ModelAndView sendPostList(@RequestParam(value = "nick", defaultValue = "") String nick) {
        ModelAndView view  = new ModelAndView();
        view.addObject("nick", nick);
        view.setViewName("views/postBox/postBoxWrite");
        return view;
    }

//    @PostMapping("/sendPost")
//    @ResponseBody
//    public Map<String, Object> PostSend(@ModelAttribute PostVO.SendPost sendRequest, HttpSession session){
//        Map<String, Object> resultMap = new HashMap<>();
//        // 세션에 저장되어있는 정보 가져오기
//        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
//        sendRequest.setPostSender(login.getUserId());
//
//        try {
//            postService.postSend(sendRequest);
//            resultMap.put("resultCode",200);
//        }catch (Exception e){
//            resultMap.put("resultCode",500);
//            e.printStackTrace();
//        }
//        return resultMap;
//    }








}
