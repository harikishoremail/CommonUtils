package com.hrc.commons.utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.context.SecurityContextHolder;

import com.hrc.security.LoginSession;
public class DateTimeUtils {



	public static String getCurrentYearStr() {
		int i = Calendar.getInstance().get(Calendar.YEAR);
		return i + "";
	}

	public static String getCurrentMonthStr() {
		long currentMonth = getCurrentMonth();
		return currentMonth + "";
	}

	public static long getCurrentMonth() {
		int i = Calendar.getInstance().get(Calendar.MONTH);
		return i + 1;
	}

	public static String getMonthNameByNumber(int month) {
		return new DateFormatSymbols().getMonths()[month - 1].toUpperCase();
	}

	public static String getMonthNameByNumber(long month) {
		return new DateFormatSymbols().getMonths()[(int) (month - 1)].toUpperCase();
	}

	public static int getCurrentYear() {
		int i = Calendar.getInstance().get(Calendar.YEAR);
		return i;
	}

	public static String getCurrentFinancialYear() {
		LoginSession principal = (LoginSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getFinancialYear();
	}

	public static String getPreviousFinancialYear() {
		LoginSession principal = (LoginSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getPreviousYear();
	}

	public static String getNextFinancialYear() {
		LoginSession principal = (LoginSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal.getNextYear();
	}

	public static Date getFromDateOfMonth(Long monthId) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, monthId.intValue() - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return c.getTime();
	}

	public static Date getToDateOfMonth(Long monthId) {

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, monthId.intValue());
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.add(Calendar.DATE, -1);

		return c2.getTime();

	}

	public static Date incrementByOneDay(Date date) {

		Calendar c2 = Calendar.getInstance();
		c2.setTime(date);
		c2.add(Calendar.DATE, 1);

		return c2.getTime();

	}

	public static Date getStartDateOfMonth(int year,Long monthId) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, monthId.intValue() - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date date = null;

		try {
			date = sdf.parse(sdf.format(c.getTime())) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;
	}

	public static int getNumberOfDaysInaMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date getEndDateOfMonth(int year,Long monthId) {

		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.YEAR, year);
		c2.set(Calendar.MONTH, monthId.intValue());
		c2.set(Calendar.DAY_OF_MONTH, 1);
		c2.add(Calendar.DATE, -1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Date date = null;

		try {
			date = sdf.parse(sdf.format(c2.getTime())) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date;

	}

	public static Date getLastDateOfMonth(Long month) {
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, month.intValue() -1);
		c2.set(Calendar.DAY_OF_MONTH, c2.getActualMaximum(Calendar.DATE));

		return c2.getTime();
	}

	public static Date getFirstDateOfMonth(String financialYear, Long monthId) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, monthId.intValue() - 1);
		c.set(Calendar.DAY_OF_MONTH, 1);

		String[] years = financialYear.split("-");

		if (monthId >=4 ) {
			c.set(Calendar.YEAR, Integer.valueOf(years[0]));
		} else {
			c.set(Calendar.YEAR, Integer.valueOf(years[1]));

		}

