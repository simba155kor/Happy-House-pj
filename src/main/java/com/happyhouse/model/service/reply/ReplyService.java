package com.happyhouse.model.service.reply;

import java.util.List;

import com.happyhouse.model.Board;
import com.happyhouse.model.Reply;

public interface ReplyService {
	
	public List<Reply> listReply(int boardNo) throws Exception;
	public boolean writeReply(Reply reply) throws Exception;
	public boolean modifyReply(Reply reply) throws Exception;
	public boolean deleteReply(int replyNo) throws Exception;
}
