package app.sum.bti.mainView.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@Slf4j
public class mainViewController {

    @GetMapping("/mainView")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/mainPage/mainPage");

        return view;

    }
}