		return c.getTime();
	}


	public static Date getLastDateOfMonth(String financialYear, Long monthId) {
		Calendar c2 = Calendar.getInstance();
		c2.set(Calendar.MONTH, monthId.intValue() -1);
		c2.set(Calendar.DAY_OF_MONTH, c2.getActualMaximum(Calendar.DATE));

		String[] years = financialYear.split("-");

		if (monthId >=4 ) {
			c2.set(Calendar.YEAR, Integer.valueOf(years[0]));

		} else {
			c2.set(Calendar.YEAR, Integer.valueOf(years[1]));
		}

		return c2.getTime();
	}


	public static Date getFirstDateOfMonthOfGivenDate(Date giveDate) {

		Calendar c = Calendar.getInstance();
		c.setTime(giveDate);
		c.set(Calendar.DAY_OF_MONTH, 1);

		return c.getTime();
	}

	public static Date getLastDateOfMonthOfGivenDate(Date date) {

		Calendar lastDateOfMonth = Calendar.getInstance();

		lastDateOfMonth.setTime(date);

		lastDateOfMonth.set(Calendar.DATE, lastDateOfMonth.getActualMaximum(Calendar.DATE));

		return lastDateOfMonth.getTime();
	}

	public static int getMonthOfGivenDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return (calendar.get(Calendar.MONTH)+1);
	}

	public static int getYearOfGivenDate(Date date)
	{
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	public static int getDaysOfMonth(long monthNumber) {

		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, (int) monthNumber - 1);
		int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	public static String getDepartmentCode() {

		LoginSession principal = (LoginSession) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return principal.getDepartmentCode();
	}

	public static int getDaysDifferenceBetweenTwoDates(Date from, Date to) {
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(from);
		calendar2.setTime(to);
		long miliSecondForDate1 = calendar1.getTimeInMillis();
		long miliSecondForDate2 = calendar2.getTimeInMillis();
		long diffInMilis = miliSecondForDate2 - miliSecondForDate1;
		long diffInDays = diffInMilis / (24 * 60 * 60 * 1000);
		return (int) diffInDays + 2; // added 2 for inclusive. remove it for exclusive.
	}

	public static int getYearsBetweenTwoDates(Date dateOfBirth, Date date) {
		Calendar day1 = Calendar.getInstance();
		Calendar day2 = Calendar.getInstance();
		day1.setTime(dateOfBirth);
		day2.setTime(new Date());

		int years1 = day1.get(Calendar.YEAR);
		int years2 = day2.get(Calendar.YEAR);

		int age = years2 - years1;
		return age;
	}
	
	public static int getMonthsBetweenTwoDatesWithinOneYear(Date fromDate, Date toDate) {
		Calendar day1 = Calendar.getInstance();
		Calendar day2 = Calendar.getInstance();
		day1.setTime(fromDate);
		day2.setTime(toDate);

		int months1 = day1.get(Calendar.MONTH)+1;
		int months2 = day2.get(Calendar.MONTH)+1;

		int noOfMonths =  (getMonthSequenceNumber(months2) - getMonthSequenceNumber(months1))+1;
		return noOfMonths;
	}

	public static String getCurrentDateWithFormat() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateWithoutTime(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	public static Date getDateWithoutTimes(Date givenDate) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		Date date = null;

		try {
			date = dateFormat.parse(dateFormat.format(givenDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}
	
	public static String getDateWithyyyyMMdd(Date givenDate) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		return dateFormat.format(givenDate);
	}

	public static Integer getMonthSequenceNumber(Long month) {

		// Months According To Financial Year

		Map<Long,Integer> months = new HashMap<Long, Integer>();
		months.put(1L,10); // JANUARY
		months.put(2L,11); // FEBRAURY
		months.put(3L,12); // MARCH -- Financial Year Ending Month
		months.put(4L,1); //APRIL -- Financial Year Starting Month
		months.put(5L,2); // MAY
		months.put(6L,3); //JUNE
		months.put(7L,4); //JULY
		months.put(8L,5); //AUGUST
		months.put(9L,6); // SEPTEMBER
		months.put(10L,7); //OCTOBER
		months.put(11L,8); //NOVEMBER
		months.put(12L,9); //DECEMBER

		return months.get(month);
	}

	public static Integer getMonthSequenceNumber(Integer month) {

		// Months According To Financial Year

		Map<Integer,Integer> months = new HashMap<Integer, Integer>();
		months.put(1,10); // JANUARY
		months.put(2,11); // FEBRAURY
		months.put(3,12); // MARCH -- Financial Year Ending Month
		months.put(4,1); //APRIL -- Financial Year Starting Month
		months.put(5,2); // MAY
		months.put(6,3); //JUNE
		months.put(7,4); //JULY
		months.put(8,5); //AUGUST
		months.put(9,6); // SEPTEMBER
		months.put(10,7); //OCTOBER
		months.put(11,8); //NOVEMBER
		months.put(12,9); //DECEMBER

		return months.get(month);
	}

	public static int getAge(Date dateOfBirth,Date curentDate) {

		Calendar today = Calendar.getInstance();
		Calendar birthDate = Calendar.getInstance();

		int age = 0;
		today.setTime(curentDate);
		birthDate.setTime(dateOfBirth);
		if (birthDate.after(today)) {
			throw new IllegalArgumentException("Can't be born in the future");
		}

		age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);

		// If birth date is greater than todays date (after 2 days adjustment of leap year) then decrement age one year   
		if ( (birthDate.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR) > 3) ||
				(birthDate.get(Calendar.MONTH) > today.get(Calendar.MONTH ))){
			age--;

			// If birth date and todays date are of same month and birth day of month is greater than todays day of month then decrement age
		}else if ((birthDate.get(Calendar.MONTH) == today.get(Calendar.MONTH )) &&
				(birthDate.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH ))){
			age--;
		}

		return age;
	}

	public static Long getnumberofdays(Date date, Date date2)
	{
		Long diff  = DateTimeUtils.getDateWithoutTimes(date2).getTime() - DateTimeUtils.getDateWithoutTimes(date).getTime();
		diff = Math.abs(diff);
		Long noofdays = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+1;

		return noofdays;
	}

	public static String getOrdinalNumbers(int i) {

		String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
		switch (i % 100) {
		case 11:
		case 12:
		case 13:
			return i + "th";
		default:
			return i + sufixes[i % 10];

		}
	}
	public static String getDayName(Long date) {
		String Name = "";
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date); 
		int day = cal.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case 1:
			Name = "Sunday";
			break;
		case 2:
			Name = "Monday";
			break;
		case 3:
			Name = "Tuesday";
			break;
		case 4:
			Name = "Wednesday";
			break;
		case 5:
			Name =  "Thursday";
			break;
		case 6:
			Name = "Friday";
			break;
		case 7:
			Name = "Saturday";
		}
		return Name;

	}
	
	public static String theMonth(Long  date){
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date); 
		int month = cal.get(Calendar.MONTH);
		
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	
	public static String getPreviousYear() {
	    Calendar prevYear = Calendar.getInstance();
	    prevYear.add(Calendar.YEAR, -1);
	    int i = prevYear.get(Calendar.YEAR);
	   return i+"";
	}

}
