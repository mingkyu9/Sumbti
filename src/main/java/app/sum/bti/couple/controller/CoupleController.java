package app.sum.bti.couple.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoupleController {

    private final CoupleService coupleService;
    @GetMapping("/zone")
    public ModelAndView getCoupleList(){
        ModelAndView view  = new ModelAndView();

        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleList();
            view.addObject("coupleList",coupleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/zonezone");

        return view;
    }

    @GetMapping("/zone2")
    public ModelAndView getCoupleListSelect(@RequestParam(value = "selectedMBTI", required = false) List<String> selectedMBTI){
        ModelAndView view  = new ModelAndView();

        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleListSelect(selectedMBTI);
            view.addObject("coupleList",coupleList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.setViewName("views/zonezone2");

        return view;
    }

}
