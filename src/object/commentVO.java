package object;

public class commentVO {
	private int commentNumber;
	private int boardNumber;
	private int userNumber;
	private String commentText;
	private boolean commentShowCheck;
	public int getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}
	public int getBoardNumber() {
		return boardNumber;
	}
	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public boolean isCommentShowCheck() {
		return commentShowCheck;
	}
	public void setCommentShowCheck(boolean commentShowCheck) {
		this.commentShowCheck = commentShowCheck;
	}
	
	
}
