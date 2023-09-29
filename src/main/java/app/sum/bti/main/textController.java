package app.sum.bti.main;
import app.sum.bti.community.service.BoardService;
import app.sum.bti.community.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class textController {

    private final BoardService boardService;

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
    public ModelAndView communityWrite(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber) {
        ModelAndView view = new ModelAndView();

        view.addObject("nowPageNumber", nowPageNumber);
        view.setViewName("views/communityZone/communityZoneWrite");

        return view;

    }

    @GetMapping("/comm/list")
    public ModelAndView communityList(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
                                      @RequestParam(value="categoryId",required = false, defaultValue ="")  String categoryId) {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/communityZone/communityZoneList");

        BoardVO.Response response = null;
        Map<String, Object> param = new HashMap<>();
        param.put("nowPageNumber",  nowPageNumber);
        param.put("categoryId",  categoryId);

        try {

            response  = boardService.getBoardList(param);
            view.addObject("data", response);

        }catch (Exception e) {
            e.printStackTrace();
        }

        return view;

    }

    @GetMapping("/comm/content")
    public ModelAndView communityContent(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
                                         @RequestParam("boardNum") int boardNum) {
        ModelAndView view = new ModelAndView();
        view.addObject("nowPageNumber", nowPageNumber);
        view.addObject("boardNum", boardNum);
        view.setViewName("views/communityZone/communityZoneContent");


        try {
            //게시글 상세정보 가져오기
            BoardVO.Detail detail = boardService.getBoardDetail(boardNum);
            view.addObject("detail", detail);

        }catch (Exception e) {
            e.printStackTrace();
        }

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
