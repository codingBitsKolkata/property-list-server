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
			System.out.println(getMinuteDiff("2019-02-10 23:59:59"));
		} catch (Exception e) {

		}

	}

	public static int getMinuteDiff(String firstDate) {

		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date1 = format.parse(firstDate);
			String currentDate = format.format(new Date());
			Date date2 = format.parse(currentDate);
			Double diffInMillies = (date2.getTime() - date1.getTime()) / (1000.0 * 60);

			return diffInMillies.intValue();

		} catch (Exception e) {

			return 0;
		}
	}
}
