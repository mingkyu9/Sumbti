package app.sum.bti.sign.controller;

import app.sum.bti.sign.service.SignService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @PostMapping("/checkIdVal")
    @ResponseBody
    public Map<String, Object> checkSameId(@RequestBody Map<String, String> requestBody) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 쿼리에 전달할 파라미터 만들기
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("checkId",requestBody.get("checkId"));

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


}
