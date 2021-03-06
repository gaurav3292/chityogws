package com.chityog.chityogws.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.UserLevelDao;
import com.chityog.chityogws.domain.UserInfo;
import com.chityog.chityogws.domain.UserLevelInfo;

@Service
public class UserLevelService {

	@Autowired
	private UserLevelDao userLevelDao;

	public UserLevelInfo checkExistingUserLevel(UserInfo userInfo) {
		return userLevelDao.checkExistingUserLevel(userInfo);
	}

	public int createUserLevel(UserInfo userInfo, String level) {
		// TODO Auto-generated method stub
		return userLevelDao.createUserLevel(userInfo, level);
	}

	public int updateUserLevel(UserInfo userInfo, UserLevelInfo userLevelInfo,
			String level, int numberOfDays, Date date) {
		return userLevelDao.updateUserLevel(userInfo, userLevelInfo, level,
				numberOfDays, date);
	}

	public int updateUserLevel(UserInfo userInfo, UserLevelInfo userLevelInfo,
			String level, int numberOfDays, Date date, String subLevel) {
		return userLevelDao.updateUserLevel(userInfo, userLevelInfo, level,
				numberOfDays, date, subLevel);
	}

	public int updateUserLevelInfo(UserInfo userInfo, UserBean user) {
		// TODO Auto-generated method stub
		return userLevelDao.updateUserLevelInfo(userInfo, user);
	}

	public int updateLevelTestSubmittion(UserLevelInfo userLevelInfo,
			UserBean user, int daysFromStartDate) {
		// TODO Auto-generated method stub
		return userLevelDao.updateLevelTestSubmittion(userLevelInfo, user,
				daysFromStartDate);
	}

	public int updateLevelTestProgramme(UserLevelInfo userLevelInfo,
			UserBean user) {
		// TODO Auto-generated method stub
		return userLevelDao.updateLevelTestProgramme(userLevelInfo, user);
	}

	public int updateExtraResult(UserInfo userInfo, UserLevelInfo userLevelInfo) {
		// TODO Auto-generated method stub
		return userLevelDao.updateExtraResult(userInfo, userLevelInfo);

	}

	public int updateUserLevelPaymentStatus(UserLevelInfo userLevelInfo) {
		return userLevelDao.updateUserLevelPaymentStatus(userLevelInfo);
	}

	public int updateNotification(UserLevelInfo userLevelInfo) {
		// TODO Auto-generated method stub
		return userLevelDao.updateNotiification(userLevelInfo);
		
	}

}
