package com.orastays.property.propertylist.helper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {

	public static void main(String[] args) {

		try {
			Test test = new Test();
			test.checkPath();
		} catch (Exception e) {

		}

	}

	public void checkPath() throws UnsupportedEncodingException {

		String rootPath = "20" + "10";
		System.out.println(rootPath);
	}

}
