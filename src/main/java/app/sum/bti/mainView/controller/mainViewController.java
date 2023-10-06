package app.sum.bti.mainView.controller;

import app.sum.bti.mainView.service.mainViewService;
import app.sum.bti.mainView.vo.mainViewVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class mainViewController {

    private final mainViewService service;
    @GetMapping("/mainView")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView();

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
