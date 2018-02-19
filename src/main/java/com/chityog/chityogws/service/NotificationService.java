package com.chityog.chityogws.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.NotificationDao;
import com.chityog.chityogws.domain.NotificationInfo;
import com.chityog.chityogws.domain.UserInfo;

@Service
public class NotificationService {

	@Autowired
	private NotificationDao notificationDao;

	public int createNotification(UserInfo userInfo, UserBean user, String msg,
			Date date) {
		return notificationDao.createNotification(userInfo, user, msg, date);
	}

	public List<NotificationInfo> getNotifications(UserInfo userInfo) {
		return notificationDao.getNotifications(userInfo);
	}

}
