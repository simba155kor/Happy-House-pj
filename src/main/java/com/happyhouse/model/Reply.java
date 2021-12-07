package com.happyhouse.model;


public class Reply {
	
	private int replyNo;
	private int boardNo;
	
	private String replyContent;
	private String replyRecent;
	private String memberId;
	
	





	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyRecent() {
		return replyRecent;
	}
	public void setReplyRecent(String replyRecent) {
		this.replyRecent = replyRecent;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", boardNo=" + boardNo + ", replyContent=" + replyContent
				+ ", replyRecent=" + replyRecent + ", memberId=" + memberId + "]";
	}
	
	
	
	
	

}
