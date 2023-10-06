package app.sum.bti.login.controller;

import app.sum.bti.login.service.LoginService;
import app.sum.bti.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;
    
    // 로그인 화면
    @GetMapping("/loginGo")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/logIn/logInPage");

        return view;
    }

    
    // form에 입력된 정보와 DB에서 로그인 정보 비교 후 세션에 사용자 정보 저장
    @PostMapping("/checkLogin")
    @ResponseBody
    public Map<String, Object> checkUserInfo(@ModelAttribute LoginVO.LoginInfo loginInfo, HttpServletRequest request){
        Map<String, Object> resultMap = new HashMap<String, Object>();

        try {
            int result = loginService.checkUserInfo(loginInfo);
            LoginVO.LoginUserInfo vo = loginService.getLoginUserInfo(loginInfo);
            //결과가 0보다 크다면 사용자 인증 완료
            if(result > 0){
                resultMap.put("resultCode",200);
                //세션에 사용자 정보 저장
                HttpSession session = request.getSession();
                session.setAttribute("loginUserInfo",vo);
            }else{
                //결과가 0보다 크지 않다면 사용자 입력정보 잘못 되었음
                resultMap.put("resultCode",500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }
}
