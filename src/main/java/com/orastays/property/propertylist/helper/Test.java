package com.orastays.property.propertylist.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {

	public static void main(String[] args) {

		try {
			Test test = new Test();
			System.out.println(test.getDayDiff("2018-12-22", "2018-12-23"));
		} catch (Exception e) {

		}

	}

	public static int getDateDiff1(String firstDate) {

		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(firstDate);
			String currentDate = format.format(new Date());
			Date date2 = format.parse(currentDate);
			Double diffInMillies = (date2.getTime() - date1.getTime()) / (1000.0 * 60 * 60 * 24);
			return diffInMillies.intValue();

		} catch (ParseException e) {

			return 0;
		}
	}
	
	public static int getDayDiff(String firstDate, String endDate) {

		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = format.parse(firstDate);
			Date date2 = format.parse(endDate);
			Double diffInMillies = (date2.getTime() - date1.getTime()) / (1000.0 * 60 * 60 * 24);
			return diffInMillies.intValue();

		} catch (ParseException e) {

			return 0;
		}
	}
}
