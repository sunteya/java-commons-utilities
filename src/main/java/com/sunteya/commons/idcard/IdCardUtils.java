/**
 * Created on 2006-6-30
 * Created by Sunteya
 */
package com.sunteya.commons.idcard;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author 诸南敏
 * @email zhunm@dep5.com
 */
public class IdCardUtils {

	private final static String DEFAULT_DATE_SYTLE = "yyyyMMdd";

	public static String formatDate(Date date) {
		return formatDate(date, DEFAULT_DATE_SYTLE);
	}

	public static String formatDate(Date date, String style) {
		if (date == null) {
			return null;
		}

		DateFormat birthdayFormatter = new SimpleDateFormat(style);
		return birthdayFormatter.format(date);
	}

	public static Date parseDate(String source) {
		return parseDate(source, DEFAULT_DATE_SYTLE);
	}

	public static Date parseDate(String source, String style) {
		DateFormat birthdayFormatter = new SimpleDateFormat(style);
		try {
			Date birthday = birthdayFormatter.parse(source);
			return birthday;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (chr < '0' || '9' < chr) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotNumber(String regionCode) {
		return !isNumber(regionCode);
	}

}
