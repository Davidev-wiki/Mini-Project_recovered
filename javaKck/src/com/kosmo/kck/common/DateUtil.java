package com.kosmo.kck.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateUtil {

	public static String yyyymmdd() {

		return new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
	}

	public static String yyyymm() {

		return new SimpleDateFormat("yyyyMM").format(new Date()).toString();
	}

	public static String yyyy() {

		return new SimpleDateFormat("yyyy").format(new Date()).toString();
	}

	public static String cTime() {

		//return new SimpleDateFormat("yyyy.MM.dd hh:mm:ss").format(new Date(time)).toString();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String cTime = sdf.format(new Date());
		
		return cTime;
	}

	public static String[] comboYear() {
		int currentYear = Integer.parseInt(DateUtil.yyyy());
		int firstYear = 1900;
		String[] years = new String[currentYear - firstYear + 2];
		years[0] = "년도";
		for (int i = 1; i < years.length; i++) {
			years[i] = String.valueOf(currentYear--);
		}
		return years;
	}

	public static String[] comboMonth() {
		String[] months = new String[13];
		months[0] = "";
		for (int i = 1; i < months.length; i++) {
			months[i] = String.valueOf(i);
			if (1 == months[i].length()) {
				months[i] = "0" + i;
			}
		}
		return months;
	}

	public static String[] comboDay() {
		String[] days = new String[32];
		days[0] = "";
		for (int i = 1; i < days.length; i++) {
			days[i] = String.valueOf(i);
			if (1 == days[i].length()) {
				days[i] = "0" + i;
			}
		}
		return days;
	}

	public static void main(String[] args) {

		String d = DateUtil.yyyymmdd();
		System.out.println("d : " + d);
		System.out.println("dt : " + DateUtil.cTime());

		String m[] = DateUtil.comboMonth();
		for (int i = 0; i < m.length; i++) {
			System.out.println("m[" + i + "] : " + m[i]);
		}
	}
}
