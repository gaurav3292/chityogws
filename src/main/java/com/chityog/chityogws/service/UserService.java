package com.chityog.chityogws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.UserDao;
import com.chityog.chityogws.domain.ForgotPasswordInfo;
import com.chityog.chityogws.domain.UserInfo;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public UserInfo checkExistingUser(UserBean user) {
		return userDao.getUser(user);
	}

	public UserInfo checkExistingPhone(UserBean user) {
		return userDao.getUserPhone(user);
	}

	public UserInfo checkExistingUserId(UserBean user) {
		return userDao.getUserId(user);
	}

	public UserInfo checkExistingUserId(Long userId) {
		return userDao.getUserId(userId);
	}

	public void createUser(UserBean user) {
		userDao.createUser(user);
	}

	public int updateUserDevice(UserBean user, UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.updateUserDevice(user, userInfo);
	}

	public int updateUserPassword(UserBean user) {
		// TODO Auto-generated method stub
		return userDao.updateUserPassword(user);
	}

	public int updateRandomPassword(UserInfo userInfo,
			ForgotPasswordInfo forgotPasswordInfo, String randomStr) {
		// TODO Auto-generated method stub
		return userDao.updateRandomPassword(userInfo, forgotPasswordInfo,
				randomStr);
	}

	public ForgotPasswordInfo checkExistingCode(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.checkExistingCode(userInfo);
	}
	
	public ForgotPasswordInfo checkExistingCode(UserBean userBean) {
		// TODO Auto-generated method stub
		return userDao.checkExistingCode(userBean);
	}

	public int createNewRandomPassword(UserInfo userInfo, String randomStr) {
		// TODO Auto-generated method stub
		return userDao.createNewRandomPassword(userInfo, randomStr);
	}

	public int updateTrues(UserInfo userInfo, int numberOfTrue) {
		// TODO Auto-generated method stub
		return userDao.updateTrues(userInfo, numberOfTrue);
	}

	public int updateToken(UserInfo userInfo, String token) {
		// TODO Auto-generated method stub
		return userDao.updateToken(userInfo, token);
	}

	public int updateUserEmailVerification(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.updateUserEmailVerification(userInfo);
	}

	public int updateProfile(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.updateProfile(userInfo);
	}

	public int updateNotificationCount(UserInfo userInfo) {
		return userDao.updateNotificationCount(userInfo);
	}

	public int readNotification(UserInfo userInfo) {
		return userDao.readNotification(userInfo);
	}

}
