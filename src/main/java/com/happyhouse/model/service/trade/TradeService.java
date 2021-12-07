package com.happyhouse.model.service.trade;

import java.util.List;

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.houseMap.GugunDongCodeDto;
import com.happyhouse.model.dto.houseMap.SidoGugunCodeDto;


public interface TradeService {
	List<Trade> searchAll() throws Exception;
	Trade search(int tradeId) throws Exception;
	List<SidoGugunCodeDto> getSido() throws Exception;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
	List<GugunDongCodeDto> getDongInGugun(String gugun) throws Exception;
	List<Trade> getTradeInDong(String dong) throws Exception;
}
