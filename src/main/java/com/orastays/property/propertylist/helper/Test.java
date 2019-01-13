package com.orastays.property.propertylist.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {

		try {
			Test test = new Test();
			System.out.println(test.getMinuteDiff("2019-04-13"));
		} catch (Exception e) {

		}

	}

	public static int getMinuteDiff(String firstDate) {

		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
