package app.sum.bti.sign.controller;

import app.sum.bti.couple.vo.CoupleVO;
import app.sum.bti.login.vo.LoginVO;
import app.sum.bti.sign.service.SignService;
import app.sum.bti.sign.vo.SignVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SignController {

    private final SignService signService;


    @GetMapping("/signinGo")
    public ModelAndView signInFirst() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/signIn/signIn-1");

        return view;
    }

    @GetMapping("/signinGo2")
    public ModelAndView signInSecond() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/signIn/signIn-2");

        return view;
    }

    @GetMapping("/selectMbtiGo")
    public ModelAndView signInThird() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/selectMbti/selectPage2");

        return view;
    }

    // 아이디 중복체크
    @PostMapping("/checkIdVal")
    @ResponseBody
    public Map<String, Object> checkSameId(@RequestParam(value = "checkId") String checkId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 쿼리에 전달할 파라미터 만들기
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("checkId",checkId);

        try {
            int result = signService.checkEqualId(param);

            if(result > 0){
                // 중복 있음
                resultMap.put("resultCode",201);
            }else{
                // 중복없음
                resultMap.put("resultCode",200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    // 닉네임 중복체크
    @PostMapping("/checkNickVal")
    @ResponseBody
    public Map<String, Object> checkSameNick(@RequestParam(value = "checkNick") String checkNick) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 쿼리에 전달할 파라미터 만들기
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("checkNick",checkNick);

        try {
            int result = signService.checkEqualNick(param);

            if(result > 0){
                // 중복 있음
                resultMap.put("resultCode",201);
            }else{
                // 중복없음
                resultMap.put("resultCode",200);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }


   // 첫번째
    @PostMapping("/nextStep")
    @ResponseBody
    public Map<String, Object> nextSign(@ModelAttribute SignVO.SignInfo InfoRequest, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 첫번쨰 페이지 정보 저장하기
        HttpSession session = request.getSession();
        session.setAttribute("signInfo",InfoRequest);

        if(session.getAttribute("signInfo") != null){
            resultMap.put("resultCode",200);
        }else{
            resultMap.put("resultCode",500);
        }
        return resultMap;
    }

    // 두번째
    @PostMapping("/nextStep2")
    @ResponseBody
    public Map<String, Object> nextSign2(@ModelAttribute SignVO.SignInfo InfoRequest, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        // 세션에 두번째 페이지 정보 저장하기
        SignVO.SignInfo signInfo =  (SignVO.SignInfo)session.getAttribute("signInfo");
        signInfo.setNameInput(InfoRequest.getNameInput());
        signInfo.setHateChooseMBTI(InfoRequest.getHateChooseMBTI());
        signInfo.setLoveChooseMBTI(InfoRequest.getLoveChooseMBTI());
        signInfo.setGenderSelection(InfoRequest.getGenderSelection());
        session.setAttribute("signInfo",signInfo);

        if(session.getAttribute("signInfo") != null){
            resultMap.put("resultCode",200);
        }else{
            resultMap.put("resultCode",500);
        }
        return resultMap;
    }


    @PostMapping("/singComplete")
    @ResponseBody
    public Map<String, Object> nextSign3(@RequestParam(value="mbtiCheck") String userNick, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();

        // 세션에 세번째 페이지 정보 저장하기
        SignVO.SignInfo signInfo =  (SignVO.SignInfo)session.getAttribute("signInfo");
        signInfo.setMyMbti(userNick);


        try {
           int result =  signService.signCheck(signInfo);
           // result > 0 이면 회원가입 완료
           if(result > 0){
               resultMap.put("resultCode",200);
           }else{
               resultMap.put("resultCode",500);
           }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return resultMap;
    }
}
