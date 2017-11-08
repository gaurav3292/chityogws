package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

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
			String level) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.userLevel = :level where u.userLevelId = :userLevelId and  userInfo.userId = :uid");
		query.setString("level", level);
		query.setLong("userLevelId", userLevelInfo.getUserLevelId());
		query.setLong("uid", userInfo.getUserId());
		return query.executeUpdate();
	}

	public int updateUserLevel(UserInfo userInfo, UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.startDate = :date , u.totalNumberOfDays = :totalNumberOfDays where  userInfo.userId = :uid");
		query.setDate("date", user.getDate());
		query.setLong("uid", userInfo.getUserId());
		query.setString("totalNumberOfDays", "30");
		return query.executeUpdate();
	}

	public int updateLevelTestSubmittion(UserLevelInfo userLevelInfo,
			UserBean user, int daysFromStartDate) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"UPDATE  UserLevelInfo u SET u.completedNumberOfDays = :completedNumberOfDays , u.numberOfTrue = :numberOfTrue , u.totalNumberOfQuestions = :totalNumberOfQuestions . u.isResult = :isResult  where  userInfo.userId = :uid");
		query.setInteger("completedNumberOfDays",daysFromStartDate);
		query.setInteger("numberOfTrue", user.getNumberOfTrue());
		query.setInteger("totalNumberOfQuestions", user.getTotalNumberOfQuestions());
		query.setString("isResult", user.getIsResult());
		query.setLong("uid", user.getUserId());
		return query.executeUpdate();
	}

}
