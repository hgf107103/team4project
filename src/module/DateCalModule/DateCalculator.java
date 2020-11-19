package module.DateCalModule;

import java.sql.Date;

public class DateCalculator {
	
	private Date nowDate;
	private Date targetDate;
	
	public DateCalculator(Date targetDate) {
		Date tempDate = new Date(System.currentTimeMillis());
		nowDate = new Date((tempDate.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
		this.targetDate = targetDate;
	}
	
	public Date DatePlus(int Day) {
		Date temp = new Date(targetDate.getTime() + (Day * (24 * 60 * 60 * 1000)));
		return temp;
	}
}
