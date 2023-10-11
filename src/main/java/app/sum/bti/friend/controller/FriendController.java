package app.sum.bti.friend.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.friend.service.FriendService;
import app.sum.bti.friend.vo.FriendVO;
import app.sum.bti.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    // FriendZoneList 화면
    @GetMapping("/friendZoneList")
    public ModelAndView getFriendList(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",login.getUserId());
        params.put("userHateMbti",login.getHateMbti());
        try {
            List<FriendVO.FriendList> friendList = friendService.getFriendList(params);
            // 반복 돌리지 않고 스트림으로 처리?
            friendList = friendList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();
            view.addObject("friendList",friendList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/friendZone/friendZoneList");

        return view;
    }

    // FriendZoneSelectedList 화면
    @GetMapping("/friendZoneSelectedList")
    public ModelAndView getCoupleListPick(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",login.getUserId());
        try {
            List<FriendVO.FriendList> friendList = friendService.getFriendList(params);

                // 반복 돌리지 않고 스트림으로 처리?
                friendList = friendList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();
            view.addObject("friendList",friendList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        view.setViewName("views/friendZone/friendZoneSelectedList");
        return view;
    }


    // FriendZone 글쓰기 화면 (그냥도 들어올수 있나?)
    @GetMapping("/friendWrite")
    public ModelAndView getWriteDetail(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        view.setViewName("views/friendZone/friendWrite");
        return view;
    }
    
    
    // 필터링된 리스트 반환 처리
    @PostMapping("/selectFriend")
    @ResponseBody
    public Map<String, Object> getFriendSelectedList(@RequestBody  Map<String, Object> requestData, HttpSession session){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 쿼리에 전달할 파라미터 만들기
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",login.getUserId());

        List<FriendVO.FriendList> friendList = new ArrayList<>();
        try {

            List<String> selectedMBTI = (List<String>)requestData.get("selectedMBTI");
            friendList = friendService.getFriendSelectedList(selectedMBTI,param);

            friendList = friendList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();

            resultMap.put("friendList",friendList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }


    /* FriendZone 상세정보 저장 처리 */
    @PostMapping("/saveDetailsFriends")
    @ResponseBody
    public Map<String, Object> saveDetail(@ModelAttribute FriendVO.FriendDetailInfo detailRequest, HttpSession session){
        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        detailRequest.setUserId(login.getUserId());

        try {
                int result = friendService.saveDetail(detailRequest);

                if(result > 0){
                    resultMap.put("resultCode",200);
                }else{
                    throw new Exception("save Error");
                }
                
        } catch (Exception e) {
            resultMap.put("resultCode",500);
            e.printStackTrace();
        }
        return resultMap;
    }


    // 해당유저의 상세 정보 가져오기 처리
    @PostMapping("/showDetailFriendData")
    @ResponseBody
    public Map<String, Object> showDetailInfo(@RequestParam(value="thumbnailId") String thumbnailId){

            Map<String, Object> resultMap = new HashMap<>();
            List<FriendVO.FriendDetailInfo> detailInfo = new ArrayList<>();

        try {
            detailInfo = friendService.showDetailInfo(thumbnailId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("detailInfos",detailInfo);
        return resultMap;
    }

    // 좋아요 목록에 추가
    @PostMapping("/saveFriendLikeList")
    @ResponseBody
    public Map<String, Object> saveLikeList(@RequestParam(value="likeToUser") String userTo,
                                            HttpSession session){
        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 로그인유저 id와 좋아요버튼유저의 id를 파라미터로 전달
        Map<String, Object> param = new HashMap<>();
        param.put("userId", login.getUserId());
        param.put("userTo", userTo);
        
        try {
            
            int result = friendService.checkExistListFriend(param);

            // 0보다 크다면 디비에서 삭제 진행 쿼리 실행
            if(result > 0){
                result = friendService.deleteLikeListFriend(param);
                resultMap.put("resultCode",201);
            }else{
                // 0 이면 디비에 저장 쿼리 실행
                result = friendService.addLikeListFriend(param);
                resultMap.put("resultCode",200);

            }

        } catch (Exception e) {
            resultMap.put("resultCode",500);
            e.printStackTrace();
        }
        return resultMap;
    }

}
