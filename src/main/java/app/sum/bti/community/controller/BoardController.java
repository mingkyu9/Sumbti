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
@RequestMapping("/comm")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;

	@GetMapping("/list")
	public ModelAndView  getBoardList(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber) {
		ModelAndView view = new ModelAndView();
		view.setViewName("views/communityZone/communityZoneList");
		
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

	//
	@GetMapping("/add/view")
	public ModelAndView  boardWriteForm(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber) {

		ModelAndView view = new ModelAndView();

		view.addObject("nowPageNumber", nowPageNumber);
		view.setViewName("views/communityZone/communityZoneWrite");

		return view;
	}
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> writeBoard(@ModelAttribute BoardVO.Request boardRequest) {
		Map<String, Object> resultMap = new HashMap<>();

		try {
			int result = service.writeBoard(boardRequest);

			if(result > 0) {
				resultMap.put("resultCode", 200);
			}else {
				throw new  Exception("insert Error");
			}

		}catch (Exception e) {
			resultMap.put("resultCode", 500);
			e.printStackTrace();
		}

		return resultMap;

	}

	//누나가 단 만들어주면 수정
	@GetMapping("/content")
	public ModelAndView  boardDetailView(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
										 @RequestParam("boardNum") int boardNum) {

		ModelAndView view = new ModelAndView();

		view.addObject("nowPageNumber", nowPageNumber);
		view.addObject("boardNum", boardNum);
		view.setViewName("views/communityZone/communityZoneContent");

		try {

			//게시글 상세정보 가져오기 
			BoardVO.Detail detail = service.getBoardDetail(boardNum);
			view.addObject("detail", detail);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}


	@GetMapping("/del/{boardNum}")
	public ModelAndView deleteBoard(@PathVariable("boardNum") int boardNum) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/comm/list?nowPageNumber=0");
		try {

			service.deleteBoard(boardNum);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	//수정하기
	@GetMapping("/modify/view")
	public ModelAndView  boardModifylView(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
										  @RequestParam("boardNum") int boardNum) {

		ModelAndView view = new ModelAndView();

		view.addObject("nowPageNumber", nowPageNumber);
		view.addObject("boardNum", boardNum);
		view.setViewName("views/board/boardUpdateNote");

		try {

			//게시글 상세정보 가져오기 
			BoardVO.Detail detail = service.getBoardDetail(boardNum);
			view.addObject("detail", detail);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}



	@PostMapping("/modify")
	@ResponseBody
	public Map<String, Object>  updateBoard(@ModelAttribute BoardVO.UpdateRequest  boardUpdate) {
		Map<String, Object> resultMap = new HashMap<>();

		try {

			int result = service.updateBoard(boardUpdate);

			if(result > 0) {
				resultMap.put("resultCode", 200);
			}else {
				throw new  Exception("insert Error");
			}

		}catch (Exception e) {
			resultMap.put("resultCode", 500);
			e.printStackTrace();
		}

		return resultMap;

	}
}
