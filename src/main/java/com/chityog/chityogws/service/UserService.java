package com.chityog.chityogws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.UserDao;
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
	
	public void createUser(UserBean user){
		userDao.createUser(user);
	}

}
