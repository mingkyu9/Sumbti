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
    
    // coupleZoneList 화면
    private final CoupleService coupleService;
    @GetMapping("/coupleZoneList")
    public ModelAndView getCoupleList(){
        ModelAndView view  = new ModelAndView();

        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleList();
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
    public ModelAndView getCoupleListPick(){
        ModelAndView view  = new ModelAndView();

        try {
            List<CoupleVO.CoupleList> coupleList = coupleService.getCoupleListPick();

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

    // 상세정보저장
    @PostMapping("/saveDetails")
    @ResponseBody
    public Map<String, Object> saveDetail(@ModelAttribute CoupleVO.DetailsInfo detailRequest){
        Map<String, Object> resultMap = new HashMap<>();

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


    @PostMapping("/showDetailData")
    @ResponseBody
    public Map<String, Object> showDetailInfo(@RequestParam(value="thumbnailId") String thumbnailId){

            Map<String, Object> resultMap = new HashMap<>();
            List<CoupleVO.DetailsInfo> detailInfo = new ArrayList<>();

        try {
            detailInfo = coupleService.showDetailInfo(thumbnailId);

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("detailInfos",detailInfo);
        return resultMap;
    }
}
