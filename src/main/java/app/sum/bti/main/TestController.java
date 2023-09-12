package app.sum.bti.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView view  = new ModelAndView();
        view.setViewName("home");

        return view;
    }

}
