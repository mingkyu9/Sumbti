package app.sum.bti.community.controller;

import app.sum.bti.community.service.BoardService;
import app.sum.bti.community.vo.BoardVO;
import app.sum.bti.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comm")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	
	private final BoardService service;

	// 게시글 리스트 화면
	@GetMapping("/list")
	public ModelAndView  getBoardList(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
									  @RequestParam(value="categoryId", defaultValue ="")  String categoryId,
									  HttpSession session) {
		ModelAndView view = new ModelAndView();

		//로그인 유저가 아니면 로그인화면으로
		if(session.getAttribute("loginUserInfo") == null){
			view.setViewName("views/logIn/logInPage");
			return view;
		}

		view.setViewName("views/communityZone/communityZoneList");
		
		BoardVO.Response response = null;
		Map<String, Object> param = new HashMap<>();
		param.put("nowPageNumber",  nowPageNumber);
		param.put("categoryId",  categoryId);
		
		try {
			
			response  = service.getBoardList(param);
			view.addObject("data", response);
			
		}catch (Exception e) {
			e.printStackTrace();
		}

		log.info("========  end  board list ======");

		return view;
	}

	// 글쓰기 화면
	@GetMapping("/add/view")
	public ModelAndView  boardWriteForm(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
										HttpSession session) {

		ModelAndView view = new ModelAndView();

		//로그인 유저가 아니면 로그인화면으로
		if(session.getAttribute("loginUserInfo") == null){
			view.setViewName("views/logIn/logInPage");
			return view;
		}

		view.addObject("nowPageNumber", nowPageNumber);
		view.setViewName("views/communityZone/communityZoneWrite");

		return view;
	}
	
	// 글 등록
	@PostMapping("/add")
	@ResponseBody
	public Map<String, Object> writeBoard(@ModelAttribute BoardVO.Request boardRequest,
										  HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();

		try {

			//로그인된 사용자 정보 가져오기
			LoginVO.LoginUserInfo user = (LoginVO.LoginUserInfo)
					request.getSession().getAttribute("loginUserInfo");


			//로그인 상태일때만 글쓸 수 있게하고 아니면 오류
			if(user != null) {
				boardRequest.setUserId(user.getUserId());
				int result = service.writeBoard(boardRequest);
				if (result > 0) {
					resultMap.put("resultCode", 200);
				} else {
					throw new Exception("insert Error");
				}
			}else {
				throw new Exception("Login Error");
			}

		}catch (Exception e) {
			resultMap.put("resultCode", 500);
			e.printStackTrace();
		}

		return resultMap;

	}
	
	// 글 상세보기 화면
	@GetMapping("/content")
	public ModelAndView  boardDetailView(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
										 @RequestParam("boardNum") int boardNum,
										 HttpServletRequest request) {

		ModelAndView view = new ModelAndView();
		HttpSession session = request.getSession();
		//로그인 유저가 아니면 로그인화면으로
		if(session.getAttribute("loginUserInfo") == null){
			view.setViewName("views/logIn/logInPage");
			return view;
		}

		//로그인된 사용자 정보 가져오기
		LoginVO.LoginUserInfo user = (LoginVO.LoginUserInfo)
				request.getSession().getAttribute("loginUserInfo");

		view.addObject("nowPageNumber", nowPageNumber);
		view.addObject("boardNum", boardNum);
		view.addObject("connectUser",user.getUserId());
		view.setViewName("views/communityZone/communityZoneDetail");

		try {

			//게시글 상세정보 가져오기
			BoardVO.Detail detail = service.getBoardDetail(boardNum);
			view.addObject("detail", detail);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}


	// 게시글 삭제
	@GetMapping("/del/{boardNum}")
	public ModelAndView deleteBoard(@PathVariable int boardNum,
									HttpSession session) {
		ModelAndView view = new ModelAndView();

		//로그인 유저가 아니면 로그인화면으로
		if(session.getAttribute("loginUserInfo") == null){
			view.setViewName("views/logIn/logInPage");
			return view;
		}

		view.setViewName("redirect:/comm/list?nowPageNumber=0");
		try {

			service.deleteBoard(boardNum);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}

	// 게시글 수정 화면
	@GetMapping("/modify/view")
	public ModelAndView  boardModifylView(@RequestParam(value="nowPageNumber", defaultValue ="0")  int nowPageNumber,
										  @RequestParam(value="boardNum") int boardNum,
										  HttpSession session) {

		ModelAndView view = new ModelAndView();
		//로그인 유저가 아니면 로그인화면으로
		if(session.getAttribute("loginUserInfo") == null){
			view.setViewName("views/logIn/logInPage");
			return view;
		}

		view.addObject("nowPageNumber", nowPageNumber);
		view.addObject("boardNum", boardNum);
		view.setViewName("views/communityZone/communityZoneModify");

		try {

			//게시글 상세정보 가져오기
			BoardVO.Detail detail = service.getBoardDetail(boardNum);
			view.addObject("detail", detail);

		}catch (Exception e) {
			e.printStackTrace();
		}

		return view;
	}


   // 게시글 수정하기
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
