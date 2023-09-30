package app.sum.bti.couple.controller;

import app.sum.bti.couple.service.CoupleService;
import app.sum.bti.couple.vo.CoupleVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CoupleController {
    
    // coupleZoneList 화면
    private final CoupleService coupleService;
    @GetMapping("/coupleZoneList") // 회원가입 후 세션에서 아이디 가져오기
    public ModelAndView getCoupleList(@RequestParam(value = "userId", defaultValue = "test1") String userId){
        ModelAndView view  = new ModelAndView();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
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
    public ModelAndView getCoupleListPick(@RequestParam(value = "userId", defaultValue = "test1") String userId){
        ModelAndView view  = new ModelAndView();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("userId",userId);
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
    public Map<String, Object> getCoupleListSelect(@RequestBody  Map<String, Object> requestData){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId",requestData.get("userID").toString());
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

    // 상세정보저장
    @PostMapping("/saveDetails")
    @ResponseBody
    public Map<String, Object> saveDetail(@ModelAttribute CoupleVO.CoupleDetailInfo detailRequest){
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
    public Map<String, Object> saveLikeList(@RequestParam(value="userId", defaultValue = "test1") String userId,
                                            @RequestParam(value="userTo", defaultValue = "test10") String userTo){


        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("userId", userId);
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
}
