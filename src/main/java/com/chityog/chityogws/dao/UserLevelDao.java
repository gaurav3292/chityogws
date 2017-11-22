package com.chityog.chityogws.dao;

import java.sql.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;
import com.chityog.chityogws.utils.Config;

@Repository
@Transactional
public class UserLevelDao {

	@Autowired
	private SessionFactory sessionFactory;

	public UserLevelInfo checkExistingUserLevel(UserInfo userInfo) {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"FROM UserLevelInfo where userInfo.userId = :userId");
		query.setLong("userId", userInfo.getUserId());
		return (UserLevelInfo) query.uniqueResult();
	}

	public int createUserLevel(UserInfo userInfo, String level) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into user_level (USER_ID,USER_LEVEL) values (:userId,:level)");

		query.setLong("userId", userInfo.getUserId());
		query.setString("level", level);
		return query.executeUpdate();
	}

	public int updateUserLevel(UserInfo userInfo, UserLevelInfo userLevelInfo,
			String level,int numberOfDays,Date date) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.userLevel = :level , "
						+ "u.totalNumberOfDays = :totalNumberOfDays , "
						+ "u.completedNumberOfDays = :completedNumberOfDays , "
						+ "u.skippedNumberOfDays = :skippedNumberOfDays , "
						+ "u.startDate = :startDate , u.isResult = :isResult ,"
						+ " u.numberOfTrue = :numberOfTrue ,"
						+ " u.totalNumberOfQuestions = :totalNumberOfQuestions ,"
						+ " u.attendedNumberOfDays = :attendedNumberOfDays "
						+ " where u.userLevelId = :userLevelId and  userInfo.userId = :uid");
		query.setString("level", level);
		query.setInteger("totalNumberOfDays", numberOfDays);
		query.setInteger("completedNumberOfDays", 0);
		query.setInteger("skippedNumberOfDays", 0);
		query.setDate("startDate", date);
		query.setString("isResult", Config.NO);
		query.setInteger("numberOfTrue", 0);
		query.setInteger("totalNumberOfQuestions", 0);
		query.setInteger("attendedNumberOfDays", 0);
		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		query.setLong("uid", userInfo.getUserId());
		return query.executeUpdate();
	}

	public int updateUserLevelInfo(UserInfo userInfo, UserBean user) {
		// TODO Auto-generated method stub
		Integer totalDays = null;
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.startDate = :date , u.totalNumberOfDays = :totalNumberOfDays where  userInfo.userId = :uid");
		query.setDate("date", user.getDate());
		query.setLong("uid", userInfo.getUserId());

		switch (user.getLevelNumber()) {
		case "1":
			totalDays = new Integer(30);
			break;

		case "2":
			totalDays = new Integer(30);
			break;

		case "3":
			totalDays = new Integer(15);
			break;

		case "4":
			totalDays = new Integer(15);
			break;

		default:
			break;
		}

		query.setInteger("totalNumberOfDays", totalDays);
		return query.executeUpdate();
	}

	public int updateLevelTestSubmittion(UserLevelInfo userLevelInfo,
			UserBean user, int daysFromStartDate) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.completedNumberOfDays = :completedNumberOfDays , u.numberOfTrue = :numberOfTrue , u.totalNumberOfQuestions = :totalNumberOfQuestions , u.isResult = :isResult , u.attendedNumberOfDays = :attendedNumberOfDays , u.skippedNumberOfDays = :skippedNumberOfDays  where  userInfo.userId = :uid");
		query.setInteger("completedNumberOfDays", daysFromStartDate);
		query.setInteger("numberOfTrue",
				user.getNumberOfTrue() + userLevelInfo.getNumberOfTrue());
		query.setInteger("totalNumberOfQuestions",
				user.getTotalNumberOfQuestions());

		int daysAttend = userLevelInfo.getAttendedNumberOfDays() + 1;
		int daysSkipped = daysFromStartDate - daysAttend;

		query.setInteger("attendedNumberOfDays", daysAttend);
		query.setInteger("skippedNumberOfDays", daysSkipped);
		query.setString("isResult", user.getIsResult());
		query.setLong("uid", user.getUserId());
		return query.executeUpdate();
	}

}
