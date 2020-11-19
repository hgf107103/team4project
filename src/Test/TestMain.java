package Test;

import java.sql.Date;

public class TestMain {

	public static void main(String[] args) {
		Date date = new Date(System.currentTimeMillis());
		Date date2 = new Date(0);
		date2.setYear(120);
		date2.setMonth(10);
		date2.setDate(21);
		Date date3 = new Date((date2.getTime() / (24 * 60 * 60 * 1000)) * (24 * 60 * 60 * 1000));
		String a = date3.toString();
		
		System.out.println("asd" + a);
	
		
		
		System.out.println(date);
		System.out.println(date2);
		System.out.println(date3);
		System.out.println(date.getTime());
		System.out.println(date2.getTime());
		System.out.println(date3.getTime());
		
		if(date.getTime() > date2.getTime()) {
			System.out.println(1);
		}

	}

}
