package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.UserInfo;

@Repository
@Transactional
public class NotificationDao {

	@Autowired
	private SessionFactory sessionFactory;

	public int createNotification(UserInfo userInfo, UserBean user,String msg) {
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into user_notification (NOTIFICATION_MSG,NOTIFICATION_DATE,USER_ID) values (:notificationMsg,:notificationDate,:userId)");
        query.setLong("userId", userInfo.getUserId());
		query.setString("notificationMsg", msg);
		query.setDate("notificationDate", null);
	
		return query.executeUpdate();
	}

}
