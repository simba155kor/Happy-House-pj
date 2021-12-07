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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.model.Reply;
import com.happyhouse.model.service.reply.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/reply")
@Api("댓글 컨트롤러 API V1")
public class ReplyRestController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReplyService service;
	
	@ApiOperation(value = "댓글 작성 ", notes = "새로운 댓글 입력한다 ", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeReply(@RequestBody @ApiParam(value = "댓글 정보 ",required = true)Reply reply) throws Exception {
		logger.info("writeReply .... 호출 ");
		System.out.println("--------------------" + reply.getBoardNo());
		System.out.println("--------------------" + reply.getReplyContent());
		System.out.println("--------------------" + reply.getMemberId());
		if(service.writeReply(reply)) {
			
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 리스트 ", notes = "모든 댓글정보를 반환한다 ",response = List.class)
	@GetMapping("/{boardNo}")
	public ResponseEntity<List<Reply>> listReply(@PathVariable("boardNo") int boardNo) throws Exception {
		logger.info("listReply --- 호출 ");
		return new ResponseEntity<List<Reply>>(service.listReply(boardNo), HttpStatus.OK);
	}
	
	@ApiOperation(value = "댓글 수정 ", notes = "댓글을 수정한다 ",response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyReply(@RequestBody Reply reply) throws Exception {
		logger.info("modify ---- 호춣 ");
		if(service.modifyReply(reply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "댓글 삭제  ", notes = "댓글을 삭제한다 ",response = String.class)
	@DeleteMapping("/{replyNo}")
	public ResponseEntity<String> deleteReply(@PathVariable("replyNo") int replyNo) throws Exception {
		logger.info("reply delete .......");
		if(service.deleteReply(replyNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
}
