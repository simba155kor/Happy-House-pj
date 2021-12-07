package com.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.houseMap.GugunDongCodeDto;
import com.happyhouse.model.dto.houseMap.SidoGugunCodeDto;

@Mapper
public interface TradeMapper {
	public List<Trade> searchAll() throws SQLException;
	public Trade search(int tradeId) throws SQLException;
	List<SidoGugunCodeDto> getSido() throws SQLException;
	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
	List<GugunDongCodeDto> getDongInGugun(String gugun) throws SQLException;
	List<Trade> getTradeInDong(String dong) throws SQLException;
}
