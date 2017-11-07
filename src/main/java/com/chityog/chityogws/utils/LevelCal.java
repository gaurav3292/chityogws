package com.chityog.chityogws.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LevelCal {

	public static int getDatesDifference(Date startDate, Date endDate) {

		LocalDate sDate, eDate;
		sDate = startDate.toLocalDate();
		eDate = endDate.toLocalDate();
		int days = (int) ChronoUnit.DAYS.between(sDate, eDate);
		return days;

	}

}
