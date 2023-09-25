package app.sum.bti.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @GetMapping("/test")
    public ModelAndView test(){
        ModelAndView view  = new ModelAndView();
        view.setViewName("home");

        return view;
    }


    @GetMapping("/api/info")
    @ResponseBody
    public Map<String, Object> getInfo(@RequestParam("name") String name,
                                       @RequestParam("age") int age){

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("age", age);

        return resultMap;

    }
}
