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
			String level,int numberOfDays,Date date) {
		return userLevelDao.updateUserLevel(userInfo, userLevelInfo, level,numberOfDays,date);
	}

	public int updateUserLevelInfo(UserInfo userInfo, UserBean user) {
		// TODO Auto-generated method stub
		return userLevelDao.updateUserLevelInfo(userInfo,user);
	}

	public int updateLevelTestSubmittion(UserLevelInfo userLevelInfo,
			UserBean user, int daysFromStartDate) {
		// TODO Auto-generated method stub
		return userLevelDao.updateLevelTestSubmittion(userLevelInfo,user,daysFromStartDate);
	}

}
