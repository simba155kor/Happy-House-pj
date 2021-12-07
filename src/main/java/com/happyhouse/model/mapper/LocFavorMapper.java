package com.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.locfavor.LocFavorDto;

@Mapper
public interface LocFavorMapper {
	int remove(LocFavorDto locfavorDto) throws Exception;
	int create(LocFavorDto locfavorDto) throws Exception;
	List<Trade> search(String memberId) throws Exception;
}
