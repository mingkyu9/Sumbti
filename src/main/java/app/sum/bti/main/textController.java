package app.sum.bti.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class textController {

    @GetMapping("/couple/recommand")
    public ModelAndView coupleList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/coupleZone/coupleZoneList");

        return view;

    }

    @GetMapping("/couple/select")
    public ModelAndView coupleSelectedList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/coupleZone/coupleZoneSelectedList");

        return view;

    }

    @GetMapping("/couple/write")
    public ModelAndView coupleWrite() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/coupleZone/coupleWrite");

        return view;

    }

    @GetMapping("/friend/recommand")
    public ModelAndView friendList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/friendZone/friendZoneList");

        return view;

    }

    @GetMapping("/friend/select")
    public ModelAndView friendSelectedList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/friendZone/friendZoneSelectedList");

        return view;

    }

    @GetMapping("/friend/write")
    public ModelAndView friendWrite() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/friendZone/friendWrite");

        return view;

    }

    @GetMapping("/comm/write")
    public ModelAndView communityWrite() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/communityZone/communityZoneWrite");

        return view;

    }

    @GetMapping("/comm/modify")
    public ModelAndView communityModify() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/communityZone/communityZoneModify");

        return view;

    }

    @GetMapping("/comm/list")
    public ModelAndView communityList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/communityZone/communityZoneList");

        return view;

    }

    @GetMapping("/comm/content")
    public ModelAndView communityContent() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/communityZone/communityZoneContent");

        return view;

    }

    @GetMapping("/post/write")
    public ModelAndView postWrite() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/postBox/postBoxWrite");

        return view;

    }

    @GetMapping("/post/detail")
    public ModelAndView postDetail() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/postBox/postBoxDetail");

        return view;

    }

    @GetMapping("/post/inbox/list")
    public ModelAndView postInboxList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/postBox/postInboxList");

        return view;

    }

    @GetMapping("/post/sent/list")
    public ModelAndView postSentList() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/postBox/postSentList");

        return view;

    }

    @GetMapping("/main")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/mainPage/mainPage");

        return view;

    }

    @GetMapping("/signin/first")
    public ModelAndView signInFirst() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/signIn/signIn-1");

        return view;

    }

    @GetMapping("/signin/second")
    public ModelAndView signInSecond() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/signIn/signIn-2");

        return view;

    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/logIn/logInPage");

        return view;

    }


    @GetMapping
    @ResponseBody
    public Map<String, Object> getInfo(@RequestParam("name") String name,
                                       @RequestParam("age") int age) {

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("name", name);
        resultMap.put("age", age);

        return resultMap;
    }
}
