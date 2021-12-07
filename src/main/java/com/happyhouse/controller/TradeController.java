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

import com.happyhouse.model.Board;
import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.BoardParameterDto;
import com.happyhouse.model.dto.houseMap.GugunDongCodeDto;
import com.happyhouse.model.dto.houseMap.SidoGugunCodeDto;
import com.happyhouse.model.service.board.BoardService;
import com.happyhouse.model.service.trade.TradeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RequestMapping("/trade")
@Api("거래정보 Api")
public class TradeController {
	
	private static final Logger logger = LoggerFactory.getLogger(TradeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	
	@Autowired
	private TradeService tradeService;
	
	@GetMapping
	public ResponseEntity<List<Trade>> searchAll() throws Exception {
		logger.info("searchAll(Trade) - 호출");
		return new ResponseEntity<List<Trade>>(tradeService.searchAll(), HttpStatus.OK);
	}
//	
	@ApiOperation(value = "거래 번호로 검색", notes = "거래번호에 해당하는 거래 정보를 반환한다.", response = Trade.class)
	@GetMapping("/{tradeId}")
	public ResponseEntity<Trade> search(@PathVariable("tradeId") @ApiParam(value = "얻어올 거래 번호.", required = true)int tradeId) throws Exception {
		logger.info("serach(Trade) - 호출 : " + tradeId);
		
		return new ResponseEntity<Trade>(tradeService.search(tradeId), HttpStatus.OK);
	}
	
	@ApiOperation(value = "시도 정보", notes = "전국의 시도를 반환한다.", response = List.class)
	@GetMapping("/sido")
	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
		logger.info("sido - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(tradeService.getSido(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "구군 정보", notes = "전국의 구군을 반환한다.", response = List.class)
	@GetMapping("/gugun")
	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") @ApiParam(value = "시도코드.", required = true) String sido) throws Exception {
		logger.info("gugun - 호출");
		return new ResponseEntity<List<SidoGugunCodeDto>>(tradeService.getGugunInSido(sido), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동 정보", notes = "구군의 동을  모두 반환한다.", response = List.class)
	@GetMapping("/dong")
	public ResponseEntity<List<GugunDongCodeDto>> dong(@RequestParam("gugun") @ApiParam(value = "구군 코드.", required = true) String gugun) throws Exception {
		logger.info("dong - 호출");
		return new ResponseEntity<List<GugunDongCodeDto>>(tradeService.getDongInGugun(gugun), HttpStatus.OK);
	}
	
	@ApiOperation(value = "동네 거래 정보", notes = "해당 동안에  모든 거래정보를 반환한다.", response = List.class)
	@GetMapping("/tradeindong")
	public ResponseEntity<List<Trade>> tradeInDong(@RequestParam("addr") @ApiParam(value = "구군 코드.", required = true) String addr) throws Exception {
		logger.info("tradeInDong - 호출");
		return new ResponseEntity<List<Trade>>(tradeService.getTradeInDong(addr), HttpStatus.OK);
	}
}
