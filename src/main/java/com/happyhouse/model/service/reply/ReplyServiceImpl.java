package com.happyhouse.model.service.reply;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyhouse.model.Reply;
import com.happyhouse.model.mapper.ReplyMapper;

/*
 * 
 * 
 * public List<Reply> replyList(int boardno) throws SQLException;
	public void write(Reply reply) throws SQLException;
	public void modify(Reply reply) throws SQLException;
 */

@Service
public class ReplyServiceImpl  implements ReplyService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	@Transactional
	public List<Reply> listReply(int boardNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReplyMapper.class).listReply(boardNo);
	}

	@Override
	@Transactional
	public boolean writeReply(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReplyMapper.class).writeReply(reply) == 1;
	}

	@Override
	@Transactional
	public boolean modifyReply(Reply reply) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReplyMapper.class).modifyReply(reply) == 1;
		
	}

	@Override
	@Transactional
	public boolean deleteReply(int replyNo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.getMapper(ReplyMapper.class).deleteReply(replyNo) == 1;
	}
	
	
	
	
	
	

}
