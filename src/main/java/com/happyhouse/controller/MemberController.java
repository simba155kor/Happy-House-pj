package com.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberFindDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginDto;
import com.happyhouse.model.dto.member.MemberPwDto;
import com.happyhouse.model.service.member.JwtService;
import com.happyhouse.model.service.member.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/member")
@Api("회원 Api")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtService jwtService;

	@ApiOperation(value = "모든 회원 리스트", notes = "모든 회원 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<MemberDto>> memberList() throws Exception {
		logger.info("listArticle - 호출");
		return new ResponseEntity<List<MemberDto>>(memberService.searchAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "회원 가입", notes = "회원 가입을 실행한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createMember(@RequestBody @ApiParam(value = "회원 정보.", required = true) MemberPwDto memberDto) throws Exception {
		logger.info("memberService.create - 호출");
		if (memberService.create(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "회원 정보 조회", notes = "회원 정보 조회", response = MemberDto.class)
	@GetMapping("/{member_id}")
	public ResponseEntity<MemberInfoDto> searchMember(@PathVariable("member_id") @ApiParam(value = "필요한 회원 id", required = true) String memberId) throws Exception {
		logger.info("searchMember - 호출 : " + memberService.search(memberId) );
		return new ResponseEntity<MemberInfoDto>(memberService.search(memberId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateMember(@RequestBody @ApiParam(value = "수정할 회원 정보.", required = true) MemberPwDto memberDto) throws Exception {
		logger.info("updateMember - 호출");
		if (memberService.update(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "회원 삭제", notes = "해당 회원 삭제. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{member_id}")
	public ResponseEntity<String> removeMember(@PathVariable("member_id") @ApiParam(value = "삭제할 회원 ID.", required = true) String memberId) throws Exception {
		logger.info("deleteMember - 호출");
		if (memberService.remvoe(memberId)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberLoginDto memberLoginDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String loginUser = memberService.login(memberLoginDto);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser, "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "비밀번호 찾기", notes = "비밀번호 찾기", response = Map.class)
	@PostMapping("/findpw")
	public ResponseEntity<String> findPw(
			@RequestBody @ApiParam(value = "비밀번호 찾기 시 필요한 정보를 받는다(아이디, email).", required = true) MemberFindDto memberFindDto) throws Exception {
		
		if (memberService.memberFindPassword(memberFindDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
