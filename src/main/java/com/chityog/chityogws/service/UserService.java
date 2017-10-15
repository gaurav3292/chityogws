package com.chityog.chityogws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	
	public void createUser(UserBean user){
		userDao.createUser(user);
	}

}
