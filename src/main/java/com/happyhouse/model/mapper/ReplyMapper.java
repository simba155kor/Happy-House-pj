package com.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.happyhouse.model.Reply;

@Mapper
public interface ReplyMapper {
	
	public List<Reply> listReply(int boardNo) throws SQLException;
	public int writeReply(Reply reply) throws SQLException;
	public int modifyReply(Reply reply) throws SQLException;
	public int deleteReply(int replyNo) throws SQLException;
	

}
