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
			System.out.println(getDayDiff("2019-04-03", "2019-12-31 00:00:01"));
		} catch (Exception e) {

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
