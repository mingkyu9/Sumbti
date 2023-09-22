package app.sum.bti.couple.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

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
    public ModelAndView getCoupleListPick(){
        ModelAndView view  = new ModelAndView();

        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleListPick();

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

    @PostMapping("/select")
    @ResponseBody
    public Map<String, Object> getCoupleListSelect(@RequestBody  Map<String, Object> requestData){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<CoupleVO.CoupleList> coupleList = new ArrayList<CoupleVO.CoupleList>();

        try {

            List<String> selectedMBTI = (List<String>)requestData.get("selectedMBTI");
            coupleList = coupleService.getCoupleListSelect(selectedMBTI);

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

}
