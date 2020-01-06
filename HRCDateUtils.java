package com.hrc.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hrc.commons.CustomDateFormat;
import com.hrc.commons.exceptions.DataParsingException;

public class HRCDateUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(HRCDateUtils.class);
	
	private static final String shortDateFormat = "dd-MM-yyyy";
	
	private static final String shortDateFormatWithSlash = "dd/MM/yyyy";
	
	private static final String reverseShortDateFormat = "yyyy-MM-dd";
	
	private static final String twelveHoursDateFormat = "dd-MM-yyyy hh:mm:ss a";
	
	private static final String twentyFourHoursDateFormat = "dd-MM-yyyy hh:mm:ss";
	
	private static final String sqlDateFormat = "MMM dd, yyyy HH:mm:ss a";
	
	private static final String reportDateFormat = "MMM dd, yyyy";
	
	private static final String prospectusBookDateFormat = "EEEE, dd MMMM, yyyy";
	
	private static final String onlyTimeFormat = "hh:mm a";
	
	public static Date truncateDate(Date date) {
		
		if (date == null) {
			return null;
		}
		
		return DateUtils.truncate(date, Calendar.DATE);
	}
	
	public static Date truncateDate(Long dateInLong) {
		
		return DateUtils.truncate(new Date(dateInLong), Calendar.DATE);
	}
	
	public static String getFormattedDate(Object inputDate, CustomDateFormat dateFormat) {
		
		if (inputDate == null) {
			return "-";
		} else if (inputDate instanceof Double) {
			inputDate = ((Double) inputDate).longValue();
		} else if (inputDate instanceof DateTime) {
			inputDate = ((DateTime) inputDate).getMillis();
			inputDate = new Date((Long) inputDate);
		}
		
		String formattedDate = null;
		Date convertedDateFromSqlDate = null;
		
		switch (dateFormat) {
			
			case ShortDateFromLong:
				formattedDate = new SimpleDateFormat(shortDateFormat).format(new Date((Long) inputDate));
				break;
			
			case ShortDateFromReverseShortDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(reverseShortDateFormat).parse((String) inputDate);
					
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(shortDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case ShortDateFromDate:
				formattedDate = new SimpleDateFormat(shortDateFormat).format(inputDate);
				break;
			case ShortDateWithSlashFromLong:
				formattedDate = new SimpleDateFormat(shortDateFormatWithSlash).format(new Date((Long)inputDate));
				break;
			case ShortDateFromReportDate:
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(reportDateFormat).parse((String) inputDate);
					
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(shortDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case ShortDateFromSqlDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(sqlDateFormat).parse((String) inputDate);
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(shortDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case ReversehortDateFromLong:
				formattedDate = new SimpleDateFormat(reverseShortDateFormat).format(new Date((Long) inputDate));
				break;
			
			case ReverseShortDateFromShortDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(shortDateFormat).parse((String) inputDate);
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(reverseShortDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case ReverseShortDateFromDate:
				formattedDate = new SimpleDateFormat(reverseShortDateFormat).format(inputDate);
				break;
			
			case ReverseShortDateFromSqlDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(sqlDateFormat).parse((String) inputDate);
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(reverseShortDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case Full12HoursDateFromLong:
				formattedDate = new SimpleDateFormat(twelveHoursDateFormat).format(new Date((Long) inputDate));
				break;
			
			case Full12HoursDateFromDate:
				formattedDate = new SimpleDateFormat(twelveHoursDateFormat).format(inputDate);
				break;
			
			case Full24HoursDateFromLong:
				formattedDate = new SimpleDateFormat(twentyFourHoursDateFormat).format(new Date((Long) inputDate));
				break;
			
			case Full24HoursDateFromDate:
				formattedDate = new SimpleDateFormat(twentyFourHoursDateFormat).format(inputDate);
				break;
				
			case ProspectusBookDate:
				formattedDate = new SimpleDateFormat(prospectusBookDateFormat).format(inputDate);
				break;
			
			case OnlyTime:
				formattedDate = new SimpleDateFormat(onlyTimeFormat).format(inputDate);
				break;
			
			case Full12HoursDateFromSqlDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(sqlDateFormat).parse((String) inputDate);
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(twelveHoursDateFormat).format(convertedDateFromSqlDate);
				break;
			
			case Full24HoursDateFromSqlDate:
				
				try {
					convertedDateFromSqlDate = new SimpleDateFormat(sqlDateFormat).parse((String) inputDate);
				} catch (ParseException e) {
					logger.debug("Internal Server Error : Can't able to Convert Date");
					throw new DataParsingException("Internal Server Error : Can't able to Convert Date");
				}
				
				formattedDate = new SimpleDateFormat(twentyFourHoursDateFormat).format(convertedDateFromSqlDate);
				break;
			
			default:
				formattedDate = new SimpleDateFormat(twelveHoursDateFormat).format(inputDate);
				break;
		}
		
		return formattedDate == null ? "-" : formattedDate.replaceAll("-", "/");
	}
	
	public static String getDateSuffix( int day) { 
        switch (day) {
            case 1: case 21: case 31:
                   return ("st");

            case 2: case 22: 
                   return ("nd");

            case 3: case 23:
                   return ("rd");

            default:
                   return ("th");
        }
	}

}
