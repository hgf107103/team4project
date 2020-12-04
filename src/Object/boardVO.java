package Object;

import java.sql.Date;

public class boardVO {
	private int boardNumber;
	private int userNumber;
	private int categoryNumber;
	private String boardText;
	private Date boardDate;
	private int commentCount;
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
	public int getCategoryNumber() {
		return categoryNumber;
	}
	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}
	public String getBoardText() {
		return boardText;
	}
	public void setBoardText(String boardText) {
		this.boardText = boardText;
	}
	public Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "boardVO [boardNumber=" + boardNumber + ", userNumber=" + userNumber + ", categoryNumber="
				+ categoryNumber + ", boardText=" + boardText + ", boardDate=" + boardDate + ", commentCount="
				+ commentCount + "]";
	}
	
	
	
}
