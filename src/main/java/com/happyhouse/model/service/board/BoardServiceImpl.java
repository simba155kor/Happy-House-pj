package com.happyhouse.model.service.board;

import java.util.List;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhouse.model.Board;

import com.happyhouse.model.dto.BoardParameterDto;
import com.happyhouse.model.mapper.BoardMapper;
import com.happyhouse.util.PageNavigation;


@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession sqlSession;
	
	/*
	 * private String boardTitle;
	private String boardContent;
	private String boardRecent;
	private int boardCnt;
	private String memberId;
	 */

	@Override
	@Transactional
	public boolean writeBoard(Board board) throws Exception {
		
		if(board.getBoardTitle() == null || board.getBoardContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardMapper.class).writeBoard(board) == 1;
//		return false;
	}

	@Override
	@Transactional
	public List<Board> listBoard(BoardParameterDto parameter) throws Exception {
		// TODO Auto-generated method stub
		int start = parameter.getPg() == 0 ? 0 : (parameter.getPg() - 1) * parameter.getSpp();
		parameter.setStart(start);
		return sqlSession.getMapper(BoardMapper.class).listBoard(parameter);
	}

	@Override
	@Transactional
	public PageNavigation makePageNavigation(BoardParameterDto parameter) throws Exception {
		// TODO Auto-generated method stub
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(parameter.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(BoardMapper.class).getTotalCount(parameter);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / parameter.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = parameter.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < parameter.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}

	@Override
	@Transactional
	public Board getBoard(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(BoardMapper.class).getBoard(boardNo);
	}

	@Override
	@Transactional
	public void plusCnt(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.getMapper(BoardMapper.class).plusCnt(boardNo);
		
	}

	@Override
	@Transactional
	public boolean modifyBoard(Board board) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(BoardMapper.class).modifyBoard(board) == 1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(BoardMapper.class).deleteBoard(boardNo) == 1;
	}
	
	

}


