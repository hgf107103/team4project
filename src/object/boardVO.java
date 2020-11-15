package object;

public class boardVO {
	private int boardNumber;
	private int userNumber;
	private int categoryNumber;
	private String boardText;
	private java.sql.Date boardDate;
	private boolean boardShowCheck;
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
	public java.sql.Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(java.sql.Date boardDate) {
		this.boardDate = boardDate;
	}
	public boolean isBoardShowCheck() {
		return boardShowCheck;
	}
	public void setBoardShowCheck(boolean boardShowCheck) {
		this.boardShowCheck = boardShowCheck;
	}
	
	
}
