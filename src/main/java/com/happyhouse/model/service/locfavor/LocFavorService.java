package com.happyhouse.model.service.locfavor;

import java.util.List;

import com.happyhouse.model.Trade;
import com.happyhouse.model.dto.locfavor.LocFavorDto;

public interface LocFavorService {
	List<Trade> search(String id) throws Exception;
	boolean remvoe(LocFavorDto locfavorDto) throws Exception;
	boolean create(LocFavorDto locfavorDto) throws Exception;
}
