package com.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.Board;
import com.happyhouse.model.dto.BoardParameterDto;
import com.happyhouse.model.service.board.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RequestMapping("/board")
@Api("게시판 컨트롤러 V1")
public class BoardRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	private BoardService service;
	
	@ApiOperation(value="게시판 글작성 ", notes = "새로운 게시글 입력한다 성공시 success 반환 ", response = String.class)
//	@ApiImplicitParams(
//			{	@ApiImplicitParam(name = "boardCnt", value = "조회수", required = true, example="0"),
//				@ApiImplicitParam(name = "boardNo", value = "번호입력",  example="string")
//			})
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody @ApiParam(value="게시글 정보", required = true)Board board) throws Exception {
		logger.info("writeBoard - 호출 ");
		if(service.writeBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	@ApiOperation(value="게시판 글목록 ", notes = "게시글 리스트를 불러온다 성공시 success 반환 ", response = List.class)
//	@ApiImplicitParams({
//		@ApiImplicitParam(name = "boardCnt",  example = "10"),
//		@ApiImplicitParam(name = "boardNo",  example = "string"),
//		
//	})
	@GetMapping
	public ResponseEntity<List<Board>> listBoard(@ApiParam(value="게시글을 얻기위한 부가정보 ", required = true)BoardParameterDto parameter) throws Exception {
		logger.info("listBoard - 호출");
		return new ResponseEntity<List<Board>>(service.listBoard(parameter), HttpStatus.OK);
	}
//	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<Board> getBoard(@PathVariable("boardNo") @ApiParam(value = "얻어올 글의 글번호.", required = true) int boardNo) throws Exception {
		logger.info("getArticle - 호출 : " + boardNo);
//		service.updateHit(articleno);
		service.plusCnt(boardNo);
		System.out.println(service.getBoard(boardNo));
		return new ResponseEntity<Board>(service.getBoard(boardNo), HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "게시판 수정 하기 ", notes = "글번호에 해당하는 게시글을 수정한다 ", response = String.class) 
	@PutMapping
	public ResponseEntity<String> modifyBoard(@RequestBody @ApiParam(value = "수정할 글정보 ") Board board) throws Exception {
		logger.info("modifyBoard ------ 호출 ");
		if(service.modifyBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable("boardNo") @ApiParam(value = "살제할 글의 글번호.")int boardNo) throws Exception {
		logger.info("deleteBoard ----- ");
		if(service.deleteBoard(boardNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
