package object;

public class userInfoVO {
	int userNumber;
	int boardCount;
	int commentCount;
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	@Override
	public String toString() {
		return "userInfoVO [userNumber=" + userNumber + ", boardCount=" + boardCount + ", commentCount=" + commentCount
				+ "]";
	}
	
	
}
