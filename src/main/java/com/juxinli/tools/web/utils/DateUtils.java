package com.juxinli.tools.web.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static final String PATTERN1 = "yyyy-MM-dd";
	public static final String PATTERN2 = "yyyy-MM-dd hh:ss:mm";
	
	public static Date transStrToDate( String dateStr, String pattern ) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		return sdf.parse( dateStr );
		
	}
	
	public static String transDateToString( Date date, String pattern ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat( pattern );
		return sdf.format( date );
		
	}

}
