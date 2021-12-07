package com.happyhouse.model.mapper;

import java.sql.SQLException;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.Board;

import com.happyhouse.model.dto.BoardParameterDto;

@Mapper
public interface BoardMapper {
	
	
	
	public int writeBoard(Board board) throws SQLException;
	public List<Board> listBoard(BoardParameterDto parameter) throws SQLException;
	public int getTotalCount(BoardParameterDto parameter) throws SQLException;
	public Board getBoard(int boardNo) throws SQLException;
	public void plusCnt(int boardNo) throws SQLException;
	public int modifyBoard(Board board) throws SQLException;
	public int deleteBoard(int boardNo) throws SQLException;
	
	
	
	
	

}
