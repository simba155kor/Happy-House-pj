package com.happyhouse.model.service.board;

import java.util.List;


import com.happyhouse.model.Board;

import com.happyhouse.model.dto.BoardParameterDto;
import com.happyhouse.util.PageNavigation;

public interface BoardService {
	

	public boolean writeBoard(Board board) throws Exception;
	public List<Board> listBoard(BoardParameterDto parameter) throws Exception;
	public PageNavigation makePageNavigation (BoardParameterDto parameter) throws Exception;
	
	public Board getBoard(int boardNo) throws Exception;
	public void plusCnt(int boardNo) throws Exception;
	
	public boolean modifyBoard(Board board) throws Exception;
	public boolean deleteBoard(int boardNo) throws Exception;
	
}



/*
 * 
 * 	public int writeBoard(BoardDto boardDto) throws SQLException;
	public List<Board> listBoard(BoardParameterDto parameter) throws SQLException;
	public int getTotalCount(BoardParameterDto parameter) throws SQLException;
	public Board getBoard(int boardNo) throws SQLException;
	public void plusCnt(int boardNo) throws SQLException;
	public int modifyBoard(BoardDto dto) throws SQLException;
	public void deleteBoard(int boardNo) throws SQLException;
	
	
 */
