package com.chityog.chityogws.dao;

import java.sql.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.NotificationInfo;
import com.chityog.chityogws.domain.UserInfo;

@Repository
@Transactional
public class NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int createNotification(UserInfo userInfo, UserBean user, String msg,
			Date date) {
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into user_notification (NOTIFICATION_MSG,NOTIFICATION_DATE,USER_ID) values (:notificationMsg,:notificationDate,:userId)");
		query.setLong("userId", userInfo.getUserId());
		query.setString("notificationMsg", msg);
		query.setDate("notificationDate", date);

		return query.executeUpdate();
	}

	public List<NotificationInfo> getNotifications(UserInfo userInfo) {

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from NotificationInfo where userInfo.userId = :userId Order By notificationId DESC");
		query.setLong("userId", userInfo.getUserId());

		return query.list();

	}

}
