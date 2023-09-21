package app.sum.bti.community.controller;

import app.sum.bti.community.service.BoardService;
import app.sum.bti.community.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/mapper")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;

	@GetMapping("/list")
	public ModelAndView  getBoardList(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("views/board/boardList");
		
		BoardVO.Response response = null;
		Map<String, Object> param = new HashMap<>();
		param.put("nowPageNumber",  nowPageNumber);
		
		try {
			
			response  = service.getBoardList(param);
			view.addObject("data", response);
			
		}catch (Exception e) {
			 e.printStackTrace();
		}
		
	    log.info("========  end  board list ======");
		
		return view;
	}
	
	@GetMapping("/add/view")
	public ModelAndView  boardWriteForm(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber) {
	
		ModelAndView view = new ModelAndView();
		
		view.addObject("nowPageNumber", nowPageNumber);
		view.setViewName("views/board/boardWriteNote");
			
		return view;
	}
	

	
}
