package com.chityog.chityogws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chityog.chityogws.bean.UserBean;
import com.chityog.chityogws.dao.PaymentDao;
import com.chityog.chityogws.domain.PaymentInfo;
import com.chityog.chityogws.domain.UserInfo;

@Service
public class PaymentService {

	@Autowired
	private PaymentDao paymentDao;

	public PaymentInfo checkExistingPaymentStatus(UserInfo userInfo,
			UserBean user) {
		return paymentDao.checkExistingPayment(userInfo, user);
	}

	public int createPayment(UserInfo userInfo, UserBean user) {
		return paymentDao.createPayment(userInfo, user);
	}

}
