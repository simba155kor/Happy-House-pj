package com.happyhouse.model.service.trade;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.houseMap.GugunDongCodeDto;
import com.happyhouse.model.dto.houseMap.SidoGugunCodeDto;
import com.happyhouse.model.mapper.TradeMapper;

@Service
public class TradeServiceImpl implements TradeService{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<Trade> searchAll() throws Exception {
		return sqlSession.getMapper(TradeMapper.class).searchAll();
	}

	@Override
	public Trade search(int tradeId) throws Exception {
		System.out.println(tradeId);
		return sqlSession.getMapper(TradeMapper.class).search(tradeId);
	}
	
	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return sqlSession.getMapper(TradeMapper.class).getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return sqlSession.getMapper(TradeMapper.class).getGugunInSido(sido);
	}
	
	@Override
	public List<GugunDongCodeDto> getDongInGugun(String gugun) throws Exception {
		return sqlSession.getMapper(TradeMapper.class).getDongInGugun(gugun);
	}

	@Override
	public List<Trade> getTradeInDong(String dong) throws Exception {
		return sqlSession.getMapper(TradeMapper.class).getTradeInDong(dong);
	}


}
