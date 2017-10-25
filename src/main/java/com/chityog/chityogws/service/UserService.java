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
	
	public UserInfo checkExistingUser(UserBean user){
		return userDao.getUser(user);
	}
	
	public UserInfo checkExistingPhone(UserBean user){
		return userDao.getUserPhone(user);
	}
	
	public UserInfo checkExistingUserId(UserBean user){
		return userDao.getUserId(user);
	}
	
	public void createUser(UserBean user){
		userDao.createUser(user);
	}

	public int updateUserPassword(UserBean user) {
		// TODO Auto-generated method stub
		return userDao.updateUserPassword(user);
	}

	public int updateRandomPassword(UserInfo userInfo, ForgotPasswordInfo forgotPasswordInfo, String randomStr) {
		// TODO Auto-generated method stub
		return userDao.updateRandomPassword(userInfo,forgotPasswordInfo,randomStr);
	}

	public ForgotPasswordInfo checkExistingCode(UserInfo userInfo) {
		// TODO Auto-generated method stub
		return userDao.checkExistingCode(userInfo);
	}

	public int createNewRandomPassword(UserInfo userInfo, String randomStr) {
		// TODO Auto-generated method stub
		return userDao.createNewRandomPassword(userInfo,randomStr);
	}



}
