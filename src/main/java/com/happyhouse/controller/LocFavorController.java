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

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.locfavor.LocFavorDto;
import com.happyhouse.model.dto.member.MemberDto;
import com.happyhouse.model.dto.member.MemberInfoDto;
import com.happyhouse.model.dto.member.MemberLoginDto;
import com.happyhouse.model.dto.member.MemberPwDto;
import com.happyhouse.model.service.locfavor.LocFavorService;
import com.happyhouse.model.service.member.JwtService;
import com.happyhouse.model.service.member.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/locfavor")
@Api("회원 Api")
public class LocFavorController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocFavorController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private LocFavorService locFavorService;

	@ApiOperation(value = "선호 거래 리스트", notes = "회원이 선택한 선호지역을 반환한다.", response = List.class)
	@GetMapping("/{memberId}")
	public ResponseEntity<List<Trade>> memberList(@PathVariable("memberId") @ApiParam(value = "찾아야 하는 멤버 선호 지역.", required = true) String memberId) throws Exception {
		logger.info("search(locFavor) - 호출");
		return new ResponseEntity<List<Trade>>(locFavorService.search(memberId), HttpStatus.OK);
	}

	@ApiOperation(value = "관심 지역 추가", notes = "선호 지역 추가. 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createMember(@RequestBody @ApiParam(value = "선호 지역", required = true) LocFavorDto locFavorDto) throws Exception {
		logger.info("locFavorService.create - 호출");
		if (locFavorService.create(locFavorDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "관심 지역 삭제", notes = "관심 지역 삭제. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> removeMember(@RequestBody @ApiParam(value = "삭제할 회원 ID.", required = true) LocFavorDto locFavordto) throws Exception {
		logger.info("deleteMember - 호출");
		if (locFavorService.remvoe(locFavordto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
