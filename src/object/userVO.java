package object;

import java.sql.Date;

public class userVO {
	private int userNumber;
	private String userID;
	private String userPassword;
	private String userName;
	private String userNickname;
	private Date userCreateDate;
	private Date userStopDay;
	private int userOutCheck;
	private int adminUserCheck;
	
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public Date getUserCreateDate() {
		return userCreateDate;
	}
	public void setUserCreateDate(Date userCreateDate) {
		this.userCreateDate = userCreateDate;
	}
	public Date getUserStopDay() {
		return userStopDay;
	}
	public void setUserStopDay(Date userStopDay) {
		this.userStopDay = userStopDay;
	}
	public int getUserOutCheck() {
		return userOutCheck;
	}
	public void setUserOutCheck(int userOutCheck) {
		this.userOutCheck = userOutCheck;
	}
	public int getAdminUserCheck() {
		return adminUserCheck;
	}
	public void setAdminUserCheck(int adminUserCheck) {
		this.adminUserCheck = adminUserCheck;
	}
	@Override
	public String toString() {
		return "userVO [userNumber=" + userNumber + ", userID=" + userID + ", userPassword=" + userPassword
				+ ", userName=" + userName + ", userNickname=" + userNickname + ", userCreateDate=" + userCreateDate
				+ ", userStopDay=" + userStopDay + ", userOutCheck=" + userOutCheck + adminUserCheck +"]";
	}
	
	
	
}
