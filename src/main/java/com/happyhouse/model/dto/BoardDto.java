package com.happyhouse.model.dto;




public class BoardDto {
	
	private String boardTitle;
	private String boardContent;
	private String boardRecent;
	private int boardCnt;
	private String memberId;
	
	public BoardDto() {
		
	}
	
	public BoardDto(String boardTitle, String boardContent, String boardRecent, int boardCnt, String memberId) {
		super();
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardRecent = boardRecent;
		this.boardCnt = boardCnt;
		this.memberId = memberId;
	}


	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardRecent() {
		return boardRecent;
	}
	public void setBoardRecent(String boardRecent) {
		this.boardRecent = boardRecent;
	}
	public int getBoardCnt() {
		return boardCnt;
	}
	public void setBoardCnt(int boardCnt) {
		this.boardCnt = boardCnt;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	
}
