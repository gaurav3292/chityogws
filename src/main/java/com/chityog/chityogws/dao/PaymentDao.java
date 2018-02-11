package com.chityog.chityogws.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.domain.PaymentInfo;
import com.chityog.chityogws.domain.UserInfo;

@Repository
@Transactional
public class PaymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public PaymentInfo checkExistingPayment(UserInfo userInfo, UserBean user) {
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(
						"FROM PaymentInfo p where userInfo.userId = :userId and p.userLevel = :level");
		query.setLong("userId", userInfo.getUserId());
		query.setString("level", user.getLevelNumber());
		return (PaymentInfo) query.uniqueResult();
	}

	public int createPayment(UserInfo userInfo, UserBean user) {
		// TODO Auto-generated method stub
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"insert into user_level_payment (USER_ID,USER_LEVEL,PAYPAL_ID,CREATE_TIME,STATE) values (:userId,:level,:paypalId,:time,:state)");

		query.setLong("userId", userInfo.getUserId());
		query.setString("level", user.getLevelNumber());
		query.setString("paypalId", user.getPayment().getId());
		query.setString("time", user.getPayment().getCreate_time());
		query.setString("state", user.getPayment().getState());
		return query.executeUpdate();
	}

}
