package app.sum.bti.mainView.controller;

import app.sum.bti.mainView.service.mainViewService;
import app.sum.bti.mainView.vo.mainViewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class mainViewController {

    private final mainViewService service;
    @GetMapping("/mainView")
    public ModelAndView main(HttpSession session) {
        ModelAndView view = new ModelAndView();

        //로그인 유저가 아니면 로그인화면으로
        if(session.getAttribute("loginUserInfo") == null){
            view.setViewName("views/logIn/logInPage");
            return view;
        }

        try {
            List<mainViewVO.LankList> coLank = service.coLank();
            view.addObject("coLank", coLank);
            List<mainViewVO.LankList> frLank = service.frLank();
            view.addObject("frLank", frLank);
        }catch(Exception e){
            e.printStackTrace();
        }

        view.setViewName("views/mainPage/mainPage");

        return view;
    }
}
