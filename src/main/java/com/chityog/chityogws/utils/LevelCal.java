package com.chityog.chityogws.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.chityog.chityogws.bean.UserBean;
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

	public static double getLevelProgramResult(UserLevelInfo userLevelInfo) {

		double y = userLevelInfo.getAttendedNumberOfDays();
		double x = userLevelInfo.getTotalNumberOfDays() / y;
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
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level 1 and 1evel 2 has been unlocked");
			} else if (levelResultInfo.getLevelResultPercent() >= 30
					&& levelResultInfo.getLevelResultPercent() < 75) {

				map.put("level", "1");
				map.put("days", 15);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");

			} else {
				map.put("level", "1");
				map.put("days", 30);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 30 days");
			}
			break;

		case "2":
			if (levelResultInfo.getLevelResultPercent() <= 25) {
				map.put("level", "3");
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level 2 and 1evel 3 has been unlocked");
			} else if (levelResultInfo.getLevelResultPercent() >= 25
					&& levelResultInfo.getLevelResultPercent() < 50) {

				map.put("level", "2");
				map.put("days", 15);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");

			} else {
				map.put("level", "2");
				map.put("days", 30);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 30 days");
			}
			break;

		case "3":
			if (levelResultInfo.getLevelResultPercent() <= 30) {
				map.put("level", "41");
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level 3.\n "
						+ "If you feel these steps of Chit Yog Sadhana have helped you to make progress"
						+ " in your total wellbeing at the emotional, physical and mental "
						+ "levels, then purchase the next, deeper levels of Chit Yog Sadhana "
						+ "to unlock your hidden power to heal");
			} else {
				map.put("level", "3");
				map.put("days", 15);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");
			}
			break;

		case "41":
			if (levelResultInfo.getLevelResultPercent() <= 30) {
				map.put("level", "42");
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level Process your thoughts  and 1evel Face the mirror has been unlocked");
			} else {
				map.put("level", "41");
				map.put("days", 15);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");
			}
			break;

		case "42":
			if (userLevelInfo.getAttendedNumberOfDays() >= 9) {
				map.put("level", "5");
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level Face the miror  and 1evel 5 has been unlocked");
			} else {
				map.put("level", "42");
				map.put("days", 30);
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 30 days");
			}
			break;

		case "51":

			map = getUpdatedSubLevel(userLevelInfo);

			break;

		case "52":

			map = getUpdatedSubLevel(userLevelInfo);

			break;

		case "53":

			map = getUpdatedSubLevel(userLevelInfo);

			break;

		case "54":

			map = getUpdatedSubLevel(userLevelInfo);

			break;

		default:
			break;
		}

		return map;
	}

	public static Map<String, Object> getUpdatedSubLevel(
			UserLevelInfo userLevelInfo) {
		Map<String, Object> map = new HashMap<String, Object>();

		switch (userLevelInfo.getUserSubLevel()) {
		case "1":
			if (userLevelInfo.getAttendedNumberOfDays() >= 15) {
				map.put("level", userLevelInfo.getUserLevel());
				map.put("sub_level", "2");
				map.put("days", 15);
				map.put("msg",
						"Congratulations! Your next meditation has been unlocked");
			} else {
				map.put("level", userLevelInfo.getUserLevel());
				map.put("days", 15);
				map.put("sub_level", "1");
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");
			}
			break;
		case "2":
			if (userLevelInfo.getAttendedNumberOfDays() >= 15) {
				map.put("level", "6");
				map.put("days", 0);
				map.put("msg",
						"Congratulations You have completed level 5 and 1evel 6 has been unlocked"
						+ ".However if you feel   you also has some emotions from other "
						+ "stage we recommend that you also try to complete those"
						+ " mediation too to make sure all layers from your Chit can"
						+ " be burned affectively");

			} else {
				map.put("level", userLevelInfo.getUserLevel());
				map.put("days", 15);
				map.put("sub_level", "2");
				map.put("msg",
						"Sorry. You haven’t given this programme enough time to benefit from its results so you will need to repeat if for another 15 days");
			}

			break;

		default:
			break;
		}

		return map;

	}

	public static Map<String, Object> getRatingLevel(
			UserLevelInfo userLevelInfo, UserBean user) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		switch (user.getLineRating()) {

		case "1":
			map.put("level", "51");
			map.put("days", 15);
			map.put("sub_level", "1");
			map.put("msg",
					"Congratulations Meditation Programme 1 has been unlocked");
			break;

		case "2":
			map.put("level", "52");
			map.put("days", 15);
			map.put("sub_level", "1");
			map.put("msg",
					"Congratulations Meditation Programme 2 has been unlocked");
			break;

		case "3":
			map.put("level", "53");
			map.put("days", 15);
			map.put("sub_level", "1");
			map.put("msg",
					"Congratulations Meditation Programme 3 has been unlocked");
			break;

		case "4":
			map.put("level", "54");
			map.put("days", 15);
			map.put("sub_level", "1");
			map.put("msg",
					"Congratulations Meditation Programme 4 has been unlocked");
			break;

		default:

			break;

		}
		return map;
	}
}
