package com.chityog.chityogws.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.chityog.chityogws.domain.LevelResultInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

public class LevelCal {

	public static int getDatesDifference(Date startDate, Date endDate) {

		LocalDate sDate, eDate;
		sDate = startDate.toLocalDate();
		eDate = endDate.toLocalDate();
		int days = (int) ChronoUnit.DAYS.between(sDate, eDate);
		return days;

	}

	public static double getLevelResult(UserLevelInfo userLevelInfo) {

		double y = userLevelInfo.getTotalNumberOfQuestions()
				* userLevelInfo.getCompletedNumberOfDays();
		double x = userLevelInfo.getNumberOfTrue() / y;

		return x * 100;

	}

	public static Map<String, Object> getUpdatedLevel(
			UserLevelInfo userLevelInfo, LevelResultInfo levelResultInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		switch (userLevelInfo.getUserLevel()) {
		case "1":
			if (levelResultInfo.getLevelResultPercent() >= 75) {
				map.put("level", "2");
				map.put("days", 30);
				map.put("msg",
						"Congratulations You have completed level 1 and 1evel 2 has been unlocked");
			} else if (levelResultInfo.getLevelResultPercent() >= 30
					&& levelResultInfo.getLevelResultPercent() < 75) {

				map.put("level", "1");
				map.put("days", 15);
				map.put("msg",
						"Oops! you have to repeat the level for next 15 days");


			}else {
				map.put("level", "1");
				map.put("days", 30);
				map.put("msg",
						"Oops! you have to repeat the level for next 30 days");
			}
			break;
			
		case "2":
			if (levelResultInfo.getLevelResultPercent() <= 25) {
				map.put("level", "3");
				map.put("days", 15);
				map.put("msg",
						"Congratulations You have completed level 2 and 1evel 3 has been unlocked");
			} else if (levelResultInfo.getLevelResultPercent() >= 25
					&& levelResultInfo.getLevelResultPercent() < 50) {

				map.put("level", "2");
				map.put("days", 15);
				map.put("msg",
						"Oops! you have to repeat the level for next 15 days");


			}else {
				map.put("level", "2");
				map.put("days", 30);
				map.put("msg",
						"Oops! you have to repeat the level for next 30 days");
			}
			break;
			
		case "3":
			if (levelResultInfo.getLevelResultPercent() <= 30) {
				map.put("level", "41");
				map.put("days", 15);
				map.put("msg",
						"Congratulations You have completed level 3 and 1evel 4 (Process your thoughts) has been unlocked");
			} else {
				map.put("level", "3");
				map.put("days", 15);
				map.put("msg",
						"Oops! you have to repeat the level for next 15 days");
			}
			break;
			
		case "41":
			if (levelResultInfo.getLevelResultPercent() <= 30) {
				map.put("level", "42");
				map.put("days", 15);
				map.put("msg",
						"Congratulations You have completed level Process your thoughts  and 1evel Face the mirror has been unlocked");
			} else {
				map.put("level", "41");
				map.put("days", 15);
				map.put("msg",
						"Oops! you have to repeat the level for next 15 days");
			}
			break;
			

		default:
			break;
		}

		return map;
	}
}
