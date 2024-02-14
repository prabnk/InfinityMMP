package Practices;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateExample {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
	    /* int day1=calendar.get(calendar.DAY_OF_MONTH);
	     int month1=calendar.get(calendar.MONTH)+1;
	     int  year1=calendar.get(calendar.YEAR);
	     
	     System.out.println(day1);
			System.out.println(month1);
			System.out.println(year1);*/
		
	calendar.add(Calendar.DATE, 5);
		
         
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM/DD/YYYY");
		String date = sdf.format(calendar.getTime());
		System.out.println("Date " +date);

		String dateArr[] = date.split("/");

		String day = dateArr[1];
	

		System.out.println("Day  " + day);

		System.out.println(dateArr[0]);
		System.out.println(dateArr[1]);
		System.out.println(dateArr[2]);
		
		String test1="04";
		String test2="40";
		
		System.out.println(test1.replaceFirst("^0", "").trim());
		System.out.println(test2.replaceFirst("^0", "").trim());
		
		SimpleDateFormat sdf1= new SimpleDateFormat("MM/DD/YYYY");
		String date1 = sdf1.format(calendar.getTime());
		System.out.println("Date " +date1);

	}

}
