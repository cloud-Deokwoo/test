package com.myweb.el;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEl {
	
	private static SimpleDateFormat format = 
			new SimpleDateFormat("yyyy-MM-dd");
	
	public static String getFormat(Date date) {
		return format.format(date);
	}

}
