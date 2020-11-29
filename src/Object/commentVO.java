package Object;

import java.sql.Date;

public class commentVO {
	private int commentNumber;
	private int boardNumber;
	private int userNumber;
	private String commentText;
	private Date commentDate;
	
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
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	@Override
	public String toString() {
		return "commentVO [commentNumber=" + commentNumber + ", boardNumber=" + boardNumber + ", userNumber="
				+ userNumber + ", commentText=" + commentText + ", commentDate=" + commentDate + "]";
	}
	
	
	
}
