package app.sum.bti.couple.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoupleController {
    
    // coupleZoneList 화면
    private final CoupleService coupleService;

    @GetMapping("/coupleZoneList")
    public ModelAndView getCoupleList(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 쿼리에 전달할 파라미터 만들기
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",login.getUserId());
        params.put("userHateMbti",login.getHateMbti());
        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleList(params);
            // 반복 돌리지 않고 스트림으로 처리?
            coupleList = coupleList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();
            view.addObject("coupleList",coupleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/coupleZone/coupleZoneList");

        return view;
    }

    // coupleZoneSelectedList 화면
    @GetMapping("/coupleZoneSelectedList")
    public ModelAndView getCoupleListPick(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 쿼리에 전달할 파라미터 만들기
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",login.getUserId());
        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleList(params);

                // 반복 돌리지 않고 스트림으로 처리?
                coupleList = coupleList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();
            view.addObject("coupleList",coupleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/coupleZone/coupleZoneSelectedList");

        return view;
    }

    // 필터링된 리스트 뿌리기 ajax 처리
    @PostMapping("/select")
    @ResponseBody
    public Map<String, Object> getCoupleListSelect(@RequestBody  Map<String, Object> requestData, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 쿼리에 전달할 파라미터 만들기
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",login.getUserId());

        List<CoupleVO.CoupleList> coupleList = new ArrayList<CoupleVO.CoupleList>();
        try {

            List<String> selectedMBTI = (List<String>)requestData.get("selectedMBTI");
            coupleList = coupleService.getCoupleListSelect(selectedMBTI,param);

            coupleList = coupleList.stream().map( obj-> {
                String mbti = obj.getUserMbti();
                String img = "/img/profileIcon/" + (obj.getUserGender().equals("남자") ? "m-"+mbti+".png" :"f-"+mbti+".png");
                obj.setImages(img);
                return obj;
            }).toList();

            resultMap.put("coupleList",coupleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }




    @PostMapping("/showDetailData")
    @ResponseBody
    public Map<String, Object> showDetailInfo(@RequestParam(value="thumbnailId") String thumbnailId){

            Map<String, Object> resultMap = new HashMap<>();
            List<CoupleVO.CoupleDetailInfo> detailInfo = new ArrayList<>();

        try {
            detailInfo = coupleService.showDetailInfo(thumbnailId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("detailInfos",detailInfo);
        return resultMap;
    }

    // 좋아요 목록에 추가
    @PostMapping("/saveCoupleLikeList")
    @ResponseBody
    public Map<String, Object> saveLikeList(@RequestParam(value="likeToUser") String userTo,
                                            HttpSession session) {

        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");

        // 로그인유저 id와 좋아요버튼유저의 id를 파라미터로 전달
        Map<String, Object> param = new HashMap<>();
        param.put("userId", login.getUserId());
        param.put("userTo", userTo);

        try {
            int result = coupleService.checkExistList(param);

            // 0보다 크다면 디비에서 삭제 진행 쿼리 실행
            if(result > 0){
                result = coupleService.deleteLikeList(param);
                resultMap.put("resultCode",201);
            }else{
                // 0 이면 디비에 저장 쿼리 실행
                result = coupleService.addLikeList(param);
                resultMap.put("resultCode",200);
            }

        } catch (Exception e) {
            resultMap.put("resultCode",500);
            e.printStackTrace();
        }
        return resultMap;
    }


    //상세정보 입력화면
    @GetMapping("/DetailInformation")
    public ModelAndView saveDetailInfo(HttpSession session){
        ModelAndView view  = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        view.setViewName("views/coupleZone/coupleWrite");

        return view;
    }

    // 상세정보저장
    @PostMapping("/saveDetails")
    @ResponseBody
    public Map<String, Object> saveDetail(@ModelAttribute CoupleVO.CoupleDetailInfo detailRequest, HttpSession session){
        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 저장되어있는 정보 가져오기
        LoginVO.LoginUserInfo login = (LoginVO.LoginUserInfo)session.getAttribute("loginUserInfo");
        // 파라미터로 전달할 객체의 userId 속성에 값 추가.
        detailRequest.setUserId(login.getUserId());

        try {
            int result = coupleService.saveDetail(detailRequest);

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
}
