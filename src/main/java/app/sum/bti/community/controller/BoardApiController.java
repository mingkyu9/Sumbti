package app.sum.bti.community.controller;

import app.sum.bti.community.service.BoardService;
import app.sum.bti.community.vo.BoardFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardApiController {
	
	private final BoardService service;
	private 	String filePath = "D:\\webclass_sdy\\download\\";

	
	@GetMapping("/mapper/filedown")
	public ResponseEntity<UrlResource>  downBoardFile(@RequestParam("fileId") int fileId) {
		String originFileName = null;
	    String storedFileName = null;
	    //파일다운로드 시 필요 
	    HttpHeaders header = new HttpHeaders();
	    UrlResource  res = null;
		
	    return new ResponseEntity<UrlResource>(res, header, HttpStatus.OK);
	}

}
