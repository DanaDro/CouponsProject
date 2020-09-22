package com.johnbryce.project.utils;

public class DateUtils {
	
	public static String niceDate(java.sql.Date date) {
		return String.format("%02d/ %02d/ %04d", date.getDate(), date.getMonth(), date.getYear());
	}
}
