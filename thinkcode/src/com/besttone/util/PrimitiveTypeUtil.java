package com.besttone.util;

public class PrimitiveTypeUtil {
	/**
	 * Long convert to hour:minute:second
	 * @param l
	 * @return
	 */
	public static String formatLongToTimeStr(Long l) {
		int hour = 0;
		int minute = 0;
		int second = 0;

		second = l.intValue() / 1000;

		if (second > 60) {
			minute = second / 60;
			second = second % 60;
		}
		if (minute > 60) {
			hour = minute / 60;
			minute = minute % 60;
		}
		return (hour + "hour" + minute + "minute" + second + "second");
	}
	
	
	public static void main(String[] args) {
		
	}
}
